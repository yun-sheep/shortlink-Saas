package com.shorlink.project.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 布隆过滤器配置
 * @auther j2-yizhiyang
 * @date 2023/12/24 16:18
 */
@Configuration
public class RBloomFilterConfiguration {
    /**
    *@Description: 防止短链接创建查询数据库的布隆过滤器
    *@Param: [redissonClient]
    *@Author: yun
    *@Date: 2023/12/24
    *@return: org.redisson.api.RBloomFilter<java.lang.String>
    *
    */
    @Bean
    public RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<String> cachePenetrationBloomFilter = redissonClient.getBloomFilter("userRegisterCachePenetrationBloomFilter");
        cachePenetrationBloomFilter.tryInit(100000000L, 0.001);
        return cachePenetrationBloomFilter;
    }
}
