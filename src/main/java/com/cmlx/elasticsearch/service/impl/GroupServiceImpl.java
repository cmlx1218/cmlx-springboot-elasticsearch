package com.cmlx.elasticsearch.service.impl;

import com.cmlx.elasticsearch.persist.dto.UserExtendDto;
import com.cmlx.elasticsearch.persist.entity.GroupEntity;
import com.cmlx.elasticsearch.persist.repository.GroupRepository;
import com.cmlx.elasticsearch.service.IGroupService;
import com.cmlx.elasticsearch.utils.BeanToMapUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.cmlx.elasticsearch.service.impl.UserServiceImpl.isContainChinese;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-20 0020 15:23
 */
@Service
public class GroupServiceImpl implements IGroupService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private GroupRepository groupRepository;


    @Override
    public Map<String, Object> search(String key, int page, int size) {

        Map<String, Object> map = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size);
        String preTag = "<font color='#67CE90'>";//google的色值
        String postTag = "</font>";

        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchPhraseQuery("name.pinyin", key))
                .should(QueryBuilders.matchPhraseQuery("id", key));
        if (isContainChinese(key)) {
            queryBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.matchPhraseQuery("name", key))
                    .should(QueryBuilders.matchPhraseQuery("id", key));
        }


        QueryBuilder queryBuilderfilter = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("dataState", "2"))
                .must(QueryBuilders.matchQuery("isOpen", "1"));


        NativeSearchQuery build = new NativeSearchQueryBuilder().withFilter(queryBuilderfilter).withQuery(queryBuilder).withPageable(pageable).withHighlightFields(new HighlightBuilder.Field[]{new HighlightBuilder.Field("name").postTags(postTag).preTags(preTag), new HighlightBuilder.Field("name.pinyin").postTags(postTag).preTags(preTag)}).build();
        Page<GroupEntity> search = groupRepository.search(build);
        long totalElements = search.getTotalElements();
        int totalPages = search.getTotalPages();
        AggregatedPage<GroupEntity> groupEntities = elasticsearchTemplate.queryForPage(build, GroupEntity.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                SearchHits searchHits = searchResponse.getHits();
                SearchHit[] hits = searchHits.getHits();
                ArrayList<GroupEntity> groupEntities = new ArrayList<>();
                for (SearchHit hit : hits) {
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    try {
                        GroupEntity groupEntity = BeanToMapUtil.convertMap2Bean(sourceAsMap, GroupEntity.class);
                        //高亮
                        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                        System.out.println(highlightFields);
                        if (highlightFields.get("name") != null) {
                            String s = highlightFields.get("name").getFragments()[0].toString();
                            groupEntity.setName(s);
                        }
                        if (highlightFields.get("name.pinyin") != null) {
                            String s = highlightFields.get("name.pinyin").getFragments()[0].toString();
                            groupEntity.setName(s);
                        }
                        groupEntities.add(groupEntity);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return new AggregatedPageImpl<T>((List<T>) groupEntities);
            }
        });

        List<GroupEntity> content = groupEntities.getContent();
        map.put("content", content);
        map.put("totalElements", totalElements);
        map.put("totalPages", totalPages);
        return map;
    }

    @Override
    public List<GroupEntity> getRecommandList(int size, int page, UserExtendDto userExtendDto) {
        Pageable pageable = PageRequest.of(page, size);
        ArrayList<GroupEntity> groupEntities = new ArrayList<>();
        long nowTime = System.currentTimeMillis();
        long todayStartTime = nowTime - (nowTime + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24);
        //不推荐私密圈子和被移出的圈子
        QueryBuilder queryBuilderfilter = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("dataState", "2"))
                .must(QueryBuilders.matchQuery("isOpen", "1"));


        //先推荐同城圈子
        if (null != userExtendDto.getCityCode()) {
            QueryBuilder queryBuilder01 = QueryBuilders.boolQuery()
                    .must(QueryBuilders.matchQuery("cityCode", userExtendDto.getCityCode()));


            //同城圈子按距离推荐
            QueryBuilders.geoDistanceQuery("location")
                    .point(userExtendDto.getLatitude(), userExtendDto.getLongitude())
                    .distance(userExtendDto.getDistance(), DistanceUnit.METERS)
                    .geoDistance(GeoDistance.ARC);

            GeoDistanceSortBuilder sortBuilder = SortBuilders
                    .geoDistanceSort("location", userExtendDto.getLatitude(), userExtendDto.getLongitude())
                    .unit(DistanceUnit.METERS)
                    .order(SortOrder.ASC);


//            FieldSortBuilder sortBuilder = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);

            NativeSearchQuery query01 = new NativeSearchQueryBuilder()
                    .withQuery(queryBuilder01)
                    .withFilter(queryBuilderfilter)
                    .withSort(sortBuilder).build();
            Page<GroupEntity> search01 = groupRepository.search(query01);
            System.out.println("同城圈子个数：" + search01.getTotalElements());
            groupEntities.addAll(search01.getContent());
        }
        //推荐今天创建的圈子
        QueryBuilder queryBuilder02 = QueryBuilders.boolQuery()
                .must(QueryBuilders.rangeQuery("createTime").from(todayStartTime - 24 * 3600 * 1000, true).to(todayStartTime + 24 * 3600 * 1000));
        FieldSortBuilder sortBuilder02 = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);
        NativeSearchQuery query02 = new NativeSearchQueryBuilder().withQuery(queryBuilder02).withFilter(queryBuilderfilter).withSort(sortBuilder02).build();
        Page<GroupEntity> search02 = groupRepository.search(query02);
        System.out.println("今日创建圈子个数：" + search02.getTotalElements());
        groupEntities.addAll(search02.getContent());

        //按活跃度高低推荐圈子
        QueryBuilder queryBuilder03 = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchAllQuery());
        FieldSortBuilder sortBuilder03 = SortBuilders.fieldSort("activeValue").order(SortOrder.DESC);
        //去除前面推荐过的圈子
        QueryBuilder queryBuilderfilter01 = QueryBuilders.boolQuery()
                .mustNot(QueryBuilders.matchQuery("cityCode", userExtendDto.getCityCode()))
                .mustNot(QueryBuilders.rangeQuery("createTime").from(todayStartTime - 24 * 3600 * 1000, true).to(todayStartTime + 24 * 3600 * 1000))
                .must(QueryBuilders.matchQuery("dataState", "2"))
                .must(QueryBuilders.matchQuery("isOpen", "1"));
        NativeSearchQuery query03 = new NativeSearchQueryBuilder().withQuery(queryBuilder03).withFilter(queryBuilderfilter01).withSort(sortBuilder03).build();
        Page<GroupEntity> search03 = groupRepository.search(query03);
        System.out.println("活跃度推荐圈子圈子个数：" + search03.getTotalElements());
        groupEntities.addAll(search03.getContent());
        return groupEntities;
    }

    /*@Override
    public List<GroupEntity> getRecommandList(int size, int page, UserExtendDto userExtendDto) {
        Pageable pageable = PageRequest.of(page, size);
        long nowTime = System.currentTimeMillis();
        long todayStartTime = nowTime - (nowTime + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24);

        *//*List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
        filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.rangeQuery("createTime").from(todayStartTime).to(todayStartTime+86400000),
                ScoreFunctionBuilders.weightFactorFunction(4)));
        filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("cityCode", userExtendDto.getCityCode()),
                ScoreFunctionBuilders.weightFactorFunction(10)));



        FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
        filterFunctionBuilders.toArray(builders);
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                .setMinScore(3);
        // 创建搜索 DSL 查询
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();*//*
//        BoolQueryBuilder builder = QueryBuilders.boolQuery();
*//*        FunctionScoreQueryBuilder builder = QueryBuilders.functionScoreQuery(
                QueryBuilders.boolQuery());*//*
        FunctionScoreQueryBuilder builder = QueryBuilders.functionScoreQuery(
                QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("cityCode", userExtendDto.getCityCode()).boost(10000f))
                        .should(QueryBuilders.rangeQuery("createTime").from(todayStartTime - 86400000).to(todayStartTime + 86400000).boost(1000f))
                        .should(QueryBuilders.rangeQuery("activeValue").from(0,true).to(2000).boost(100f))
                        .should(QueryBuilders.matchAllQuery())
        );

        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(builder).withPageable(pageable).withSort(SortBuilders.scoreSort()).build();

        Page<GroupEntity> searchPageResults = groupRepository.search(query);
        System.out.println(searchPageResults.getTotalElements());
        System.out.println(searchPageResults.getTotalPages());
        return searchPageResults.getContent();
    }*/


}




































