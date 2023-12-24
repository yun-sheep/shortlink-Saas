package com.shorlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shorlink.project.dao.entity.ShortLinkDO;
import com.shorlink.project.dto.rep.ShortLinkCreateReqDTO;
import com.shorlink.project.dto.resp.ShortLinkCreateRespDTO;

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
    
    
}
