package com.shorlink.project.common.constant;

/**
 * @Description 短链接后台管理Redis缓存常量类
 * @auther j2-yizhiyang
 * @date 2023/12/19 14:51
 */
public class RedisCacheConstant {
    /**
     * 用户注册分布式锁
     */
    public static final String LOCK_USER_REGISTER_KEY = "short-link:lock_user-register:";

    /**
     * 分组创建分布式锁
     */
    public static final String LOCK_GROUP_CREATE_KEY = "short-link:lock_group-create:%s";
}
