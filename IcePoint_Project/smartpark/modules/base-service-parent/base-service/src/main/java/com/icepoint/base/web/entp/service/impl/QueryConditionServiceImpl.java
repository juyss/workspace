package com.icepoint.base.web.entp.service.impl;

import com.icepoint.base.api.entity.QueryCondition;
import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.web.entp.mapper.QueryConditionMapper;
import com.icepoint.base.web.entp.service.QueryConditionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryConditionServiceImpl extends AntdPageService<QueryConditionMapper, QueryCondition, Long> implements QueryConditionService {

    @Override
    public Long add(QueryCondition entity) {
        mapper.add(entity);
        return entity.getId();
    }

    @Override
    public List<String> listName() {
        return mapper.listName();
    }

    @Override
    public List<QueryCondition> getListByName(String name) {
        return mapper.getListByName(name);
    }

    @Override
    public Integer deleted(Long id) {
        return mapper.deleted(id);
    }
}
