package com.cmlx.elasticsearch.persist.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-2 0002 19:49
 */
@Document(indexName = "company",type = "user",shards = 1,replicas = 0)
public class UserDto {

    @Id
    private Long userId;

    @Field(type = FieldType.String,analyzer = "ik_max_word")
    private String userName;

    @Field(type = FieldType.String,analyzer = "ik_max_word")
    private String address;

    @Field(type = FieldType.Double)
    private Double price;

}
