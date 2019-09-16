package com.cmlx.elasticsearch.service.impl;

import com.cmlx.elasticsearch.persist.entity.UserBaseInfoEntity;
import com.cmlx.elasticsearch.persist.repository.UserBaseInfoRepository;
import com.cmlx.elasticsearch.service.IUserService;
import org.assertj.core.util.Lists;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-2 0002 19:48
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserBaseInfoRepository userBaseInfoRepository;

    @Override
    public List<UserBaseInfoEntity> search(String key) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(key);
        builder.field("nickName.pinyin");
        Iterable<UserBaseInfoEntity> search = userBaseInfoRepository.search(builder);
        return Lists.newArrayList(search);
    }
}
