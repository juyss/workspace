package com.icepoint.framework.web.core.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * @author Jiawei Zhao
 */
public class PageResponse<T> extends CollectionResponse<T> {

    private final PageMetadata metadata;

    public PageResponse(@Nullable Page<T> data) {
        this(data == null ? null : data.getContent(), data == null ? null : new PageMetadata(data));
    }

    public PageResponse(@Nullable Collection<T> data, @Nullable PageMetadata metadata) {
        super(data);
        this.metadata = metadata;
    }

    @JsonProperty("page")
    public PageMetadata getMetadata() {
        return metadata;
    }

    public static <T> PageResponse<T> of(Response<Page<T>> response) {
        Page<T> data = response.getData();
        data = data == null ? Page.empty() : data;

        PageResponse<T> pageResponse = new PageResponse<>(data);
        pageResponse.setCode(response.getCode());
        pageResponse.setMessage(response.getMessage());

        return pageResponse;
    }
}
