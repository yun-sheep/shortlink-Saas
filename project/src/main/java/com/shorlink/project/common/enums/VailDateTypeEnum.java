package com.shorlink.project.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @Description 有效期类型
 * @auther j2-yizhiyang
 * @date 2023/12/26 11:06
 */
@RequiredArgsConstructor
public enum VailDateTypeEnum {
    /**
     * 永久有效期
     */
    PERMANENT(0),

    /**
     * 自定义有效期
     */
    CUSTOM(1);

    @Getter
    private final int type;
}
