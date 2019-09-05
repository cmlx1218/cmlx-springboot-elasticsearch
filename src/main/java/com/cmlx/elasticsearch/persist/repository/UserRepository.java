package com.cmlx.elasticsearch.persist.repository;

import com.cmlx.elasticsearch.persist.dto.UserDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-5 0005 18:46
 */
public interface UserRepository extends ElasticsearchRepository<UserDto,Long> {
}
