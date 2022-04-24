package com.github.tangyi.core.common.util;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.github.tangyi.core.common.BaseConstants;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * JSON工具
 *
 * @author hedongzhou
 * @date 2018/06/13
 */
public class JsonUtils {

    public static final ObjectMapper MAPPER = setCommonConfig();

    /**
     * 常规配置
     */
    public static ObjectMapper setCommonConfig() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object obj, JsonGenerator json, SerializerProvider provider) throws IOException {
                json.writeString("");
            }
        });

        objectMapper.setDateFormat(new SimpleDateFormat(BaseConstants.DEFAULT_PATTERN_DATETIME));

        return objectMapper;
    }

    /**
     * 获取JSON节点对象
     *
     * @param json
     * @return
     */
    public static JsonNode toJsonNode(String json) {
        try {
            return MAPPER.readTree(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象转JSON
     *
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        } else {
            try {
                return MAPPER.writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * JSON转对象
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T toObject(String json, Class<T> type) {
        try {
            return MAPPER.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * JSON转List
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> toList(String json, Class<T>... type) {
        try {
            return MAPPER.readValue(json, getCollectionType(ArrayList.class, type));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * JSON转Set
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> HashSet<T> toSet(String json, Class<T>... type) {
        try {
            return MAPPER.readValue(json, getCollectionType(HashSet.class, type));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取泛型的类型
     *
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

	/**
	 * json字符串转成List
	 * 
	 * @param jsonStr
	 * @param typeClass
	 * @return
	 */
	public static <T> List<T> nodeToList(String jsonStr, Class<T> typeClass) {
		try {
			List<T> list = MAPPER.readValue(jsonStr,
					MAPPER.getTypeFactory().constructCollectionType(List.class, typeClass));
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
