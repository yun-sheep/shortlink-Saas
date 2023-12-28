package com.shorlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shorlink.project.dao.entity.LinkOsStatsDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @Description 操作系统统计访问持久层
 * @auther j2-yizhiyang
 * @date 2023/12/28 21:00
 */
public interface LinkOsStatsMapper extends BaseMapper<LinkOsStatsDO> {
    /**
    *@Description: 记录操作系统统计数据
    *@Param: [linkOsStatsDO]
    *@Author: yun
    *@Date: 2023/12/28
    *@return: void
    *
    */
    @Insert("INSERT INTO t_link_os_stats (full_short_url, gid, date, cnt, os, create_time, update_time, del_flag) " +
            "VALUES( #{linkOsStats.fullShortUrl}, #{linkOsStats.gid}, #{linkOsStats.date}, #{linkOsStats.cnt}, #{linkOsStats.os}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE cnt = cnt +  #{linkOsStats.cnt};")
    void shortLinkOsState(@Param("linkOsStats") LinkOsStatsDO linkOsStatsDO);
}
