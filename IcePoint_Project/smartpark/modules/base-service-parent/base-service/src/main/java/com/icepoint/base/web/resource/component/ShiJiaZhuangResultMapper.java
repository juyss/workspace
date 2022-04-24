package com.icepoint.base.web.resource.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@SuppressWarnings("unchecked")
@RequiredArgsConstructor
public enum ShiJiaZhuangResultMapper {

    EMERGENCY(map -> {
        if (CollectionUtils.isEmpty(map))
            return null;

        Map<String, Object> retJSON;
        if (CollectionUtils.isEmpty(retJSON = (Map<String, Object>) map.get("retJSON")))
            return null;

        if (retJSON.get("result") instanceof String)
            return null;

        Map<String, Object> result;
        if (CollectionUtils.isEmpty(result = (Map<String, Object>) retJSON.get("result")))
            return null;

        Object data;
        if ((data = result.get("data")) == null)
            return null;

        if (data instanceof String)
            return null;

        if (data instanceof List)
            return (List<Map<String, Object>>) data;
        else
            return Collections.singletonList((Map<String, Object>) data);
    }),

    ENVIRONMENTAL(map -> {
        if (CollectionUtils.isEmpty(map))
            return null;

        Map<String, Object> retJSON;
        if (CollectionUtils.isEmpty(retJSON = (Map<String, Object>) map.get("retJSON")))
            return null;

        Map<String, Object> result;
        if (CollectionUtils.isEmpty(result = (Map<String, Object>) ((Map<String, Object>) retJSON.get("result")).get("result")))
            return null;

        Object data;
        if ((data = result.get("items")) == null)
            return null;

        if (data instanceof List)
            return (List<Map<String, Object>>) data;
        else
            return Collections.singletonList((Map<String, Object>) data);
    }),

    SECURITY(map -> {
        if (CollectionUtils.isEmpty(map))
            return null;

        Map<String, Object> retJSON;
        if (CollectionUtils.isEmpty(retJSON = (Map<String, Object>) map.get("retJSON")))
            return null;

        List<Map<String, Object>> result;
        if (CollectionUtils.isEmpty(result = (List<Map<String, Object>>) retJSON.get("result")))
            return null;

        if (result instanceof List)
            return result;
        else
            return Collections.singletonList((Map<String, Object>) result);
    }),

    // 总体应急预案
    EMERGENCY_TOTAL_PLAN(map -> {
        if (CollectionUtils.isEmpty(map))
            return null;

        Map<String, Object> retJSON;
        if (CollectionUtils.isEmpty(retJSON = (Map<String, Object>) map.get("retJSON")))
            return null;

        Map<String, Object> result;
        if (CollectionUtils.isEmpty(result = (Map<String, Object>) retJSON.get("result")))
            return null;

        Map<String, Object> data;
        if (CollectionUtils.isEmpty(data = (Map<String, Object>) result.get("data")))
            return null;

        List<Map<String, Object>> totalPlan;
        if (CollectionUtils.isEmpty(totalPlan = (List<Map<String, Object>>) data.get("totalPlan")))
            return null;

        return totalPlan;
    }),

    // 专项应急预案
    EMERGENCY_SPECIAL_PLAN(map -> {
        if (CollectionUtils.isEmpty(map))
            return null;

        Map<String, Object> retJSON;
        if (CollectionUtils.isEmpty(retJSON = (Map<String, Object>) map.get("retJSON")))
            return null;

        Map<String, Object> result;
        if (CollectionUtils.isEmpty(result = (Map<String, Object>) retJSON.get("result")))
            return null;

        Map<String, Object> data;
        if (CollectionUtils.isEmpty(data = (Map<String, Object>) result.get("data")))
            return null;

        List<Map<String, Object>> specialPlan;
        if (CollectionUtils.isEmpty(specialPlan = (List<Map<String, Object>>) data.get("specialplan")))
            return null;

        return specialPlan;
    }),

    // 现场处置方案
    EMERGENCY_LIVE_PLAN(map -> {
        if (CollectionUtils.isEmpty(map))
            return null;

        Map<String, Object> retJSON;
        if (CollectionUtils.isEmpty(retJSON = (Map<String, Object>) map.get("retJSON")))
            return null;

        Map<String, Object> result;
        if (CollectionUtils.isEmpty(result = (Map<String, Object>) retJSON.get("result")))
            return null;

        Map<String, Object> data;
        if (CollectionUtils.isEmpty(data = (Map<String, Object>) result.get("data")))
            return null;

        List<Map<String, Object>> livePlan;
        if (CollectionUtils.isEmpty(livePlan = (List<Map<String, Object>>) data.get("livePlan")))
            return null;

        return livePlan;
    }),

    //针对中台接口做分页，目前只针对于`重点监管危险化学品（园区）`这一个中台接口
    PAGE(map -> {
        if (CollectionUtils.isEmpty(map))
            return null;

        Map<String, Object> retJSON;
        if (CollectionUtils.isEmpty(retJSON = (Map<String, Object>) map.get("retJSON")))
            return null;

        if (retJSON.get("result") instanceof String)
            return null;

        Map<String, Object> result;
        if (CollectionUtils.isEmpty(result = (Map<String, Object>) retJSON.get("result")))
            return null;

        //获取总数量
        Object total = result.get("total");
        HashMap<String, Object> totalValue = new HashMap<>();
        totalValue.put("total",total);

        Object temp;
        if ((temp = result.get("data")) == null)
            return null;

        if (temp instanceof String)
            return null;

        if (temp instanceof List){
            List<Map<String, Object>> data = (List<Map<String, Object>>)temp;
            data.add(totalValue);
            return data;
        } else {
            return Collections.singletonList(result);
        }
    });

    @Getter
    private final Function<Map<String, Object>, List<Map<String, Object>>> mapper;


}
