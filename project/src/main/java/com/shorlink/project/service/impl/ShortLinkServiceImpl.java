package com.shorlink.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shorlink.project.common.convention.exception.ClientException;
import com.shorlink.project.common.convention.exception.ServiceException;
import com.shorlink.project.common.enums.VailDateTypeEnum;
import com.shorlink.project.dao.entity.ShortLinkDO;
import com.shorlink.project.dao.mapper.ShortLinkMapper;
import com.shorlink.project.dto.rep.ShortLinkCreateReqDTO;
import com.shorlink.project.dto.rep.ShortLinkUpdateReqDTO;
import com.shorlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.shorlink.project.service.ShortLinkService;
import com.shorlink.project.toolkit.HashUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @Description 短链接接口服务层
 * @auther j2-yizhiyang
 * @date 2023/12/24 10:26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {
    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;
    @Value("${short-link.domain.default}")
    private String createShortLinkDefaultDomain;
    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        //1、生成短链接后缀
        String shortLinkSuffix = generateSuffix(requestParam);
        //2、生成完整短链接
        String fullShortUrl = new StringBuilder()
                .append(requestParam.getDomain())
                .append("/").append(shortLinkSuffix)
                .toString();
        //3、生成短链接DO
        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .createdType(requestParam.getCreatedType())
                .validDateType(requestParam.getValidDateType())
                .validDate(requestParam.getValidDate())
                .describe(requestParam.getDescribe())
                .shortUri(shortLinkSuffix)
                .enableStatus(0)
                .totalPv(0)
                .totalUv(0)
                .totalUip(0)
                .delTime(0L)
                .fullShortUrl(fullShortUrl)
                .build();
        //4、插入数据库
        try {
            baseMapper.insert(shortLinkDO);
        }catch (DuplicateKeyException duplicateKeyException){
            //5、再次查询判断数据库中有没有生成的短链接
            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl, fullShortUrl);
            ShortLinkDO hasShortLinkDO = baseMapper.selectOne(queryWrapper);
            if (hasShortLinkDO != null) {
                log.warn("短链接：{} 重复入库", fullShortUrl);
                throw new ServiceException("短链接生成重复");
            }
        }
        //不存在就添加到布隆过滤器中
        shortUriCreateCachePenetrationBloomFilter.add(fullShortUrl);
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl("http://" + shortLinkDO.getFullShortUrl())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }
    //要开一个事务
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateShortLink(ShortLinkUpdateReqDTO requestParam) {
        //1、查找短链接
        LambdaQueryWrapper<ShortLinkDO>  queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getOriginGid())
                .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDO::getDelFlag, 0)
                .eq(ShortLinkDO::getEnableStatus, 0);
        ShortLinkDO hasShortLinkDO = baseMapper.selectOne(queryWrapper);
        if(hasShortLinkDO==null){
            throw new ClientException("短链接记录不存在");
        }
        //如果是修改分组就要先删除原来表中的信息再添加.否则直接更新即可
        if(Objects.equals(requestParam.getGid(),hasShortLinkDO.getGid())){
            LambdaUpdateWrapper<ShortLinkDO> updateWrapper = Wrappers.lambdaUpdate(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                    .eq(ShortLinkDO::getGid, requestParam.getGid())
                    .eq(ShortLinkDO::getDelFlag, 0)
                    .eq(ShortLinkDO::getEnableStatus, 0)
                    .set(Objects.equals(requestParam.getValidDateType(), VailDateTypeEnum.PERMANENT.getType()), ShortLinkDO::getValidDate, null);
            ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                    .domain(hasShortLinkDO.getDomain())
                    .shortUri(hasShortLinkDO.getShortUri())
                    .favicon(hasShortLinkDO.getFavicon())
                    .createdType(hasShortLinkDO.getCreatedType())
                    .gid(requestParam.getGid())
                    .originUrl(requestParam.getOriginUrl())
                    .describe(requestParam.getDescribe())
                    .validDateType(requestParam.getValidDateType())
                    .validDate(requestParam.getValidDate())
                    .build();
            baseMapper.update(shortLinkDO, updateWrapper);
        }
        else {
            LambdaUpdateWrapper<ShortLinkDO> linkUpdateWrapper = Wrappers.lambdaUpdate(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                    .eq(ShortLinkDO::getGid, hasShortLinkDO.getGid())
                    .eq(ShortLinkDO::getDelFlag, 0)
                    .eq(ShortLinkDO::getDelTime, 0L)
                    .eq(ShortLinkDO::getEnableStatus, 0);
            ShortLinkDO delShortLinkDO = ShortLinkDO.builder()
                    .delTime(System.currentTimeMillis())
                    .build();
            ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                    .domain(createShortLinkDefaultDomain)
                    .originUrl(requestParam.getOriginUrl())
                    .gid(requestParam.getGid())
                    .createdType(hasShortLinkDO.getCreatedType())
                    .validDateType(requestParam.getValidDateType())
                    .validDate(requestParam.getValidDate())
                    .describe(requestParam.getDescribe())
                    .shortUri(hasShortLinkDO.getShortUri())
                    .enableStatus(hasShortLinkDO.getEnableStatus())
                    .totalPv(hasShortLinkDO.getTotalPv())
                    .totalUv(hasShortLinkDO.getTotalUv())
                    .totalUip(hasShortLinkDO.getTotalUip())
                    .fullShortUrl(hasShortLinkDO.getFullShortUrl())
                    .delTime(0L)
                    .build();
            baseMapper.insert(shortLinkDO);
        }
    }

    private String generateSuffix(ShortLinkCreateReqDTO requestParam){
        int customGenerateCount = 0;
        String shorUri;
        while (true){
            if (customGenerateCount>10){
                throw  new ServiceException("短链接频繁生成，请稍后再试");
            }
            String originUrl = requestParam.getOriginUrl();
            //加上一个当前时间，减少冲突
            originUrl += System.currentTimeMillis();
            shorUri = HashUtil.hashToBase62(originUrl);
            if (!shortUriCreateCachePenetrationBloomFilter.contains(requestParam.getDomain() + "/" + shorUri)) {
                break;
            }
            customGenerateCount++;

        }
        return shorUri;
    }
}
