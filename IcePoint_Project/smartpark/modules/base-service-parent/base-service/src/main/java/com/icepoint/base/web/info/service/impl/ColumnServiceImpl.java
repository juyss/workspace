package com.icepoint.base.web.info.service.impl;

import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.web.info.entity.Column;
import com.icepoint.base.web.info.mapper.ColumnMapper;
import com.icepoint.base.web.info.service.ColumnService;

//@Service
public class ColumnServiceImpl extends AntdPageService<ColumnMapper, Column, Long> implements ColumnService {

    @Override
    public Integer maxSort(Column map) {
        return getRepository().maxSort(map);
    }

}