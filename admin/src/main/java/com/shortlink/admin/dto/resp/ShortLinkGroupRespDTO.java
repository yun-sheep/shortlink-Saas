package com.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * @Description 短链接分组返回实体对象
 * @auther j2-yizhiyang
 * @date 2023/12/21 16:13
 */
@Data
public class ShortLinkGroupRespDTO {
    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;

    /**
     * 分组排序
     */
    private Integer sortOrder;

    /**
     * 分组下短链接数量
     */
    private Integer shortLinkCount;
}
