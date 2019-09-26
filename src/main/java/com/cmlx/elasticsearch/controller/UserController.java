package com.cmlx.elasticsearch.controller;

import com.cmlx.elasticsearch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

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
    public Map<String,Object> search(@NotBlank String key, @NotNull int page, @NotNull int size){
        Map<String,Object> search = userService.search(key,page,size);
        return search;
    }


}
