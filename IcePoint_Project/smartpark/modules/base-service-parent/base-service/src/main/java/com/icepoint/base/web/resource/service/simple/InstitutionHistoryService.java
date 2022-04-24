package com.icepoint.base.web.resource.service.simple;

import com.icepoint.base.api.entity.InstitutionHistory;
import com.icepoint.base.web.basic.service.CrudService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.SUPPORTS)
public interface InstitutionHistoryService extends CrudService<InstitutionHistory, Long> {
    void deleteByIdList(List<Long> idList);

    List<InstitutionHistory> listInstitutionHistory(List<Long> idList);
}
