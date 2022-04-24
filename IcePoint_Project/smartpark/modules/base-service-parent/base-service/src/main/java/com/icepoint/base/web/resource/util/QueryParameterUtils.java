package com.icepoint.base.web.resource.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icepoint.base.api.util.ApplicationContextUtils;
import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.GenericProperty;
import com.icepoint.base.web.resource.component.query.*;
import lombok.experimental.UtilityClass;
import org.springframework.data.util.Lazy;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@UtilityClass
public class QueryParameterUtils {

    private static final Pattern LINK_INFO_PARAMETER_PATTERN = Pattern.compile("^\\{.+}$");

    private static final Lazy<ObjectMapper> OBJECT_MAPPER_LAZY =
            Lazy.of(() -> ApplicationContextUtils.getBean(ObjectMapper.class))
                    .or(ObjectMapper::new);

    public static QueryParameter createFrom(String linkInfo, GenericEntity entity) {
        GenericQueryParameter parameter = new GenericQueryParameter();
        if (!StringUtils.hasText(linkInfo))
            return parameter;

        ObjectMapper objectMapper = OBJECT_MAPPER_LAZY.get();

        Map<String, FieldOperation> fops = new HashMap<>();
        try {
            JsonNode linkInfoJson = objectMapper.readTree(linkInfo);
            if (linkInfoJson != null && !linkInfoJson.isNull()) {
                linkInfoJson.fields().forEachRemaining(e -> {
                    String fieldName = e.getKey();
                    String value = e.getValue().asText().trim();
                    if (value.length() > 2 && LINK_INFO_PARAMETER_PATTERN.matcher(value).matches()) {
                        String propertyName = value.substring(1, value.length() - 1);
                        GenericProperty property = entity.getProperty(propertyName);
                        Assert.state(property != null && property.getValue() != null,
                                "Parse link info fail, cause: " +
                                        "null value or unknown property \"" + propertyName + "\" in the main entity.");
                        value = Objects.toString(property.getValue());
                    }
                    FieldOperation fop = new FieldOperation(
                            fieldName, Collections.singletonMap(Operation.EQ, value)
                    );

                    fops.put(fieldName, fop);
                });

                parameter.setMatch(new Match(fops));
            }
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Exception when parsing link info.", e);
        }

        return parameter;
    }

    public static Optional<Match> parseMatch(@Nullable String jsonString) {
        try {
            ObjectMapper objectMapper = OBJECT_MAPPER_LAZY.get();
            JsonNode matchJson;

            if (StringUtils.hasText(jsonString)
                    && (matchJson = objectMapper.readTree(jsonString)) != null
                    && !matchJson.isNull()) {

                Map<String, FieldOperation> fieldOps = new LinkedHashMap<>();
                matchJson.fields().forEachRemaining(entry -> {
                    String fieldName = entry.getKey();
                    JsonNode fieldOpJson = entry.getValue();

                    Map<Operation, Object> ops = new LinkedHashMap<>();
                    FieldOperation fieldOp = new FieldOperation(fieldName, ops);
                    fieldOpJson.fields().forEachRemaining(fieldOpEntry -> {
                        Operation op = Operation.from(fieldOpEntry.getKey());
                        JsonNode valueJson = fieldOpEntry.getValue();
                        Object value = JacksonUtils.getMostSpecificValue(valueJson);

                        ops.computeIfPresent(op, (k, v) -> {
                            throw new IllegalArgumentException("同一个字段中，每种查询条件只能有一个");
                        });
                        ops.put(op, value);
                    });

                    fieldOps.put(fieldName, fieldOp);
                });
                return Optional.of(new Match(fieldOps));
            }
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("$match参数解析异常", e);
        }

        return Optional.empty();
    }

    public static Optional<Require> parseRequire(@Nullable String jsonString) {
        try {
            ObjectMapper objectMapper = OBJECT_MAPPER_LAZY.get();
            JsonNode requireJson;

            if (StringUtils.hasText(jsonString)
                    && (requireJson = objectMapper.readTree(jsonString)) != null
                    && !requireJson.isNull()) {

                JsonNode keys = requireJson.get("keys");
                if (keys != null && !keys.isNull()) {
                    List<String> joinsList;
                    if (keys.isArray()) {
                        joinsList = StreamSupport.stream(keys.spliterator(), false)
                                .map(JsonNode::asText)
                                .collect(Collectors.toList());
                    } else {
                        joinsList = Collections.singletonList(keys.asText());
                    }
                    return Optional.of(new Require(joinsList));
                }
            }
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("$require参数解析异常", e);
        }

        return Optional.empty();
    }
}
