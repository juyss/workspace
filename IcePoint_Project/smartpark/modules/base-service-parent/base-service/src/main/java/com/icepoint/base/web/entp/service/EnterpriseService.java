package com.icepoint.base.web.entp.service;

import com.icepoint.base.api.entity.Enterprise;
import com.icepoint.base.api.entity.QueryCondition;
import com.icepoint.base.web.basic.service.CrudService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.SUPPORTS)
public interface EnterpriseService extends CrudService<Enterprise, Long> {

    List<Enterprise> list(List<QueryCondition> queryConditionList);

}
