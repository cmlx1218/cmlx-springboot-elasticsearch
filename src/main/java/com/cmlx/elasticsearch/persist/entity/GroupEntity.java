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
 * @Date 2019-9-20 0020 15:22
 */
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setting(settingPath = "elasticsearch/group/group_setting.json")
@Mapping(mappingPath = "elasticsearch/group/group_mapping.json")
@Document(indexName = "aimymusic-distance", type = "group-distance")
public class GroupEntity {

    @Id
    @Field(type = FieldType.Keyword, store = true)
    private Long id;

    @Field(type = FieldType.Text, store = true)
    private String name;

    @Field(type = FieldType.Text, store = true)
    private String description;

    @Field(type = FieldType.Text, store = true)
    private String localization;

    @Field(type = FieldType.Double, store = true)
    private Double longitude;

    @Field(type = FieldType.Double, store = true)
    private Double latitude;

    @Field(type = FieldType.Text, store = true)
    private String location;

    @Field(type = FieldType.Text, store = true)
    private String cityCode;

    @Field(type = FieldType.Integer, store = true)
    private Integer dataState;

    @Field(type = FieldType.Long, store = true)
    private Long createTime;

    @Field(type = FieldType.Long, store = true)
    private Long updateTime;

    @Field(type = FieldType.Long, store = true)
    private Long creatorId;

    @Field(type = FieldType.Text, store = true)
    private String addressName;

    @Field(type = FieldType.Double, store = true)
    private Double activeValue;

    @Field(type = FieldType.Text, store = true)
    private String activeTime;

    @Field(type = FieldType.Integer, store = true)
    private Integer isOpen;

    @Field(type = FieldType.Integer, store = true)
    private Integer isOpenAudit;

    private Double distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getDataState() {
        return dataState;
    }

    public void setDataState(Integer dataState) {
        this.dataState = dataState;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Double getActiveValue() {
        return activeValue;
    }

    public void setActiveValue(Double activeValue) {
        this.activeValue = activeValue;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public Integer getIsOpenAudit() {
        return isOpenAudit;
    }

    public void setIsOpenAudit(Integer isOpenAudit) {
        this.isOpenAudit = isOpenAudit;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
