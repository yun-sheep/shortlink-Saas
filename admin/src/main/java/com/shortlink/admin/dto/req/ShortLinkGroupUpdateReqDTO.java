package com.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @Description 短链接分组修改参数
 * @auther j2-yizhiyang
 * @date 2023/12/21 20:18
 */
@Data
public class ShortLinkGroupUpdateReqDTO {
    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名
     */
    private String name;
}
