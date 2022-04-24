package com.icepoint.base.web.resource.service.simple;

import com.icepoint.base.web.basic.service.CrudService;
import com.icepoint.base.api.entity.Resource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS)
public interface ResourceService extends CrudService<Resource, Long> {

}
