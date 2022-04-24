package com.icepoint.framework.web.core.message;

import com.icepoint.framework.web.core.util.CoreMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.Nullable;

/**
 * @author Jiawei Zhao
 */
@Slf4j
public abstract class AbstractMessageContext implements MessageContext {

    private final MessageRegistry registry;

    private final MessageTemplateParser parser;

    protected AbstractMessageContext(MessageRegistry registry,
            MessageTemplateParser parser) {
        this.registry = registry;
        this.parser = parser;
    }

    @Override
    public String getCode(Exception ex) {

        MessageDefinition definition = registry.getMessageDefinition(ex.getClass());

        if (definition == null) {
            return CoreMessage.UNDEFINED.getCode();
        }

        return definition.getCode();
    }

    @Override
    public String getMessage(@Nullable Exception ex, String code) {

        if (ex == null) {
            return getMessage(code);
        }

        String template = getMessageTemplate(code);
        return parseMessage(template, ex);
    }

    @Override
    public String getMessage(String code) {
        String template = getMessageTemplate(code);
        return parseMessage(template, new CodedMessageException(code));
    }

    @Override
    public String getMessage(Message message) {
        return getMessage(message.getCode());
    }

    private String getMessageTemplate(String code) {

        MessageDefinition definition = registry.getMessageDefinition(code);
        if (definition == null) {
            throw new IllegalStateException("找不到对应的信息, code: " + code);
        }

        String template = getMessageTemplateInternal(definition);

        if (template == null) {
            template =  definition.getDefaultTemplate();
        }

        if (template == null) {
            throw new IllegalStateException("找不到对应的信息模板");
        }

        return template;
    }

    private String parseMessage(String template, Exception e) {

        String message;
        try {

            message = parser.parse(template, e);

        } catch (MessageParseException exception) {

            message = "信息解析异常: " + NestedExceptionUtils.getMostSpecificCause(exception).getMessage();
            log.warn(message);

        }

        return message;
    }

    @Nullable
    protected String getMessageTemplateInternal(MessageDefinition definition) {
        return null;
    }

    protected MessageRegistry getRegistry() {
        return this.registry;
    }
}
