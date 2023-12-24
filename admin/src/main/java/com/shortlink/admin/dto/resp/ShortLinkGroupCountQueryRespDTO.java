package com.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * @Description 短链接分组查询返回参数
 * @auther j2-yizhiyang
 * @date 2023/12/23 16:12
 */
@Data
public class ShortLinkGroupCountQueryRespDTO {
    /**
     * 分组标识
     */
    private String gid;

    /**
     * 短链接数量
     */
    private Integer shortLinkCount;
}
