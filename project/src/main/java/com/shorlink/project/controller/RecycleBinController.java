package com.shorlink.project.controller;

import com.shorlink.project.common.convention.result.Result;
import com.shorlink.project.common.convention.result.Results;
import com.shorlink.project.dto.rep.RecycleBinRecoverReqDTO;
import com.shorlink.project.dto.rep.RecycleBinRemoveReqDTO;
import com.shorlink.project.dto.rep.RecycleBinSaveReqDTO;
import com.shorlink.project.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 回收站管理控制层
 * @auther j2-yizhiyang
 * @date 2023/12/27 15:29
 */
@RestController
@RequiredArgsConstructor
public class RecycleBinController {
    private final RecycleBinService recycleBinService;
    /** 
    *@Description: 保存到回收站
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/27
    *@return: com.shorlink.project.common.convention.result.Result<java.lang.Void>
    *
    */
    @PostMapping("/api/short-link/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        recycleBinService.saveRecycleBin(requestParam);
        return Results.success();
    }
    /** 
    *@Description: 回复短链接
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/27
    *@return: com.shorlink.project.common.convention.result.Result<java.lang.Void>
    *
    */
    @PostMapping("/api/short-link/v1/recycle-bin/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam) {
        recycleBinService.recoverRecycleBin(requestParam);
        return Results.success();
    }
    /** 
    *@Description: 移除短链接
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/27
    *@return: com.shorlink.project.common.convention.result.Result<java.lang.Void>
    *
    */
    @PostMapping("/api/short-link/v1/recycle-bin/remove")
    public Result<Void> removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam) {
        recycleBinService.removeRecycleBin(requestParam);
        return Results.success();
    }
}
