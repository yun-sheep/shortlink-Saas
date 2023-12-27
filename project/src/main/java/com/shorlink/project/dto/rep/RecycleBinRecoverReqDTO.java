package com.shorlink.project.dto.rep;

import lombok.Data;

/**
 * @Description  回收站恢复功能
 * @auther j2-yizhiyang
 * @date 2023/12/27 15:01
 */
@Data
public class RecycleBinRecoverReqDTO {
    /**
     * 分组标识（可以传分组表示吗？？)
     */
    private String gid;

    /**
     * 全部短链接
     */
    private String fullShortUrl;
}
