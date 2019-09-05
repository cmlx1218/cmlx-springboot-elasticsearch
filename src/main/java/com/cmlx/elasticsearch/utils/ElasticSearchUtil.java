package com.cmlx.elasticsearch.utils;

import com.cmlx.elasticsearch.persist.dto.UserDto;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-2 0002 20:00
 */
@UtilityClass
public class ElasticSearchUtil {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void createIndex(){

        elasticsearchTemplate.createIndex(UserDto.class);

    }

    public void deleteIndex(UserDto userDto){
        elasticsearchTemplate.deleteIndex(userDto.getClass());
    }



}
