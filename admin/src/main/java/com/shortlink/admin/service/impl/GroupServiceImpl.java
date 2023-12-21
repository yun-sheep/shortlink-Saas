package com.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shortlink.admin.dao.entity.GroupDO;
import com.shortlink.admin.dao.mapper.GroupMapper;
import com.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @Description 短链接分组接口实现层
 * @auther j2-yizhiyang
 * @date 2023/12/21 16:09
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    @Override
    public void saveGroup(String groupName) {
        //

    }

    @Override
    public void saveGroup(String username, String groupName) {
        //TODO 当前用户新建分组

    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {

        return null;
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO requestParam) {

    }

    @Override
    public void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam) {

    }

    @Override
    public boolean save(GroupDO entity) {
        return super.save(entity);
    }

}
