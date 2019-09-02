package com.cmlx.elasticsearch;

import com.cmlx.elasticsearch.persist.dto.UserDto;
import com.cmlx.elasticsearch.utils.ElasticSearchUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchApplicationTests {

    @Autowired
    private ElasticSearchUtil elasticSearchUtil;


    @Test
    public void createIndex() {
        UserDto userDto = new UserDto();
        elasticSearchUtil.createIndex(userDto);
    }

}
