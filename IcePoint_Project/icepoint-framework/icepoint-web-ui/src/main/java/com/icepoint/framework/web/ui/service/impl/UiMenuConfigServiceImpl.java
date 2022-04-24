package com.icepoint.framework.web.ui.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.data.domain.TreeNode;
import com.icepoint.framework.web.ui.entity.*;
import com.icepoint.framework.web.ui.mapper.UiMenuConfigMapper;
import com.icepoint.framework.web.ui.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Administrator
 */
@Service
public class UiMenuConfigServiceImpl implements UiMenuConfigService {

    @Resource
    private UiMenuConfigMapper mapper;

    @Resource
    private UiMenuService uiMenuService;

    @Resource
    private UiMenuTemplateService uiMenuTemplateService;

    @Resource
    private UiSkinConfigService uiSkinConfigService;

    @Resource
    private UiLogoConfigService uiLogoConfigService;

    @Override
    public UiMenuConfig get(Long appId, Long ownerId) {
        Assert.isTrue(!ObjectUtils.isEmpty(appId), "app不存在");
        Assert.isTrue(!ObjectUtils.isEmpty(ownerId), "租户不存在");
        LambdaQueryWrapper<UiMenuConfig> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(UiMenuConfig::getAppId, appId);
        lambdaQueryWrapper.eq(UiMenuConfig::getOwnerId,ownerId);
         Optional<UiMenuConfig> one = mapper.findOne(lambdaQueryWrapper);
         one.orElseThrow(()->new IllegalArgumentException("未找到配置"));
        return one.get();
    }

    @Override
    public Map<String, Object> allConfig(Long appId, Long ownerId,Integer termType) {
        List<TreeNode<UiMenu>> treeList = uiMenuService.getTreeList(null, termType, 1, appId, ownerId);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("menus", treeList);
        UiMenuTemplate uiMenuTemplate = uiMenuTemplateService.get(appId, ownerId);
        map.put("uiMenuTemplate", uiMenuTemplate);
        UiSkinConfig uiSkinConfig = uiSkinConfigService.get(appId, ownerId);
        map.put("skinConfig",uiSkinConfig);
        List<UiLogoConfig> uiLogoConfigs = uiLogoConfigService.get(appId, ownerId);
        map.put("logoConfig",uiLogoConfigs.get(0));
        UiMenuConfig uiMenuConfig = this.get(appId, ownerId);
        map.put("menuConfig", uiMenuConfig);
        return map;
    }

    @Override
    public Page<UiMenuConfig> page(UiMenuConfig menuConfig, Pageable pageable) {
        Assert.isTrue(!ObjectUtils.isEmpty(menuConfig), "参数有误");
        Assert.isTrue(!ObjectUtils.isEmpty(menuConfig.getOwnerId()), "ownerId不存在");
        Assert.isTrue(!ObjectUtils.isEmpty(menuConfig.getAppId()), "appId不存在");
        Map<String, Object> map = MapUtils.objectToLineMap(menuConfig);
        QueryWrapper<UiMenuConfig> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);
        return mapper.findAll(wrapper,pageable);
    }

    @Override
    public UiMenuConfig queryById(Long id) {
        return this.mapper.findById(id).orElseThrow(() -> new IllegalArgumentException("参数错误"));
    }


}
