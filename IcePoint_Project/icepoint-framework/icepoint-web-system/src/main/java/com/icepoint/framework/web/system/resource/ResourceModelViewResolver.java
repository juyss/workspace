package com.icepoint.framework.web.system.resource;

import java.util.Map;

/**
 * 将{@link ResourceModel}对象解析成{@link Map}视图的视图解析器
 *
 * @author Jiawei Zhao
 */
public interface ResourceModelViewResolver {

    /**
     * 根据传入viewType, 将model解析成Map视图 <br/>
     * 这个方法将忽略ResourceModel内部Lookup对象中的ViewType
     *
     * @param model    要解析的model对象
     * @param viewType 视图类型
     * @return Map视图
     */
    Map<String, Object> resolve(ResourceModel model, ViewType viewType);

    /**
     * 根据model内部Lookup对象中的ViewType，将model解析成Map视图
     *
     * @param model 要解析的model对象
     * @return Map视图
     */
    Map<String, Object> resolve(ResourceModel model);

}
