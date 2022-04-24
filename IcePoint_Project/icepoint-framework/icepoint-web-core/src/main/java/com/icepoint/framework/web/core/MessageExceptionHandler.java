package com.icepoint.framework.web.core;

import com.icepoint.framework.web.core.message.CodedMessageException;
import com.icepoint.framework.web.core.message.MessageContext;
import com.icepoint.framework.web.core.response.Response;
import com.icepoint.framework.web.core.response.ResponseBuilder;
import com.icepoint.framework.web.core.util.CoreMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author Jiawei Zhao
 */
@Slf4j
@RestControllerAdvice
public class MessageExceptionHandler {

    private final MessageContext messageContext;

    public MessageExceptionHandler(MessageContext messageContext) {
        this.messageContext = messageContext;
    }

    @ExceptionHandler(Exception.class)
    public Response<Void> handle(Exception e) {
        String code;
        if (e instanceof CodedMessageException) {
            code = ((CodedMessageException) e).getCode();
        } else {
            code = messageContext.getCode(e);
            if (CoreMessage.UNDEFINED.getCode().equals(code)) {
                log.warn("抛出信息既没有继承CodedMessage也没有与异常类型绑定");
            }
        }

        String message = messageContext.getMessage(e, code);
        log.debug(message);

        e.printStackTrace();

        return ResponseBuilder.<Void>any(null)
                .code(code)
                .message(message)
                .build();
    }
}
