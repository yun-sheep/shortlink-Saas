package com.shorlink.project.dao.mapper;

import com.shorlink.project.dao.entity.LinkDeviceStatsDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @auther j2-yizhiyang
 * @date 2023/12/28 20:54
 */
public interface LinkDeviceStatsMapper {
    /**
    *@Description:  记录访问设备监控数据
    *@Param: [linkDeviceStatsDO]
    *@Author: yun
    *@Date: 2023/12/28
    *@return: void
    *
    */
    @Insert("INSERT INTO t_link_device_stats (full_short_url, gid, date, cnt, device, create_time, update_time, del_flag) " +
            "VALUES( #{linkDeviceStats.fullShortUrl}, #{linkDeviceStats.gid}, #{linkDeviceStats.date}, #{linkDeviceStats.cnt}, #{linkDeviceStats.device}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE cnt = cnt +  #{linkDeviceStats.cnt};")
    void shortLinkDeviceState(@Param("linkDeviceStats") LinkDeviceStatsDO linkDeviceStatsDO);
}
