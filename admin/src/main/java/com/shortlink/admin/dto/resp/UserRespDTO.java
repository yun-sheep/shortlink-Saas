package com.shortlink.admin.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shortlink.admin.common.serialize.PhoneDesensitizationSerializer;

/**
 * @Description 用户返回参数响应
 * @auther j2-yizhiyang
 * @date 2023/12/19 17:00
 */
public class UserRespDTO {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    private String phone;

    /**
     * 邮箱
     */
    private String mail;
}
