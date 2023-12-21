package com.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shortlink.admin.dao.entity.UserDO;
import com.shortlink.admin.dto.req.UserLoginReqDTO;
import com.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.shortlink.admin.dto.resp.UserRespDTO;

/**
 * @Description 用户接口层
 * @auther j2-yizhiyang
 * @date 2023/12/19 17:10
 */
public interface UserService extends IService<UserDO> {
    /**
    *@Description: 注册用户
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/19
    *@return: void
    *
    */
    void register(UserRegisterReqDTO requestParam);
    /** 
    *@Description: 布隆过滤器中查询用户是否存在
    *@Param: [username]
    *@Author: yun
    *@Date: 2023/12/19
    *@return: java.lang.Boolean
    *
    */
    Boolean hasUsername(String username);
    /**
     * @Description: 用户登录
     * @Param: [requestParam]
     * @Author: yun
     * @Date: 2023/12/19
     * @return: com.shortlink.admin.dto.req.UserLoginReqDTO，用户登录返回参数Token
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);
    /**
    *@Description: 根据用户名查询用户信息
    *@Param: [username]
    *@Author: yun
    *@Date: 2023/12/19
    *@return: com.shortlink.admin.dto.resp.UserRespDTO
    *
    */
    UserRespDTO getUserByUsername(String username);
    /**
    *@Description: 用户登出
    *@Param: [username, token]
    *@Author: yun
    *@Date: 2023/12/21
    *@return: void
    *
    */
    void logout(String username, String token);
    /**
    *@Description: 检查是否登录
    *@Param: [username, token]
    *@Author: yun
    *@Date: 2023/12/21
    *@return: java.lang.Boolean
    *
    */
    Boolean checkLogin(String username, String token);
    /**
    *@Description: 更新用户数据
    *@Param: [requestParam]
    *@Author: yun
    *@Date: 2023/12/21
    *@return: void
    *
    */
    void  update(UserUpdateReqDTO requestParam);

}
