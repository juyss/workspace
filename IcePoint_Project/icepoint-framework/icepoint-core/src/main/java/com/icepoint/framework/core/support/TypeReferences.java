package com.icepoint.framework.core.support;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Map;

/**
 * @author Jiawei Zhao
 */
public class TypeReferences {

    public static final TypeReference<Map<String, Object>> STRING_OBJECT_MAP = new TypeReference<Map<String, Object>>() {
    };

    private TypeReferences() {
    }
}
