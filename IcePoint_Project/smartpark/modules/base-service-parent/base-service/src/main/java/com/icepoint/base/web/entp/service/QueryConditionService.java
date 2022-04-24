package com.icepoint.base.web.entp.service;

import com.icepoint.base.api.entity.QueryCondition;
import com.icepoint.base.web.basic.service.CrudService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.SUPPORTS)
public interface QueryConditionService extends CrudService<QueryCondition, Long> {
    List<String> listName();

    List<QueryCondition> getListByName(String name);

    Integer deleted(Long id);
}
