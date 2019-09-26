package com.cmlx.elasticsearch.service;

import com.cmlx.elasticsearch.persist.dto.UserExtendDto;
import com.cmlx.elasticsearch.persist.entity.GroupEntity;

import java.util.List;
import java.util.Map;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-20 0020 15:23
 */
public interface IGroupService {

    Map<String,Object> search(String key,int page,int size);

    List<GroupEntity> getRecommandList(int size, int page, UserExtendDto userExtendDto);

}
