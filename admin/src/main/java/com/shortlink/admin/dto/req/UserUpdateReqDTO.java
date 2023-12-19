package com.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @Description 用户更新请求参数
 * @auther j2-yizhiyang
 * @date 2023/12/19 16:54
 */
@Data
public class UserUpdateReqDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;
}
