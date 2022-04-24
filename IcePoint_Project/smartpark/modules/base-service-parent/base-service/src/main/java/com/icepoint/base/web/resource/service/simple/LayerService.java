package com.icepoint.base.web.resource.service.simple;

import com.icepoint.base.web.basic.service.CrudService;
import com.icepoint.base.api.entity.Layer;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional(propagation = Propagation.SUPPORTS)
public interface LayerService extends CrudService<Layer, Long> {

    Integer batchUpdate(List<Map<String, Object>> list);
}
