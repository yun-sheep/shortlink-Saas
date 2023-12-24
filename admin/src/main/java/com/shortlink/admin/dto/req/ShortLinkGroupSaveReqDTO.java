package com.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @Description 短链接分组创建参数
 * @auther j2-yizhiyang
 * @date 2023/12/23 16:21
 */
@Data
public class ShortLinkGroupSaveReqDTO {
    /**
     * 分组名
     */
    private String name;
}
