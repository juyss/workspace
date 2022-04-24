package com.github.tangyi.file.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.file.api.model.Plan;
import com.github.tangyi.file.mapper.PlanMapper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PlanService extends CrudService<PlanMapper, Plan> {

    public List<Plan> listByIdList(List<Long> idList) {
        if (CollectionUtil.isEmpty(idList)) {
            return Lists.newArrayList();
        }
        return dao.listByIdList(idList);
    }

}
