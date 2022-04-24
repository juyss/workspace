package com.icepoint.base.web.resource.service.simple.impl;

import com.icepoint.base.api.entity.Approval;
import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.web.resource.mapper.ApprovalMapper;
import com.icepoint.base.web.resource.service.simple.ApprovalService;
import org.springframework.stereotype.Service;

@Service
public class ApprovalServiceImpl extends AntdPageService<ApprovalMapper, Approval, Long> implements ApprovalService {

    @Override
    public Long add(Approval entity) {
        mapper.add(entity);
        return entity.getId();
    }
}
