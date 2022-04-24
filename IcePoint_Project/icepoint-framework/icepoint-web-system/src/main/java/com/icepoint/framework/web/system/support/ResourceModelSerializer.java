package com.icepoint.framework.web.system.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.icepoint.framework.web.system.resource.ResourceModel;
import com.icepoint.framework.web.system.resource.ResourceModelViewResolver;

import java.io.IOException;
import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public class ResourceModelSerializer extends JsonSerializer<ResourceModel> {

    private final ResourceModelViewResolver resolver;

    public ResourceModelSerializer(ResourceModelViewResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void serialize(ResourceModel value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Map<String, Object> view = resolver.resolve(value);
        gen.writeObject(view);
    }

    @Override
    public Class<ResourceModel> handledType() {
        return ResourceModel.class;
    }
}
