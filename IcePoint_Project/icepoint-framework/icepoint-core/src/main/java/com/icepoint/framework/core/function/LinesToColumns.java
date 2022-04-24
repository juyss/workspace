package com.icepoint.framework.core.function;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 行转列
 */
public class LinesToColumns extends AbstractProcessor<LinesToColumns.Parameters, Map<String, Object>> {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Parameters {
        /**
         * 行对象列表
         */
        private Collection<Map<String,Object>> lines;
        /**
         * 字段，这些字段，每一个转为一个行对象，如果不存在则创建字段，值为空
         */
        private Collection<String> fields;
        /**
         * 行转列后的名称字段
         */
        private String key;
        /**
         * 行转列后的值字段
         */
        private String value;
        /**
         * 公共字段，这些字段在转为列对象后，每个对象中均保留
         */
        private Collection<String> common;
        /**
         * 字段映射，转换的字段以及公共字段，映射后的值
         */
        private Map<String, String> mapping;
        /**
         * 扩展对象，添加到每个转换后的对象上，不参与字段映射
         */
        private Map<String, Object> mapEx;

    }


    @Override
    protected Map<String, Object> processInternal(Parameters parameters) {
        Collection<Map<String, Object>> lines = parameters.getLines();
        Collection<String> fields = parameters.getFields();
        Collection<String> common = parameters.getCommon();
        String key = parameters.getKey();
        String value = parameters.getValue();
        Map<String, String> mapping = parameters.getMapping();
        Map<String, Object> mapEx = parameters.getMapEx();
        if(ObjectUtils.isEmpty(lines) || ObjectUtils.isEmpty(fields)
                || ObjectUtils.isEmpty(key) || ObjectUtils.isEmpty(value)){
           return Collections.singletonMap("list",null);
        }
        List<Object> list = new ArrayList<>();
        for (Map<String, Object> line : lines) {
            List<Map<String,Object>> rList = null;
            rList = fields.stream().map(field -> {
                Map<String,Object> map = new HashMap<>();
                if (line.containsKey(field)){
                    if (null != mapping && mapping.containsKey(field)){
                        map.put(key, mapping.get(field));
                    }
                    else{
                        map.put(key, field);
                    }
                    map.put(value, line.get(field));
                }
                else{
                    map.put(key, null);
                    map.put(value,null);
                }
                if (null != common){
                    common.stream().forEach(it -> {
                        if (line.containsKey(it)){
                            if (null != mapping && mapping.containsKey(it)){
                                map.put(mapping.get(it), line.get(it));
                            }
                            else{
                                map.put(it, line.get(it));
                            }
                        }
                        else{
                            map.put(it, null);
                        }
                    });
                }
                if (null != mapEx){
                    mapEx.entrySet().stream().forEach(it -> {
                        map.put(it.getKey(), it.getValue());
                    });
                }
                return map;
            }).collect(Collectors.toList());


            list.add(rList);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("list", list);
        return result;
    }
    @Override
    protected Map<String, Object> wrapResult(Map<String, Object> result) {
        return null;
    }


}
