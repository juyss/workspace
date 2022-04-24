package com.icepoint.base.web.resource.service.simple.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.icepoint.base.api.entity.Plan;
import com.icepoint.base.config.mybatis.pageable.ListDelegatePage;
import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.web.resource.mapper.PlanMapper;
import com.icepoint.base.web.resource.service.simple.PlanService;
import org.apache.commons.collections4.ListUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanServiceImpl extends AntdPageService<PlanMapper, Plan, Long> implements PlanService {

    @Override
    public Page<Plan> pageByHelper(String fileName, String intelligence, List<String> deptIdList, Integer page, Integer size) {
        // 去除空字符串
        deptIdList = deptIdList.stream().filter(deptId -> !deptId.isEmpty()).collect(Collectors.toList());
        boolean isTop = false;
        if (CollectionUtil.isNotEmpty(deptIdList)) {
            for (String deptId : deptIdList) {
                if ("781910899077369856".equals(deptId)) {
                    isTop = true;
                }
            }
            if (isTop) {
                deptIdList = null;
            }
            return mapper.pageByHelper(fileName, intelligence, deptIdList, PageRequest.of(page, size));
        }
        return new ListDelegatePage<>(Lists.newArrayList());
    }

    @Override
    public List<Plan> listByIdList(List<Long> idList) {
        return mapper.listByIdList(idList);
    }

    @Override
    public void deleteByIdList(List<Long> idList) {
        if (CollectionUtil.isEmpty(idList)) {
            return;
        }
        mapper.deleteByIdList(idList);
    }

}
