package com.icepoint.framework.web.ui.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.web.ui.entity.UiLogoConfig;
import com.icepoint.framework.web.ui.mapper.UiLogoConfigMapper;
import com.icepoint.framework.web.ui.service.UiLogoConfigService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UiLogoConfigServiceImpl implements UiLogoConfigService {
    @Resource
    private UiLogoConfigMapper mapper;

    @Override
    public List<UiLogoConfig> get(Long appId, Long ownerId) {
        Assert.isTrue(!ObjectUtils.isEmpty(appId), "app不存在");
        Assert.isTrue(!ObjectUtils.isEmpty(ownerId), "租户不存在");
        LambdaQueryWrapper<UiLogoConfig> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UiLogoConfig::getAppId, appId)
                .eq(UiLogoConfig::getOwnerId, ownerId);
        List<UiLogoConfig> all = mapper.findAll(lambdaQueryWrapper);
        return all;
    }

    @Override
    public Boolean updateUiLogoConfig(UiLogoConfig uiLogoConfig) {
        if (ObjectUtils.isEmpty(uiLogoConfig.getId())) {
            return mapper.insert(uiLogoConfig) > 0;
        }
        return mapper.update(uiLogoConfig) > 0;
    }

    public UiLogoConfig getById(Long id) {
        UiLogoConfig uiLogoConfig = mapper.findById(id).
                orElseThrow(() -> new IllegalArgumentException("id错误"));
        return uiLogoConfig;
    }

    @Override
    public Boolean deleteById(Long id) {
        return mapper.deleteById(id) > 0;
    }

    @Override
    public Page<UiLogoConfig> page(UiLogoConfig uiLogoConfig, Pageable pageable) {
        Assert.isTrue(!ObjectUtils.isEmpty(uiLogoConfig), "参数有误");
        Assert.isTrue(!ObjectUtils.isEmpty(uiLogoConfig.getOwnerId()), "ownerId不存在");
        Assert.isTrue(!ObjectUtils.isEmpty(uiLogoConfig.getAppId()), "appId不存在");
        Map<String, Object> map = MapUtils.objectToLineMap(uiLogoConfig);
        QueryWrapper<UiLogoConfig> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);
        return mapper.findAll(wrapper,pageable);
    }

    @Override
    public UiLogoConfig queryById(Long id) {
        return this.mapper.findById(id).orElseThrow(() -> new IllegalArgumentException("参数错误"));
    }
}
