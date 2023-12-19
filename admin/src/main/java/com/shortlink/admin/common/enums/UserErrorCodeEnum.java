package com.shortlink.admin.common.enums;

import com.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * @Description 用户错误码
 * @auther j2-yizhiyang
 * @date 2023/12/19 15:02
 */
public enum UserErrorCodeEnum implements IErrorCode {
    USER_TOKEN_FAIL("A000200", "用户Token验证失败"),

    USER_NULL("B000200", "用户记录不存在"),

    USER_NAME_EXIST("B000201", "用户名已存在"),

    USER_EXIST("B000202", "用户记录已存在"),

    USER_SAVE_ERROR("B000203", "用户记录新增失败");

    private final String code;

    private final String message;
    UserErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
