package com.shortlink.admin.controller;

import com.shortlink.admin.common.convention.result.Result;
import com.shortlink.admin.common.convention.result.Results;
import com.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description 短链接分组控制层
 * @auther j2-yizhiyang
 * @date 2023/12/23 16:19
 */
@RestController
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    /**
    *@Description: 新增短链接分组
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/23
    *@return: Result<Void>
    *
    */
    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }
    /**
    *@Description: 查询短链接分组集合
    *@Param: []
    *@Author: yun
    *@Date: 2023/12/23
    *@return: com.shortlink.admin.common.convention.result.Result<java.util.List<com.shortlink.admin.dto.resp.ShortLinkGroupRespDTO>>
    *
    */
    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }
    /**
    *@Description: 修改短链接分组名称
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/23
    *@return: com.shortlink.admin.common.convention.result.Result<java.lang.Void>
    *
    */
    @PutMapping("/api/short-link/admin/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }
    /**
    *@Description: 修改短链接分组名（只能修改分组名）
    *@Param: [gid]
    *@Author: yun
    *@Date: 2023/12/23
    *@return: com.shortlink.admin.common.convention.result.Result<java.lang.Void>
    *
    */
    @DeleteMapping("/api/short-link/admin/v1/group")
    public Result<Void> updateGroup(@RequestParam String gid) {
        groupService.deleteGroup(gid);
        return Results.success();
    }
}
