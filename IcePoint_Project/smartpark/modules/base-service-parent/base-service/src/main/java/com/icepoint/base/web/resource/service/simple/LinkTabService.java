package com.icepoint.base.web.resource.service.simple;

import com.icepoint.base.web.basic.service.CrudService;
import com.icepoint.base.api.entity.LinkTab;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS)
public interface LinkTabService extends CrudService<LinkTab, Long> {
}
