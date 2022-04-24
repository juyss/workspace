package com.icepoint.framework.web.core.message;

import com.icepoint.framework.web.core.dao.ResponseMessageRepository;
import com.icepoint.framework.web.core.entity.QResponseMessage;
import com.icepoint.framework.web.core.entity.ResponseMessage;
import com.icepoint.framework.web.core.util.CoreMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Jiawei Zhao
 */
@Slf4j
@Component
public class RepositoryMessageContext extends AbstractMessageContext {

    private final ResponseMessageRepository messageRepository;

    public RepositoryMessageContext(MessageRegistry registry,
            MessageTemplateParser parser, ResponseMessageRepository messageRepository) {

        super(registry, parser);
        this.messageRepository = messageRepository;
    }

    @Override
    protected String getMessageTemplateInternal(MessageDefinition definition) {

        String code = definition.getCode();

        ResponseMessage responseMessage = messageRepository.findOne(QResponseMessage.responseMessage.code.eq(code), false)
                .orElseGet(() -> {
                    log.warn("数据库没有对应信息码相关的信息, code: " + code);
                    return messageRepository.findOne(QResponseMessage.responseMessage.code.eq(CoreMessage.UNDEFINED.getCode()))
                            .orElseThrow(UnsupportedOperationException::new);
                });

        return responseMessage.getMessage();
    }
}
