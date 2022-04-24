package com.icepoint.base.web.resource.service.simple.impl;

import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.api.entity.Layer;
import com.icepoint.base.web.resource.mapper.LayerMapper;
import com.icepoint.base.web.resource.service.simple.LayerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class LayerServiceImpl extends AntdPageService<LayerMapper, Layer, Long> implements LayerService {

    @Transactional
    @Override
    public Integer batchUpdate(List<Map<String, Object>> list) {
        return getRepository().batchUpdate(list);
    }

}