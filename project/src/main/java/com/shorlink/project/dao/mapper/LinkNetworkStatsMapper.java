package com.shorlink.project.dao.mapper;

import com.shorlink.project.dao.entity.LinkNetworkStatsDO;
import com.shorlink.project.dto.rep.ShortLinkStatsReqDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description 访问网络监控持久层
 * @auther j2-yizhiyang
 * @date 2023/12/29 22:08
 */
public interface LinkNetworkStatsMapper {
    /**
    *@Description: 记录访问设备监控数据
    *@Param: [linkNetworkStatsDO]
    *@Author: yun
    *@Date: 2023/12/29
    *@return: void
    *
    */
    @Insert("INSERT INTO t_link_network_stats (full_short_url, gid, date, cnt, network, create_time, update_time, del_flag) " +
            "VALUES( #{linkNetworkStats.fullShortUrl}, #{linkNetworkStats.gid}, #{linkNetworkStats.date}, #{linkNetworkStats.cnt}, #{linkNetworkStats.network}, NOW(), NOW(), 0) " +
            "ON DUPLICATE KEY UPDATE cnt = cnt +  #{linkNetworkStats.cnt};")
    void shortLinkNetworkState(@Param("linkNetworkStats") LinkNetworkStatsDO linkNetworkStatsDO);

    /**
    *@Description: 根据短链接获取指定日期内访问网络监控数据
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/29
    *@return: java.util.List<com.shorlink.project.dao.entity.LinkNetworkStatsDO>
    *
    */
    @Select("SELECT " +
            "    network, " +
            "    SUM(cnt) AS cnt " +
            "FROM " +
            "    t_link_network_stats " +
            "WHERE " +
            "    full_short_url = #{param.fullShortUrl} " +
            "    AND gid = #{param.gid} " +
            "    AND date BETWEEN #{param.startDate} and #{param.endDate} " +
            "GROUP BY " +
            "    full_short_url, gid, network;")
    List<LinkNetworkStatsDO> listNetworkStatsByShortLink(@Param("param") ShortLinkStatsReqDTO requestParam);

}
