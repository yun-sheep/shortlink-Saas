package com.shorlink.project.service;

/**
 * @Description URL 标题接口层
 * @auther j2-yizhiyang
 * @date 2023/12/26 21:57
 */
public interface UrlTitleService {
    /**
    *@Description: 获取url链接的标题
    *@Param: [url]
    *@Author: yun
    *@Date: 2023/12/26
    *@return: java.lang.String
    *
    */
    String getTitleByUrl(String url);
}
