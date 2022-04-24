package com.icepoint.base.web.basic.controller;

import com.icepoint.base.api.domain.BasicEntity;
import com.icepoint.base.web.basic.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public abstract class AutowiredController<S extends CrudService<E, ID>, E extends BasicEntity<ID>, ID
        extends Serializable> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected S service;
}
