package com.icepoint.framework.core.function;

import com.icepoint.framework.core.util.MapUtils;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * 根据字段对列表进行分组，以字段值为键，以分组对象为值返回。
 */
public class ListGrpByFld extends AbstractProcessor<ListGrpByFld.Parameters, Map<String, Object>>{

    @Data
    @AllArgsConstructor
    public static class Parameters {
        /**
         * 对象列表
         */
        private Collection<Object> objList;
        /**
         * 分组字段
         */
        private String field;

    }


    @Override
    public Map<String, Object> processInternal(Parameters parameters) {
         String field = parameters.getField();
         Collection<Object> objList = parameters.getObjList();
        List<Map<String,Object>> list = new ArrayList<>();
        Map<Object ,List<Object>> objectMap = new HashMap<>(objList.size());
        for (Object o : objList) {
            Map<String, Object> map = MapUtils.objectToMap(o);
            Object o1 = map.get(field);
            if(objectMap.containsKey(o1)){
                List<Object> groupList = objectMap.get(o1);
                groupList.add(map);
            }else {
                List<Object> firstList = new ArrayList<>();
                firstList.add(map);
                objectMap.put(o1,firstList);
            }
        }
        System.out.println(objectMap);
        return null;
    }

    @Override
    protected Map<String, Object> wrapResult(Map<String, Object> result) {
        return null;
    }




}
