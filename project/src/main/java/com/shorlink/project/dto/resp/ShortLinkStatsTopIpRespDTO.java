package com.shorlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 短链接高频访问IP监控数据响应
 * @auther j2-yizhiyang
 * @date 2023/12/29 21:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsTopIpRespDTO {
    /**
     * 统计
     */
    private Integer cnt;

    /**
     * IP
     */
    private String ip;
}
