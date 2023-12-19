package com.shortlink.admin.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description 用户登录返回值
 * @auther j2-yizhiyang
 * @date 2023/12/19 17:00
 */
@Data
@AllArgsConstructor
public class UserLoginRespDTO {
    String token;
}
