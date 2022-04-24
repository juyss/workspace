package com.icepoint.base.web.resource.service.simple.impl;

import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.api.entity.Line;
import com.icepoint.base.web.resource.mapper.LineMapper;
import com.icepoint.base.web.resource.service.simple.LineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LineServiceImpl extends AntdPageService<LineMapper, Line, Long> implements LineService {

    @Override
    public Integer batchUpdate(List<Map<String, Object>> list) {
        return getRepository().batchUpdate(list);
    }

    @Override
    public Integer batchAdd(List<Map<String, Object>> list) {
        return getRepository().batchAdd(list);
    }

    @Override
    public Integer batchDelete(List<Map<String, Object>> list) {
        return getRepository().batchDelete(list);
    }
}