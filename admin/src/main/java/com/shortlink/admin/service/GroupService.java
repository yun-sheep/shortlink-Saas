package com.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shortlink.admin.dao.entity.GroupDO;
import com.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;

import java.util.List;

/**
 * @Description 短链接分组接口层
 * @auther j2-yizhiyang
 * @date 2023/12/21 16:09
 */
public interface GroupService extends IService<GroupDO> {
    /**
    *@Description: 新增短链接
    *@Param: [groupName]
    *@Author: yun
    *@Date: 2023/12/21
    *@return: void
    *
    */
    void saveGroup(String groupName);
    /**
    *@Description: 新增短链接分组
    *@Param: [username, groupName]
    *@Author: yun
    *@Date: 2023/12/21
    *@return: void
    *
    */
    void saveGroup(String username, String groupName);
    /** 
    *@Description: 查询用户短链接分组集合
    *@Param: []
    *@Author: yun
    *@Date: 2023/12/21
    *@return: java.util.List<com.shortlink.admin.dto.resp.ShortLinkGroupRespDTO>
    *
    */
    List<ShortLinkGroupRespDTO> listGroup();
    /**
    *@Description: 更新短链接分组
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/21
    *@return: void
    *
    */
    void updateGroup(ShortLinkGroupUpdateReqDTO requestParam);
    /**
    *@Description: 短链接分组排序
    *@Param: [requestParam:短链接分组排序参数]
    *@Author: yun
    *@Date: 2023/12/21
    *@return: void
    *
    */
    void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam);
    /**
    *@Description: 删除分组短链接
    *@Param: [gid]
    *@Author: yun
    *@Date: 2023/12/23
    *@return: void
    *
    */
    void deleteGroup(String gid);

}
