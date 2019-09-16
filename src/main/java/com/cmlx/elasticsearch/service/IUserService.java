package com.cmlx.elasticsearch.service;

import com.cmlx.elasticsearch.persist.entity.UserBaseInfoEntity;

import java.util.List;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-2 0002 19:47
 */
public interface IUserService {

    List<UserBaseInfoEntity> search(String key);

}


