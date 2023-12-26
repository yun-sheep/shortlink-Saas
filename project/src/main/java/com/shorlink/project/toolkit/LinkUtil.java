package com.shorlink.project.toolkit;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.Optional;

import static com.shorlink.project.common.constant.ShortLinkConstant.DEFAULT_CACHE_VALID_TIME;

/**
 * @Description 短链接工具类
 * @auther j2-yizhiyang
 * @date 2023/12/26 19:45
 */
public class LinkUtil {
    
    /** 
    *@Description: 获取短链接缓存有效时间
    *@Param: [validDate]
    *@Author: yun
    *@Date: 2023/12/26
    *@return: long
    *
    */
    public static long getLinkCacheValidTime(Date validDate) {
        return Optional.ofNullable(validDate)
                .map(each -> DateUtil.between(new Date(), each, DateUnit.MS))
                .orElse(DEFAULT_CACHE_VALID_TIME);
    }
}
