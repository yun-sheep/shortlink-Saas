package com.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * @Description 返回用户的无脱敏信息
 * @auther j2-yizhiyang
 * @date 2023/12/21 13:59
 */
@Data
public class UserActualRespDTO {
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
    private String phone;

    /**
     * 邮箱
     */
    private String mail;
}
