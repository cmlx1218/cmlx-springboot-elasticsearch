package com.cmlx.elasticsearch.persist.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-26 0026 10:00
 */
@Data
public class UserExtendDto {
    private Long uid;

    private double longitude; //经度

    private double latitude; //维度

    private String cityCode; //城市码

    private double distance; //查询范围

    private Integer isPerfect;

    private Long registerTime;

}