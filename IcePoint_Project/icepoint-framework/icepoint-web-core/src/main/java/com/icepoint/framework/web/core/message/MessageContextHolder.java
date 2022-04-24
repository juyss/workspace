package com.icepoint.framework.web.core.message;

import com.icepoint.framework.core.util.MessageTemplates;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Jiawei Zhao
 */
@Component
public class MessageContextHolder {

    private static MessageContext context;

    @Autowired
    private MessageContextHolder(BeanFactory beanFactory) {
        MessageContextHolder.setContext(beanFactory.getBean(MessageContext.class));
    }

    public static MessageContext getContext() {
        return context;
    }

    public static void setContext(MessageContext context) {
        Assert.notNull(context, MessageTemplates.notNull("MessageContext"));
        MessageContextHolder.context = context;
    }
}
