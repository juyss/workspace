package com.icepoint.framework.web.system.service.impl;

import com.icepoint.framework.web.system.entity.Attribute;
import com.icepoint.framework.web.system.entity.Label;
import com.icepoint.framework.web.system.entity.MultiAttribute;
import com.icepoint.framework.web.system.service.AttributeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Override
    public Integer addAttrs(List<Attribute> attrs) {
        return null;
    }

    @Override
    public Integer addMultiAttrs(List<MultiAttribute> multiAttrs) {
        return null;
    }

    @Override
    public Integer addObjLabels(List<Label> labels) {
        return null;
    }
}
