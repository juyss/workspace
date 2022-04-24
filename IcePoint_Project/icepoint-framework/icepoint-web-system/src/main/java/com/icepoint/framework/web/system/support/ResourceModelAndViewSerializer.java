package com.icepoint.framework.web.system.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.icepoint.framework.web.system.resource.ResourceModelAndView;

import java.io.IOException;

/**
 * @author Jiawei Zhao
 */
public class ResourceModelAndViewSerializer extends JsonSerializer<ResourceModelAndView> {

    @Override
    public void serialize(ResourceModelAndView value, JsonGenerator gen,
            SerializerProvider serializers) throws IOException {
        gen.writeObject(value.getView());
    }

    @Override
    public Class<ResourceModelAndView> handledType() {
        return ResourceModelAndView.class;
    }

}
