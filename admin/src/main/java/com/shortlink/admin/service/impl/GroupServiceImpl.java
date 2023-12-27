package com.shortlink.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shortlink.admin.common.biz.user.UserContext;
import com.shortlink.admin.common.convention.exception.ClientException;
import com.shortlink.admin.common.convention.result.Result;
import com.shortlink.admin.dao.entity.GroupDO;
import com.shortlink.admin.dao.mapper.GroupMapper;
import com.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.shortlink.admin.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.shortlink.admin.service.GroupService;
import com.shortlink.admin.toolkit.RandomGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.shortlink.admin.common.constant.RedisCacheConstant.LOCK_GROUP_CREATE_KEY;

/**
 * @Description 短链接分组接口实现层
 * @auther j2-yizhiyang
 * @date 2023/12/21 16:09
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    private final RedissonClient redissonClient;

    @Value("${short-link.group.max-num}")
    private Integer groupMaxNum;
    @Override
    public void saveGroup(String groupName) {
        saveGroup(UserContext.getUsername(),groupName);

    }

    @Override
    public void saveGroup(String username, String groupName) {
        //1、获取分布式锁
        RLock lock = redissonClient.getLock(String.format(LOCK_GROUP_CREATE_KEY, username));
        lock.lock();
        try{
            //2、查出当前用户拥有的分组数
            LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                    .eq(GroupDO::getUsername, username)
                    .eq(GroupDO::getDelFlag, 0);
            List<GroupDO> groupDOList = baseMapper.selectList(queryWrapper);
            if (CollUtil.isNotEmpty(groupDOList) && groupDOList.size() == groupMaxNum) {
                throw new ClientException(String.format("已超出最大分组数：%d", groupMaxNum));
            }
            //3、生成gid
            String gid;
            do {
                gid = RandomGenerator.generateRandom();
            }while (!hasGid(username, gid));
            //4、插入
            GroupDO groupDO = GroupDO.builder()
                    .gid(gid)
                    .sortOrder(0)
                    .username(username)
                    .name(groupName)
                    .build();
            baseMapper.insert(groupDO);
        }finally {
            lock.unlock();
        }

    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername,UserContext.getUsername())
                .eq(GroupDO::getDelFlag,0)
                .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> groupDOList =  baseMapper.selectList(queryWrapper);
        //TODO 查询短链接分组有多少个短链接
        //TODO 查询分组短链接返回值
        //Result<List<ShortLinkGroupCountQueryRespDTO>>


        return null;
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO requestParam) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, requestParam.getGid())
                .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setName(requestParam.getName());
        baseMapper.update(groupDO, updateWrapper);

    }

    @Override
    public void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam) {

    }

    @Override
    public void deleteGroup(String gid) {
        //TODO
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setDelFlag(1);
        baseMapper.update(groupDO, updateWrapper);
    }
    /**
    *@Description: 查询是否有已经存在gid
    *@Param: [username, gid]
    *@Author: yun
    *@Date: 2023/12/23
    *@return: boolean
    *
    */
    private boolean hasGid(String username, String gid){
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                .eq(GroupDO::getUsername, Optional.ofNullable(username).orElse(UserContext.getUsername()));
        GroupDO hasGroupFlag = baseMapper.selectOne(queryWrapper);
        return hasGroupFlag == null;
    }

}
