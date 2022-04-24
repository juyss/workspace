package com.icepoint.framework.web.ui.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.web.ui.entity.UiSkinConfig;
import com.icepoint.framework.web.ui.mapper.UiSkinConfigMapper;
import com.icepoint.framework.web.ui.service.UiSkinConfigService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (UiSkinConfig)表服务实现类
 *
 * @author makejava
 * @since 2021-06-18 16:31:56
 */
@Service("uiSkinConfigService")
public class UiSkinConfigServiceImpl implements UiSkinConfigService {
    @Resource
    private UiSkinConfigMapper mapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UiSkinConfig queryById(Long id) {
        return this.mapper.findById(id).orElseThrow(() -> new IllegalArgumentException("参数错误"));
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public UiSkinConfig get(Long appId, Long ownerId) {
        Assert.isTrue(!ObjectUtils.isEmpty(appId), "app不存在");
        Assert.isTrue(!ObjectUtils.isEmpty(ownerId), "租户不存在");
        LambdaQueryWrapper<UiSkinConfig> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UiSkinConfig::getAppId, appId)
                .eq(UiSkinConfig::getOwnerId, ownerId);
        return mapper.findOne(lambdaQueryWrapper).orElseThrow(()->new IllegalArgumentException("配置出错"));
    }

    /**
     * 修改数据
     *
     * @param uiSkinConfig 实例对象
     * @return 实例对象
     */


    @Override
    public Boolean update(UiSkinConfig uiSkinConfig) {
        if (ObjectUtils.isEmpty(uiSkinConfig.getId())) {
            return mapper.insert(uiSkinConfig) > 0;
        }
        return mapper.update(uiSkinConfig) > 0;
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.mapper.deleteById(id) > 0;
    }

    @Override
    public Page<UiSkinConfig> page(Map<String, Object> map, Pageable pageable) {
        Assert.isTrue(!ObjectUtils.isEmpty(map), "参数有误");
        Assert.isTrue(!ObjectUtils.isEmpty(map.get("ownerId")), "ownerId不存在");
        Assert.isTrue(!ObjectUtils.isEmpty(map.get("appId")), "appId不存在");
        QueryWrapper<UiSkinConfig> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);
        return mapper.findAll(wrapper, pageable);
    }

    @Override
    public Boolean delete(Long id) {
        return mapper.deleteById(id) > 0;
    }
}
