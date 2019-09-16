package com.cmlx.elasticsearch.persist.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-16 0016 9:25
 */
@Accessors(chain = true)
@Data
@Setting(settingPath = "elasticsearch/ucenter/userinfo_setting.json")
@Mapping(mappingPath = "elasticsearch/ucenter/userinfo_mapping.json")
@Document(indexName = "aimymusic", type = "userinfo")
public class UserBaseInfoEntity {

    @Id
    @Field(type = FieldType.Keyword,store = true)
    private Long uid;     //账户Id

    @Field(type = FieldType.Keyword,store = true)
    private String phone;   //手机号

    @Field(type = FieldType.Keyword,store = true)
    private String email;   //邮箱

    @Field(type = FieldType.Text,store = true)
    private String realName;    //真名

    @Field(type = FieldType.Text,store = true)
    private String nickName;    //昵称

    @Field(type = FieldType.Text,store = true)
    private String avatarUri;    //头像

    @Field(type = FieldType.Text,store = true)
    private String description;    //个性签名

    @Field(type = FieldType.Text,store = true)
    private String birthday;      //生日

    @Field(type = FieldType.Integer,store = true)
    private Integer sex;        //性别 0-未知 1-男 2-女

    @Field(type = FieldType.Text,store = true)
    private String constellation;   //星座

    @Field(type = FieldType.Text,store = true)
    private String job;   //职业

    @Field(type = FieldType.Text,store = true)
    private String industry;   //行业

    @Field(type = FieldType.Text,store = true)
    private String hobby;   //喜好

    @Field(type = FieldType.Text,store = true)
    private String education;   //学历

    @Field(type = FieldType.Text,store = true)
    private String income;   //收入

    @Field(type = FieldType.Text,store = true)
    private String country;   //国家

    @Field(type = FieldType.Text,store = true)
    private String language;   //语言

    @Field(type = FieldType.Text,store = true)
    private String province;   //省份

    @Field(type = FieldType.Text,store = true)
    private String city;   //城市

    @Field(type = FieldType.Text,store = true)
    private String address;   //详细地址

    @Field(type = FieldType.Text,store = true)
    private String source;   //已绑定的其他账号标识  数组

    @Field(type = FieldType.Long,store = true)
    private Long createdAt;     //创建时间

    @Field(type = FieldType.Long,store = true)
    private Long updatedAt;     //更新时间

    @Field(type = FieldType.Long,store = true)
    private Long deletedAt;     //删除时间

    @Field(type = FieldType.Integer,store = true)
    private Integer status;     //用户状态，0未激活，1正常，2已拉黑

    @Field(type = FieldType.Keyword,store = true)
    private Integer age;     //年龄

    @Field(type = FieldType.Integer,store = true)
    private Integer isPerfect; //0 未完善 1 完善

    @Field(type = FieldType.Integer,store = true)
    private Integer isProhibit; //是否被封禁

    @Field(type = FieldType.Integer,store = true)
    private Integer isPhone;


}
