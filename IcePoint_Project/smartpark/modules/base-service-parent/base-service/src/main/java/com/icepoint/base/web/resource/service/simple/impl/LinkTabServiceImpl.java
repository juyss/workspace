package com.icepoint.base.web.resource.service.simple.impl;

import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.api.entity.LinkTab;
import com.icepoint.base.web.resource.mapper.LinkTableMapper;
import com.icepoint.base.web.resource.service.simple.LinkTabService;
import org.springframework.stereotype.Service;

@Service
public class LinkTabServiceImpl extends AntdPageService<LinkTableMapper, LinkTab, Long> implements LinkTabService {

}