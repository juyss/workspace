package com.icepoint.framework.web.core.support;

import com.icepoint.framework.web.core.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jiawei Zhao
 */
public abstract class AbstractController<S extends Service> implements Responses {

    protected S service;

    private ResponseFactory responseFactory;

    @Autowired
    private void setService(S service) {
        this.service = service;
    }

}
