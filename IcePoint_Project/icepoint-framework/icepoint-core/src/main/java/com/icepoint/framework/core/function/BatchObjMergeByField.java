package com.icepoint.framework.core.function;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author Mozr
 *  将一个数组对象，按照某个字段，根据标识字段，将多个对象合并为一个对象，形成一个新的数组
 */
public class BatchObjMergeByField extends AbstractProcessor<BatchObjMergeByField.Parameters,  List<Map<String, Object>>> {

    @Data
    @AllArgsConstructor
    public static class Parameters {
        /**
         *  list      对象列表
         */
        private Collection<Map<String, Object>> list;
        /**
         * idFields  标识字段，如果右多个用逗号分开
         */
        private String idFields;
        /**
         * field     合并字段
         */
        private String  field;
        /**
         * mergeMode 合并字段模式，1-数组类型 2-逗号分割类型
         */
        private Integer mergeMode;
    }


    @Override
    protected List<Map<String, Object>> processInternal(Parameters parameters) {
        Collection<Map<String, Object>> list = parameters.getList();
        String field = parameters.getField();
        Integer mergeMode = parameters.getMergeMode();
        String idFields = parameters.getIdFields();

        List<Object> items = new ArrayList<>();
        list.forEach(map -> {
            Object obj = map.get(field);
            if (null != obj) {
                items.add(obj);
            }
        });
        Map<String, Object> record = new HashMap<>();
        if (1 == mergeMode) {
            String symbol = StringUtils.join(items.toArray(), ",");
            record.put(field, symbol);
        } else if (2 == mergeMode) {
            record.put(field, items.toArray());
        }
        List<Map<String, Object>> result = new ArrayList<>();
        result.add(record);
        return result;

    }

    @Override
    protected Map<String, Object> wrapResult(List<Map<String, Object>> result) {
        return null;
    }











}
