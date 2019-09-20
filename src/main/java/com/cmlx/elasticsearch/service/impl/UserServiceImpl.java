package com.cmlx.elasticsearch.service.impl;

import com.cmlx.elasticsearch.persist.entity.UserBaseInfoEntity;
import com.cmlx.elasticsearch.persist.repository.UserBaseInfoRepository;
import com.cmlx.elasticsearch.service.IUserService;
import com.cmlx.elasticsearch.utils.BeanToMapUtil;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-2 0002 19:48
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserBaseInfoRepository userBaseInfoRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Map<String,Object> search(String key,int page,int size){

        /*SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryStringQuery(key))
                .withQuery(boolQuery()
                        .must(termQuery("isPerfect",1)).must(termQuery("isProhibit",0))).build();
        List<UserBaseInfoEntity> userBaseInfoEntities = elasticsearchTemplate.queryForList(searchQuery, UserBaseInfoEntity.class);
        return Lists.newArrayList(userBaseInfoEntities);*/

//        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(key);
//        builder.field("nickName").analyzer("pinyin_analyzer");
//        Iterable<UserBaseInfoEntity> search = userBaseInfoRepository.search(builder);
//        ArrayList<UserBaseInfoEntity> userBaseInfoEntities = Lists.newArrayList(search);
//        ArrayList<UserBaseInfoEntity> userBaseInfoEntities1 = new ArrayList<>();
//        for (UserBaseInfoEntity u : userBaseInfoEntities) {
//            if ( u.getIsProhibit() ==null || u.getIsPerfect() == null || u.getIsProhibit() != 0 || u.getIsPerfect() != 1) {
//                userBaseInfoEntities1.add(u);
//            }
//        }
//        userBaseInfoEntities.removeAll(userBaseInfoEntities1);
//        return userBaseInfoEntities;\

        Map<String,Object> map = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size);
        String preTag = "<font color='#67CE90'>";//google的色值
        String postTag = "</font>";

        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchPhraseQuery("nickName.pinyin", key))
                .should(QueryBuilders.matchPhraseQuery("uid", key));
//        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(key,"nickName.pinyin","uid");
        if (isContainChinese(key)) {
            queryBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.matchPhraseQuery("nickName", key))
                    .should(QueryBuilders.matchPhraseQuery("uid", key));
//            queryBuilder = QueryBuilders.multiMatchQuery(key,"nickName","uid");

        }


        QueryBuilder queryBuilderfilter = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("isPerfect", "1"))
                .must(QueryBuilders.matchQuery("isProhibit", "0"));


        NativeSearchQuery build = new NativeSearchQueryBuilder().withFilter(queryBuilderfilter).withQuery(queryBuilder).withPageable(pageable).withHighlightFields(new HighlightBuilder.Field[]{new HighlightBuilder.Field("nickName").postTags(postTag).preTags(preTag),new HighlightBuilder.Field("nickName.pinyin").postTags(postTag).preTags(preTag)}).build();
        Page<UserBaseInfoEntity> search = userBaseInfoRepository.search(build);
        long totalElements = search.getTotalElements();
        int totalPages = search.getTotalPages();
        AggregatedPage<UserBaseInfoEntity> userBaseInfoEntities = elasticsearchTemplate.queryForPage(build, UserBaseInfoEntity.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                SearchHits searchHits = searchResponse.getHits();
                SearchHit[] hits = searchHits.getHits();
                ArrayList<UserBaseInfoEntity> userBaseInfoEntities = new ArrayList<>();
                for (SearchHit hit : hits) {
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    try {
                        UserBaseInfoEntity userBaseInfoEntity = BeanToMapUtil.convertMap2Bean(sourceAsMap, UserBaseInfoEntity.class);
                        //高亮
                        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                        System.out.println(highlightFields);
                        if (highlightFields.get("nickName") != null){
                            String s = highlightFields.get("nickName").getFragments()[0].toString();
                            userBaseInfoEntity.setNickName(s);
                        }
                        if (highlightFields.get("nickName.pinyin") != null){
                            String s = highlightFields.get("nickName.pinyin").getFragments()[0].toString();
                            userBaseInfoEntity.setNickName(s);
                        }
/*                        if (highlightFields.get("uid") != null){
                            Long s =Long.parseLong(highlightFields.get("uid").getFragments()[0].toString());
                            userBaseInfoEntity.setUid(s);
                        }*/
                        userBaseInfoEntities.add(userBaseInfoEntity);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return new AggregatedPageImpl<T>((List<T>) userBaseInfoEntities);
            }
        });

        /*List<UserBaseInfoEntity> content = userBaseInfoRepository.search(build).getContent();*/
        List<UserBaseInfoEntity> content = userBaseInfoEntities.getContent();
        map.put("content",content);
        map.put("totalElements",totalElements);
        map.put("totalPages",totalPages);
        return map;


    }

    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

/*    public DisMaxQueryBuilder structureQuery(String content) {
        //使用dis_max直接取多个query中，分数最高的那一个query的分数即可
        DisMaxQueryBuilder disMaxQueryBuilder = QueryBuilders.disMaxQuery();
        //boost 设置权重,只搜索匹配name和disrector字段
        QueryBuilder ikNameQuery = QueryBuilders.matchQuery("nickName", content).boost(2f);
        QueryBuilder pinyinNameQuery = QueryBuilders.matchQuery("nickName.pinyin", content);
        disMaxQueryBuilder.add(ikNameQuery);
        disMaxQueryBuilder.add(pinyinNameQuery);
        return disMaxQueryBuilder;
    }*/
}
