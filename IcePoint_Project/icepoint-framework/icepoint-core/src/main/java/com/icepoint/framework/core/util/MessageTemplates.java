package com.icepoint.framework.core.util;

/**
 * 消息模版工具类
 *
 * @author Jiawei Zhao
 */
public final class MessageTemplates {

    private MessageTemplates() {
    }

    /**
     * 非空判断的消息模版
     *
     * @param target 不能为空对象的名称
     * @return 返回消息
     */
    public static String notNull(String target) {
        return "{ " + target + " }不能为空";
    }

    /**
     * 找不到数据的消息模版
     *
     * @param target 找不到数据的目标名称
     * @param value  找不到数据的条件
     * @return 返回消息
     */
    public static String notFound(String target, Object value) {
        return "找不到{ " + target + " }为: { " + value + " }的数据";
    }
}
