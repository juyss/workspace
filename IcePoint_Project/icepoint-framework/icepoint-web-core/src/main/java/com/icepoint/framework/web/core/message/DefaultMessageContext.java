package com.icepoint.framework.web.core.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/**
 * @author Jiawei Zhao
 */
@Slf4j
@ConditionalOnMissingBean(MessageContext.class)
@Component
public class DefaultMessageContext extends AbstractMessageContext {

    public DefaultMessageContext(MessageRegistry registry,
            MessageTemplateParser parser) {
        super(registry, parser);
    }

}
