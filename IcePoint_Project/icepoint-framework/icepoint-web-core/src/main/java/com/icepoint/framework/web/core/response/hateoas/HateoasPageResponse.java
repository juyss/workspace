package com.icepoint.framework.web.core.response.hateoas;

import com.icepoint.framework.web.core.response.PageMetadata;
import com.icepoint.framework.web.core.response.PageResponse;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;

/**
 * @author Jiawei Zhao
 */
public class HateoasPageResponse<T> extends PageResponse<T> implements HateoasResponse {

    private final Links links;

    public HateoasPageResponse(Collection<T> data, Links links, PagedModel.PageMetadata metadata) {
        this(data, links, PageMetadata.of(metadata));
    }

    public HateoasPageResponse(Collection<T> data, Links links, PageMetadata metadata) {
        super(data, metadata);
        this.links = links;
    }

    @Override
    public Links getLinks() {
        return links;
    }
}
