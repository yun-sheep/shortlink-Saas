package com.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.shortlink.admin.common.convention.result.Result;
import com.shortlink.admin.common.convention.result.Results;
import com.shortlink.admin.dto.req.UserLoginReqDTO;
import com.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.shortlink.admin.dto.resp.UserActualRespDTO;
import com.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.shortlink.admin.dto.resp.UserRespDTO;
import com.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 短链接用户Controller
 * @auther j2-yizhiyang
 * @date 2023/12/19 16:02
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
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
        userService.register(requestParam);
        return Results.success();
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
        return Results.success(userService.login(requestParam));
    }
    /**
    *@Description: 根据用户名查询用户信息
    *@Param: [username]
    *@Author: yun
    *@Date: 2023/12/21
    *@return: com.shortlink.admin.common.convention.result.Result<com.shortlink.admin.dto.resp.UserRespDTO>
    *
    */
    @GetMapping("/api/short-link/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }
    /** 
    *@Description: 返回无脱敏信息
    *@Param: [username]
    *@Author: yun
    *@Date: 2023/12/21
    *@return: com.shortlink.admin.common.convention.result.Result<com.shortlink.admin.dto.resp.UserActualRespDTO>
    *
    */
    @GetMapping("/api/short-link/admin/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username){
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }
    /** 
    *@Description: 查询用户名是否存在
    *@Param: [username]
    *@Author: yun
    *@Date: 2023/12/21
    *@return: com.shortlink.admin.common.convention.result.Result<java.lang.Boolean>
    *
    */
    @GetMapping("/api/short-link/admin/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }
    /** 
    *@Description: 更新用户
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/21
    *@return: com.shortlink.admin.common.convention.result.Result<java.lang.Void>
    *
    */
    @PutMapping("/api/short-link/admin/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam) {
        userService.update(requestParam);
        return Results.success();
    }
    /** 
    *@Description: 用户退出登录
    *@Param: [username, token]
    *@Author: yun
    *@Date: 2023/12/21
    *@return: com.shortlink.admin.common.convention.result.Result<java.lang.Void>
    *
    */
    @DeleteMapping("/api/short-link/admin/v1/user/logout")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("token") String token) {
        userService.logout(username, token);
        return Results.success();
    }


}
