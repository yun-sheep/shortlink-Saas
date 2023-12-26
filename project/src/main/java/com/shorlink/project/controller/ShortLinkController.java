package com.shorlink.project.controller;

import com.shorlink.project.common.convention.result.Result;
import com.shorlink.project.common.convention.result.Results;
import com.shorlink.project.dto.rep.ShortLinkCreateReqDTO;
import com.shorlink.project.dto.rep.ShortLinkUpdateReqDTO;
import com.shorlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.shorlink.project.service.ShortLinkService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Description 短链接控制层
 * @auther j2-yizhiyang
 * @date 2023/12/24 11:13
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    private final ShortLinkService shortLinkService;
    /**
    *@Description: 新建短链接
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/24
    *@return: com.shorlink.project.common.convention.result.Result<com.shorlink.project.dto.resp.ShortLinkCreateRespDTO>
    *
    */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }
    /** 
    *@Description: 修改短链接
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/26
    *@return: com.shorlink.project.common.convention.result.Result<java.lang.Void>
    *
    */
    @PostMapping("/api/short-link/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkService.updateShortLink(requestParam);
        return Results.success();
    }
    /** 
    *@Description: 短链接跳转原始链接
    *@Param: [shortUri, request, response]
    *@Author: yun
    *@Date: 2023/12/26
    *@return: void
    *
    */
    @GetMapping("/{short-uri}")
    public void restoreUrl(@PathVariable("short-uri") String shortUri, ServletRequest request, ServletResponse response) throws IOException {
        shortLinkService.restoreUrl(shortUri, request, response);
    }

}
