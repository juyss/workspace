package com.icepoint.framework.core.function;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 给定列表和连接符，将其拼接为字符串
 * @author BD
 *
 */
public class ListToString extends AbstractProcessor<ListToString.Parameters, String> {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Parameters {
        /**
         * 对象列表
         */
        private @Nullable List list;
        /**
         * 连接符，默认都英文逗号
         */
        private @Nullable String connector;
    }

    @Override
    protected String processInternal(Parameters parameters) {
    	List objList = parameters.getList();
        String connector = parameters.getConnector();
        if (StringUtils.isEmpty(connector)){
        	connector = ",";
        }
        final String symbol = connector;
        return (String)(objList.stream().reduce((cnt,item) -> cnt.toString() + symbol + String.valueOf(item)).get());
    }

    @Override
    protected Map<String, Object> wrapResult(String result) {
        return Collections.singletonMap("str", result);
    }
}
