package com.cmlx.elasticsearch.persist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Long deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getIsPerfect() {
        return isPerfect;
    }

    public void setIsPerfect(Integer isPerfect) {
        this.isPerfect = isPerfect;
    }

    public Integer getIsProhibit() {
        return isProhibit;
    }

    public void setIsProhibit(Integer isProhibit) {
        this.isProhibit = isProhibit;
    }

    public Integer getIsPhone() {
        return isPhone;
    }

    public void setIsPhone(Integer isPhone) {
        this.isPhone = isPhone;
    }
}
