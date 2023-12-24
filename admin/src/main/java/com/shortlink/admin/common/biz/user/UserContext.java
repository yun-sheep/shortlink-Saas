package com.shortlink.admin.common.biz.user;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Optional;

/**
 * @Description 用户上下文
 * @auther j2-yizhiyang
 * @date 2023/12/23 15:50
 */
public final class UserContext {
    /**
     * <a href="https://github.com/alibaba/transmittable-thread-local" />
     */
    private static final ThreadLocal<UserInfoDTO> USER_THREAD_LOCAL = new TransmittableThreadLocal<>();
    /** 
    *@Description: 设置用户至上下文
    *@Param: [user]
    *@Author: yun
    *@Date: 2023/12/23
    *@return: void
    *
    */
    public static void setUser(UserInfoDTO user){
        USER_THREAD_LOCAL.set(user);
    }
    /** 
    *@Description: 获取上下文用户ID
    *@Param: []
    *@Author: yun
    *@Date: 2023/12/23
    *@return: java.lang.String
    *
    */
    public static String getUserId(){
        UserInfoDTO userInfoDTO = USER_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfoDTO).map(UserInfoDTO::getUserId).orElse(null);
    }
    /** 
    *@Description: 取上下文中用户名称
    *@Param: []
    *@Author: yun
    *@Date: 2023/12/23
    *@return: java.lang.String
    *
    */
    public static String getUsername() {
        UserInfoDTO userInfoDTO = USER_THREAD_LOCAL.get();
        return Optional.ofNullable(userInfoDTO).map(UserInfoDTO::getUsername).orElse(null);
    }
    /** 
    *@Description: 清理用户上下文（一定要记得，不然内存会泄漏）
    *@Param: []
    *@Author: yun
    *@Date: 2023/12/24
    *@return: void
    *
    */
    public static void removeUser() {
        USER_THREAD_LOCAL.remove();
    }
}
