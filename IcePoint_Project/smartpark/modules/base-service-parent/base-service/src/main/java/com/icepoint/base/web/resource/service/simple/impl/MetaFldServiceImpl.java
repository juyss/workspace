package com.icepoint.base.web.resource.service.simple.impl;

import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.web.resource.mapper.MetaFldMapper;
import com.icepoint.base.web.resource.service.simple.MetaFldService;
import org.springframework.stereotype.Service;

@Service
public class MetaFldServiceImpl extends AntdPageService<MetaFldMapper, MetaField, Long> implements MetaFldService {

}