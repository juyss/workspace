package com.icepoint.base.web.resource.util;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;

import java.util.stream.StreamSupport;

@UtilityClass
public class JacksonUtils {

    @Nullable
    public static Object getMostSpecificValue(@Nullable JsonNode jsonNode) {
        if (jsonNode == null || jsonNode.isNull())
            return null;
        else if (jsonNode.isArray())
            return StreamSupport.stream(jsonNode.spliterator(), false)
                    .map(JacksonUtils::getMostSpecificValue).toArray();
        else if (jsonNode.isLong())
            return jsonNode.asLong();
        else if (jsonNode.isInt())
            return jsonNode.asInt();
        else if (jsonNode.isBoolean())
            return jsonNode.asBoolean();
        else
            return jsonNode.asText();
    }

}
