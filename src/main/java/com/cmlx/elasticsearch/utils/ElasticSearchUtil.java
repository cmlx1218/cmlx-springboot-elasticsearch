package com.cmlx.elasticsearch.utils;

import com.cmlx.elasticsearch.persist.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-2 0002 20:00
 */
@Component
public class ElasticSearchUtil {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public void createIndex(UserDto userDto){

        elasticsearchTemplate.createIndex(userDto.getClass());

    }

    public void deleteIndex(UserDto userDto){
        elasticsearchTemplate.deleteIndex(userDto.getClass());
    }



}
