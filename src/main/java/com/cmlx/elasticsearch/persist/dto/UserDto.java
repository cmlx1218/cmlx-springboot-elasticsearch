package com.cmlx.elasticsearch.persist.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-2 0002 19:49
 */
@Accessors(chain = true)
@Data
@Setting(settingPath = "elasticsearch_setting.json")
@Mapping(mappingPath = "elasticsearch_mapping.json")
@Document(indexName = "school111", type = "class111")
public class UserDto {

    @Id
    @Field(type = FieldType.Keyword, store = true)
    private Long userId;

    @Field(type = FieldType.Text, store = true)
    private String userName;

    @Field(type = FieldType.Text, store = true)
    private String address;

    @Field(type = FieldType.Double, store = true)
    private Double price;

}
