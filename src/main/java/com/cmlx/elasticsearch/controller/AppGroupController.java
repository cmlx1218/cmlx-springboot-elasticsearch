package com.cmlx.elasticsearch.controller;

import com.cmlx.elasticsearch.persist.dto.UserExtendDto;
import com.cmlx.elasticsearch.persist.entity.GroupEntity;
import com.cmlx.elasticsearch.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @Desc
 * @Author cmlx
 * @Date 2019-9-20 0020 15:21
 */
@RequestMapping("group")
@RestController
@Validated
public class AppGroupController {

    @Autowired
    private IGroupService groupService;

    @RequestMapping("/search")
    public Map<String, Object> search(@NotBlank String key, @NotNull int page, @NotNull int size) {
        Map<String, Object> search = groupService.search(key, page, size);
        return search;
    }

    @RequestMapping("/getRecommandList")
    public List<GroupEntity> getRecommandList(@NotNull Integer size, @NotNull Integer page, UserExtendDto userExtendDto) {
        List<GroupEntity> recommandList = groupService.getRecommandList(size, page, userExtendDto);
        return recommandList;
    }
}