package com.icepoint.framework.web.core.response.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Links;

/**
 * 标记这是一个Hateoas的Response
 *
 * @author Jiawei Zhao
 */
public interface HateoasResponse {

    @JsonProperty("_links")
    Links getLinks();
}
