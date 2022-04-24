package com.github.tangyi.file.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.file.api.model.InstitutionHistory;
import com.github.tangyi.file.mapper.InstitutionHistoryMapper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class InstitutionHistoryService extends CrudService<InstitutionHistoryMapper, InstitutionHistory> {

    public List<InstitutionHistory> listInstitutionHistory(List<Long> idList) {
        if (CollectionUtil.isEmpty(idList)) {
            return Lists.newArrayList();
        }
        return dao.listInstitutionHistory(idList);
    }

}
