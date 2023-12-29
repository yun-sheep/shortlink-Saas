package com.shorlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shorlink.project.dao.entity.ShortLinkDO;
import com.shorlink.project.dto.biz.ShortLinkStatsRecordDTO;
import com.shorlink.project.dto.rep.ShortLinkCreateReqDTO;
import com.shorlink.project.dto.rep.ShortLinkUpdateReqDTO;
import com.shorlink.project.dto.resp.ShortLinkCreateRespDTO;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

/**
 * @Description 短链接接口层
 * @auther j2-yizhiyang
 * @date 2023/12/24 10:25
 */
public interface ShortLinkService extends IService<ShortLinkDO> {
    /** 
    *@Description: 新建短链接
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/24
    *@return: com.shorlink.project.dto.resp.ShortLinkCreateRespDTO
    *
    */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    /** 
    *@Description: 修改短链接
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/24
    *@return: void
    *
    */
    void updateShortLink(ShortLinkUpdateReqDTO requestParam);
    /** 
    *@Description: 短链接跳转原始链接
    *@Param: [shortUri, request, response]
    *@Author: yun
    *@Date: 2023/12/26
    *@return: void
    *
    */
    void restoreUrl(String shortUri, ServletRequest request, ServletResponse response) throws IOException;
    /** 
    *@Description: 短链接统计
    *@Param: [fullShortUrl, gid, shortLinkStatsRecord]
    *@Author: yun
    *@Date: 2023/12/28
    *@return: void
    *
    */
    void shortLinkStats(String fullShortUrl, String gid, ShortLinkStatsRecordDTO shortLinkStatsRecord);
}
