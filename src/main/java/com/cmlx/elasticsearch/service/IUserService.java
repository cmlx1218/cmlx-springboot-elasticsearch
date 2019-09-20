package com.cmlx.elasticsearch.service;

import com.cmlx.elasticsearch.persist.entity.UserBaseInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-2 0002 19:47
 */
public interface IUserService {

    Map<String,Object> search(String key, int page, int size);

}


