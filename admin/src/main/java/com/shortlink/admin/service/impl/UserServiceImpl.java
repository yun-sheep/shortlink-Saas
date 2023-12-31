package com.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shortlink.admin.common.convention.exception.ClientException;
import com.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.shortlink.admin.dao.entity.UserDO;
import com.shortlink.admin.dao.mapper.UserMapper;
import com.shortlink.admin.dto.req.UserLoginReqDTO;
import com.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.shortlink.admin.dto.resp.UserRespDTO;
import com.shortlink.admin.service.GroupService;
import com.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.shortlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static com.shortlink.admin.common.enums.UserErrorCodeEnum.*;

/**
 * @Description
 * @auther j2-yizhiyang
 * @date 2023/12/19 17:15
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    //布隆过滤器
    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    //redission
    private final RedissonClient redissonClient;
    //redis操作类
    private final StringRedisTemplate stringRedisTemplate;
    private final GroupService groupService;
    @Override
    public void register(UserRegisterReqDTO requestParam) {
        //1、判断用户是否存在（先用一层布隆过滤器）
        if(!hasUsername(requestParam.getUsername())){
            throw new ClientException(USER_NAME_EXIST);
        }
        //2、获取分布式锁(防止恶意插入）
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        //3、插入到数据库
        try {
            //再次判断是否获得锁
            if(lock.tryLock()){
                try{
                    int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
                    if(inserted<1){
                        throw new ClientException(USER_SAVE_ERROR);
                    }
                }catch (DuplicateKeyException es){
                    throw new ClientException(USER_EXIST);
                }
                //把新注册的用户添加到布隆过滤器
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
                //一旦注册就有一个默认分组
                groupService.saveGroup(requestParam.getUsername(), "默认分组");
                return;
            }
        }finally {
            lock.unlock();
        }

    }

    @Override
    public Boolean hasUsername(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        //1、在mysql中查找
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class);
        queryWrapper.eq(UserDO::getUsername,requestParam.getUsername())
                .eq(UserDO::getPassword,requestParam.getPassword())
                .eq(UserDO::getDelFlag,0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if(userDO == null){
            throw new ClientException("用户不存在");
        }
        //2、存在redis中（为什么要这样写）
        Map<Object ,Object> hasLoginMap = stringRedisTemplate.opsForHash().entries("login_" + requestParam.getUsername());
        //3、如果不是空的话，说明已经登录过了，直接返回这个token
        if(CollUtil.isNotEmpty(hasLoginMap)){
            String token = hasLoginMap.keySet().stream()
                    .findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new ClientException("用户登录错误"));
            return new UserLoginRespDTO(token);
        }
        /**
         * Hash
         * Key：login_用户名
         * Value：
         *  Key：token标识
         *  Val：JSON 字符串（用户信息）
         */
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().put("login_" + requestParam.getUsername(), uuid, JSON.toJSONString(userDO));
        stringRedisTemplate.expire("login_" + requestParam.getUsername(), 30L, TimeUnit.MINUTES);
        return new UserLoginRespDTO(uuid);

    }

    @Override
    public UserRespDTO getUserByUsername(String username) {
        //1、查找用户
       LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
               .eq(UserDO::getUsername,username);
       UserDO userDO = baseMapper.selectOne(queryWrapper);
       if(userDO == null){
           throw new ClientException(USER_NULL);
       }
       UserRespDTO userRespDTO = new UserRespDTO();
       BeanUtils.copyProperties(userDO,userRespDTO);
       return userRespDTO;
    }

    @Override
    public void logout(String username, String token) {
        //判断是否已经登录
        if (checkLogin(username,token)){
            stringRedisTemplate.delete("login_"+username);
            return;
        }
        throw  new ClientException("用户Token不存在或者用户未登录");
    }

    @Override
    public Boolean checkLogin(String username, String token) {
        return stringRedisTemplate.opsForHash().get("login_" + username, token)!=null;
    }

    @Override
    public void update(UserUpdateReqDTO requestParam) {
        //TODO 检查是否是当前用户
        //直接更新到mysql中
        LambdaQueryWrapper<UserDO> userDOLambdaQueryWrapper = new LambdaQueryWrapper<>(UserDO.class)
                .eq(UserDO::getUsername,requestParam);
        baseMapper.update(BeanUtil.toBean(requestParam, UserDO.class),userDOLambdaQueryWrapper);
    }

}
