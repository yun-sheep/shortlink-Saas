package com.shorlink.project.service;

import com.shorlink.project.dto.rep.ShortLinkStatsReqDTO;
import com.shorlink.project.dto.resp.ShortLinkStatsRespDTO;

/**
 * @Description 短链接监控接口层
 * @auther j2-yizhiyang
 * @date 2023/12/28 14:55
 */
public interface ShortLinkStatsService {
    /**
    *@Description: 访问指定时间内单个短链接的统计数据
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/29
    *@return: com.shorlink.project.dto.resp.ShortLinkStatsRespDTO
    *
    */
    ShortLinkStatsRespDTO oneShortLinkStats(ShortLinkStatsReqDTO requestParam);
}
