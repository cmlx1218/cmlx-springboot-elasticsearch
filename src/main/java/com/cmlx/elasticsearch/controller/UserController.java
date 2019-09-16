package com.cmlx.elasticsearch.controller;

import com.cmlx.elasticsearch.persist.entity.UserBaseInfoEntity;
import com.cmlx.elasticsearch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-2 0002 19:46
 */
@RequestMapping("user")
@RestController
@Validated
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/search")
    public List<UserBaseInfoEntity> search(@NotBlank String key) {
        List<UserBaseInfoEntity> search = userService.search(key);
        return search;
    }


}
