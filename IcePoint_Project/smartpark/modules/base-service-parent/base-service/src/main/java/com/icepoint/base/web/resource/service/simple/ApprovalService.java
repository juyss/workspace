package com.icepoint.base.web.resource.service.simple;

import com.icepoint.base.api.entity.Approval;
import com.icepoint.base.web.basic.service.CrudService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS)
public interface ApprovalService extends CrudService<Approval, Long> {
}
