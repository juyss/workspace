package com.icepoint.base.web.resource.service.complex;

import com.icepoint.base.api.domain.TreeEntity;
import com.icepoint.base.api.entity.Layer;
import com.icepoint.base.api.entity.Resource;
import com.icepoint.base.web.sys.entity.BaseParam;
import com.icepoint.base.web.sys.entity.Dict;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface ResourceLayerService {

    List<Layer> getLayerConfig(BaseParam entity);

    @Transactional
    Boolean putLayerConfig(List<Map<String, Object>> config);

    @Transactional
    Integer layerTop(Long resId);

    @Transactional
    Integer layerBottom(Long resId);

    @Transactional
    Integer layerUp(Long resId);

    @Transactional
    Integer layerDown(Long resId);

    Dict getResourceDictionary(String key);

    List<TreeEntity<Resource>> getResourceTree();
}
