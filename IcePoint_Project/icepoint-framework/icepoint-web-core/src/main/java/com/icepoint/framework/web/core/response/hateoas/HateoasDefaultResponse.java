package com.icepoint.framework.web.core.response.hateoas;

import com.icepoint.framework.web.core.response.DefaultResponse;
import org.springframework.hateoas.Links;
import org.springframework.lang.Nullable;

/**
 * @author Jiawei Zhao
 */
public class HateoasDefaultResponse<T> extends DefaultResponse<T> implements HateoasResponse {
    
    private final Links links;

    public HateoasDefaultResponse(@Nullable T data, Links links) {
        super(data);
        this.links = links;
    }

    @Override
    public Links getLinks() {
        return this.links;
    }
}
