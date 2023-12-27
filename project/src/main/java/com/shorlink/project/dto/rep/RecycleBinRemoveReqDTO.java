package com.shorlink.project.dto.rep;

import lombok.Data;

/**
 * @Description 回收站移除功能
 * @auther j2-yizhiyang
 * @date 2023/12/27 15:06
 */
@Data
public class RecycleBinRemoveReqDTO {
    /**
     * 分组标识
     */
    private String gid;

    /**
     * 全部短链接
     */
    private String fullShortUrl;
}
