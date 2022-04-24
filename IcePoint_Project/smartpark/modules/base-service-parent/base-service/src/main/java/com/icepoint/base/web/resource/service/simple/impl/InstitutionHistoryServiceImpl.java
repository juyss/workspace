package com.icepoint.base.web.resource.service.simple.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.icepoint.base.api.entity.InstitutionHistory;
import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.web.resource.mapper.InstitutionHistoryMapper;
import com.icepoint.base.web.resource.service.simple.InstitutionHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionHistoryServiceImpl extends AntdPageService<InstitutionHistoryMapper, InstitutionHistory, Long> implements InstitutionHistoryService {

    @Override
    public Long add(InstitutionHistory entity) {
        mapper.add(entity);
        return entity.getId();
    }

    @Override
    public void deleteByIdList(List<Long> idList) {
        if (CollectionUtil.isEmpty(idList)) {
            return;
        }
        mapper.deleteByIdList(idList);
    }

    @Override
    public List<InstitutionHistory> listInstitutionHistory(List<Long> idList) {
        if (CollectionUtil.isEmpty(idList)) {
            return Lists.newArrayList();
        }
        return mapper.listInstitutionHistory(idList);
    }
}
