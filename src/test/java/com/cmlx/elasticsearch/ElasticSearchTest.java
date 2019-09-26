//package com.cmlx.elasticsearch;
//
//import com.cmlx.elasticsearch.persist.dto.UserDto;
//import com.cmlx.elasticsearch.persist.entity.GroupEntity;
//import com.cmlx.elasticsearch.persist.repository.UserRepository;
//import com.cmlx.elasticsearch.persist.entity.UserBaseInfoEntity;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.index.query.QueryStringQueryBuilder;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.Iterator;
//
// /*
// * @Desc
// * @Author cmlx
// * @Date 2019-9-5 0005 18:38
// **/
//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ElasticsearchApplication.class)
//public class ElasticSearchTest {
//
//    @Autowired
//    ElasticsearchTemplate elasticsearchTemplate;
//
//    @Autowired
//    UserRepository userRepository;
//
//
//     @Test
//     public void createUserInfoIndex() {
//         elasticsearchTemplate.createIndex(UserBaseInfoEntity.class);
//     }
//
//     @Test
//     public void createGroupIndex() {
//         elasticsearchTemplate.createIndex(GroupEntity.class);
//     }
//
//
//    @Test
//    public void testCreateIndex() {
//        elasticsearchTemplate.createIndex(UserDto.class);
//    }
//
//
//
//
//    @Test
//    public void addMapping() {
//        userRepository.save(new UserDto().setUserId(11L).setUserName("郑强").setAddress("成都").setPrice(111D));
//        userRepository.save(new UserDto().setUserId(22L).setUserName("赤名莉香").setAddress("美国").setPrice(111D));
//        userRepository.save(new UserDto().setUserId(33L).setUserName("和贺夏树").setAddress("东京").setPrice(111D));
//        userRepository.save(new UserDto().setUserId(44L).setUserName("永尾完治").setAddress("爱媛").setPrice(111D));
//        userRepository.save(new UserDto().setUserId(55L).setUserName("关口里美").setAddress("东京").setPrice(111D));
//        userRepository.save(new UserDto().setUserId(66L).setUserName("张无忌").setAddress("武当").setPrice(111D));
//        userRepository.save(new UserDto().setUserId(77L).setUserName("周芷若").setAddress("峨眉").setPrice(111D));
//        userRepository.save(new UserDto().setUserId(88L).setUserName("赵敏").setAddress("五").setPrice(111D));
//        userRepository.save(new UserDto().setUserId(99L).setUserName("无心").setAddress("寒水寺").setPrice(111D));
//        userRepository.save(new UserDto().setUserId(111L).setUserName("雷无桀").setAddress("无双城").setPrice(111D));
//        userRepository.save(new UserDto().setUserId(222L).setUserName("李星云").setAddress("汴梁").setPrice(111D));
//    }
//
//    @Test
//    public void testSearch() {
//        QueryStringQueryBuilder builder = new QueryStringQueryBuilder("zhengqiang");
//        builder.field("userName.pinyin");
//        Iterable<UserDto> search = userRepository.search(builder);
//        Iterator<UserDto> iterator = search.iterator();
//        while (iterator.hasNext()) {
//            System.out.println("---》》匹配数据：" + iterator.next());
//        }
//    }
//
//    @Test
//    public void testfenci() {
//        QueryStringQueryBuilder builder = new QueryStringQueryBuilder("wx");
//        builder.analyzer("pinyin_analyzer").field("userName.pinyin").field("address.pinyin");
//        Iterable<UserDto> search = userRepository.search(builder);
//        Iterator<UserDto> iterator = search.iterator();
//        while (iterator.hasNext()) {
//            System.out.println("---》》匹配数据：" + iterator.next());
//        }
//    }
//
//    @Test
//    public void testdeleteIndex() {
//        elasticsearchTemplate.deleteIndex(UserDto.class);
//    }
//
//
//
//}
