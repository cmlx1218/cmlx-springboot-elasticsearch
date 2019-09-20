package com.cmlx.elasticsearch.persist.repository;

import com.cmlx.elasticsearch.persist.entity.GroupEntity;
import com.cmlx.elasticsearch.persist.entity.UserBaseInfoEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-5 0005 18:46
 */
public interface GroupRepository extends ElasticsearchRepository<GroupEntity, Long> {
}
