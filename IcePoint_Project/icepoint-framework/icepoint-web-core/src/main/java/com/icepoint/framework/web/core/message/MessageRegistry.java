package com.icepoint.framework.web.core.message;

import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.MessageTemplates;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 应用信息注册中心
 *
 * @author Jiawei Zhao
 */
public class MessageRegistry {

    /**
     * key是{@link MessageDefinition}对应的异常类型
     */
    private final Map<Class<? extends Exception>, MessageDefinition> definitionsByType = new ConcurrentHashMap<>();

    /**
     * key是{@link MessageDefinition}对应的code
     */
    private final Map<String, MessageDefinition> definitionsByCode = new ConcurrentHashMap<>();

    /**
     * 注册一个{@link Message}
     *
     * @param message 要注册的Message
     */
    public void register(Message message) {
        Class<?>[] exTypes = message.getExTypes();
        register(message.getDefaultTemplate(), message.getCode(), exTypes == null ? null : Arrays.asList(exTypes));
    }

    /**
     * 注册一组code和异常类型的对应关系
     *
     * @param code    信息的code
     * @param exTypes 信息异常的类型, 不能为空
     */
    public void register(String code, List<Class<?>> exTypes) {
        register(null, code, exTypes);
    }

    /**
     * 注册一组code和异常类型的对应关系, 并且提供一个默认的信息模板
     *
     * @param defaultTemplate 信息模板, 允许为空
     * @param code            信息的code
     * @param exTypes         信息异常的类型, 不能为空
     */
    public void register(@Nullable String defaultTemplate, String code, @Nullable List<Class<?>> exTypes) {

        Assert.hasText(code, MessageTemplates.notNull("code"));

        register(new MessageDefinition(defaultTemplate, code, CastUtils.cast(exTypes)));
    }

    /**
     * 注册一个{@link MessageDefinition}
     *
     * @param definition 信息定义
     */
    public void register(MessageDefinition definition) {

        for (Class<? extends Exception> exType : definition.getExceptionTypes()) {

            Assert.isAssignable(Exception.class, exType, "绑定的Class对象必须是Exception类型");
            Assert.notNull(exType, MessageTemplates.notNull("异常类型"));

            definitionsByType.merge(exType, definition, (c1, c2) -> {
                throw new IllegalArgumentException(String.format(
                        "异常类型绑定的信息码重复, code: [%s, %s] -> exception type: %s",
                        c1, c2, exType.getName()));
            });
        }

        definitionsByCode.merge(definition.getCode(), definition, (a, b) -> {
            throw new IllegalArgumentException(String.format(
                    "信息码重复, code: %s", a.getCode()));
        });
    }

    /**
     * 根据异常类型获取信息定义, 如果不存在时返回{@code null}
     *
     * @param exceptionType 要获取的信息的异常类型
     * @return MessageDefinition
     */
    @Nullable
    public MessageDefinition getMessageDefinition(Class<? extends Exception> exceptionType) {
        return definitionsByType.get(exceptionType);
    }

    /**
     * 根据信息的code获取信息定义，如果不存在时返回{@code null}
     *
     * @param code 信息的code
     * @return MessageDefinition
     */
    @Nullable
    public MessageDefinition getMessageDefinition(String code) {
        return definitionsByCode.get(code);
    }
}
