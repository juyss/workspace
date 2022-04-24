package com.icepoint.base.web.resource.service.simple;

import com.icepoint.base.api.entity.Plan;
import com.icepoint.base.web.basic.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.SUPPORTS)
public interface PlanService extends CrudService<Plan, Long> {
    Page<Plan> pageByHelper(String fileName, String intelligence, List<String> deptId, Integer page, Integer size);

    List<Plan> listByIdList(List<Long> idList);

    void deleteByIdList(List<Long> idList);
}
