package com.shorlink.project.dto.rep;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 回收站保存功能
 * @auther j2-yizhiyang
 * @date 2023/12/26 23:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecycleBinSaveReqDTO {
    /**
     * 分组标识
     */
    private String gid;

    /**
     * 全部短链接
     */
    private String fullShortUrl;

}
