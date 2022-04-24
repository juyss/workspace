package com.icepoint.base.web.resource.service.simple;

import com.icepoint.base.web.basic.service.CrudService;
import com.icepoint.base.api.entity.MetaTab;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS)
public interface MetaTabService extends CrudService<MetaTab, Long> {
}
