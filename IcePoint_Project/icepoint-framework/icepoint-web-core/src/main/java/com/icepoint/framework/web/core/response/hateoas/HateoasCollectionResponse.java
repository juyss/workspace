package com.icepoint.framework.web.core.response.hateoas;

import com.icepoint.framework.web.core.response.CollectionResponse;
import org.springframework.hateoas.Links;

import java.util.Collection;

/**
 * @author Jiawei Zhao
 */
public class HateoasCollectionResponse<T> extends CollectionResponse<T> implements HateoasResponse {

    private final Links links;

    public HateoasCollectionResponse(Collection<T> collection, Links links) {
        super(collection);
        this.links = links;
    }

    @Override
    public Links getLinks() {
        return this.links;
    }
}
