package com.shortlink.admin.controller;

import com.shortlink.admin.common.convention.result.Result;
import com.shortlink.admin.dto.req.UserLoginReqDTO;
import com.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.shortlink.admin.dto.resp.UserLoginRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 短链接用户Controller
 * @auther j2-yizhiyang
 * @date 2023/12/19 16:02
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    /**
    *@Description: 用户注册
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/19
    *@return: com.shortlink.admin.common.convention.result.Result<java.lang.Void>
    *
    */
    @PostMapping("/api/short-link/admin/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam){
        return null;
    }
    /**
    *@Description: 用户登录
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/19
    *@return: com.shortlink.admin.common.convention.result.Result<com.shortlink.admin.dto.resp.UserLoginRespDTO>
    *
    */
    @PostMapping("/api/short-link/admin/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam){
        return null;
    }


}
