package com.icepoint.base.web.resource.service.simple.impl;

import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.api.entity.Resource;
import com.icepoint.base.web.resource.mapper.ResourceMapper;
import com.icepoint.base.web.resource.service.simple.ResourceService;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl extends AntdPageService<ResourceMapper, Resource, Long> implements ResourceService {

}