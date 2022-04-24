package com.icepoint.framework.web.core.response.hateoas;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Links;

/**
 * @author Jiawei Zhao
 */
public class HateoasEntityResponse<T extends EntityModel<?>> extends HateoasDefaultResponse<T> {

    public HateoasEntityResponse(T data, Links links) {
        super(data, links);
    }
}
