package com.shorlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shorlink.project.dao.entity.ShortLinkDO;
import com.shorlink.project.dto.rep.RecycleBinRecoverReqDTO;
import com.shorlink.project.dto.rep.RecycleBinRemoveReqDTO;
import com.shorlink.project.dto.rep.RecycleBinSaveReqDTO;

/**
 * @Description 回收站接口层
 * @auther j2-yizhiyang
 * @date 2023/12/26 22:55
 */
public interface RecycleBinService extends IService<ShortLinkDO> {
    /** 
    *@Description: 保存到回收站
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/27
    *@return: void
    *
    */
    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    /**
    *@Description: 从回收站回复短链接
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/27
    *@return: void
    *
    */
    void recoverRecycleBin(RecycleBinRecoverReqDTO requestParam);
    void removeRecycleBin(RecycleBinRemoveReqDTO requestParam);
}
