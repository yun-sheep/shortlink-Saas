package com.shorlink.project.controller;

import com.shorlink.project.common.convention.result.Result;
import com.shorlink.project.common.convention.result.Results;
import com.shorlink.project.dto.rep.ShortLinkStatsReqDTO;
import com.shorlink.project.dto.resp.ShortLinkStatsRespDTO;
import com.shorlink.project.service.ShortLinkStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @auther j2-yizhiyang
 * @date 2023/12/28 21:13
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkStatsController {
    private final ShortLinkStatsService shortLinkStatsService;
    /**
    *@Description: 单个短链接监控数据
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/28
    *@return: Result<ShortLinkStatsRespDTO>
    *
    */
    @GetMapping("/api/short-link/v1/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return Results.success(shortLinkStatsService.oneShortLinkStats(requestParam));
    }

}
