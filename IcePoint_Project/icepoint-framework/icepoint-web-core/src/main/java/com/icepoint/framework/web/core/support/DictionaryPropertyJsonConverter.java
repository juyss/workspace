/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.icepoint.framework.web.core.support;

import com.icepoint.framework.core.io.Serializers;
import com.icepoint.framework.data.annotation.DictionaryProperty;
import com.icepoint.framework.web.core.entity.Dict;
import com.icepoint.framework.web.core.service.DictService;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Set;


/**
 * @author jiawei
 */
public class DictionaryPropertyJsonConverter implements AnnotationFormatterFactory<DictionaryProperty> {

    private final DictService dictService;

    public DictionaryPropertyJsonConverter(DictService dictService) {
        this.dictService = dictService;
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        return Collections.singleton(String.class);
    }

    @Override
    public Printer<?> getPrinter(DictionaryProperty annotation, Class<?> fieldType) {
        String category = (String) AnnotationUtils.getValue(annotation);

        return (object, locale) -> {
            if (!StringUtils.hasText(category)) {
                return (String) object;
            }

//            Dict dict = dictService.findByCategoryAndValue(category, (String) object)
//                    .orElseThrow(() -> new IllegalArgumentException("无法找到字典值: " + object));
//
//            Dict simpleDict = Dict.builder()
//                    .category(dict.getCategory())
//                    .categoryName(dict.getCategoryName())
//                    .item(dict.getItem())
//                    .itemName(dict.getItemName())
//                    .value(dict.getValue())
//                    .build();
//
//            return Serializers.json().serializeAsString(simpleDict);
            return null;
        };
    }

    @Override
    public Parser<?> getParser(DictionaryProperty annotation, Class<?> fieldType) {
        String category = (String) AnnotationUtils.getValue(annotation);

        return (text, locale) -> {
            if (!StringUtils.hasText(category)) {
                return text;
            }

            try {
                Dict dict = Serializers.json().deserialize(text, Dict.class);
                return dict.getValue();
            } catch (Exception e) {
                return text;
            }
        };
    }
}
