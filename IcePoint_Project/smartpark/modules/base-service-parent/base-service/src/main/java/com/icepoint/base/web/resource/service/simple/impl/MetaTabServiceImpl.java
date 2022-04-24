package com.icepoint.base.web.resource.service.simple.impl;

import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.api.entity.MetaTab;
import com.icepoint.base.web.resource.mapper.MetaTabMapper;
import com.icepoint.base.web.resource.service.simple.MetaTabService;
import org.springframework.stereotype.Service;

@Service
public class MetaTabServiceImpl extends AntdPageService<MetaTabMapper, MetaTab, Long> implements MetaTabService {

}