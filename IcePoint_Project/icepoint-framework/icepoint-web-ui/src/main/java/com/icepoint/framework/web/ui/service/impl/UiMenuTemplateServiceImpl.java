package com.icepoint.framework.web.ui.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.icepoint.framework.core.util.MapUtils;
import com.icepoint.framework.web.ui.entity.UiMenuTemplate;
import com.icepoint.framework.web.ui.mapper.UiMenuTemplateMapper;
import com.icepoint.framework.web.ui.service.UiMenuTemplateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

/**
 * @author Administrator
 */
@Service
public class UiMenuTemplateServiceImpl implements UiMenuTemplateService {

    @Resource
    private UiMenuTemplateMapper mapper;

    @Override
    public UiMenuTemplate get(Long appId, Long ownerId) {
        Assert.isTrue(!ObjectUtils.isEmpty(appId), "app不存在");
        Assert.isTrue(!ObjectUtils.isEmpty(ownerId), "租户不存在");
        LambdaQueryWrapper<UiMenuTemplate> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(UiMenuTemplate::getAppId, appId);
        lambdaQueryWrapper.eq(UiMenuTemplate::getOwnerId, ownerId);
        Optional<UiMenuTemplate> one = mapper.findOne(lambdaQueryWrapper);
        one.orElseThrow(() -> new IllegalArgumentException("未找到配置"));
        return one.get();
    }

    @Override
    public Page<UiMenuTemplate> page(UiMenuTemplate uiMenuTemplate, Pageable pageable) {
        Assert.isTrue(!ObjectUtils.isEmpty(uiMenuTemplate), "参数有误");
        Assert.isTrue(!ObjectUtils.isEmpty(uiMenuTemplate.getOwnerId()), "ownerId不存在");
        Assert.isTrue(!ObjectUtils.isEmpty(uiMenuTemplate.getAppId()), "appId不存在");
        Map<String, Object> map = MapUtils.objectToLineMap(uiMenuTemplate);
        QueryWrapper<UiMenuTemplate> wrapper = new QueryWrapper<>();
        wrapper.allEq(map);
        return mapper.findAll(wrapper, pageable);
    }

    @Override
    public Boolean deleteUiMenuTemplate(Long id) {
        return mapper.deleteById(id) > 0;
    }

    @Override
    public Boolean update(UiMenuTemplate uiMenuTemplate) {
        if (ObjectUtils.isEmpty(uiMenuTemplate.getId())) {
            return mapper.insert(uiMenuTemplate) > 0;
        }
        return mapper.update(uiMenuTemplate) > 0;
    }

    @Override
    public UiMenuTemplate queryById(Long id) {
        return this.mapper.findById(id).orElseThrow(() -> new IllegalArgumentException("参数错误"));
    }


}
