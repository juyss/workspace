package com.icepoint.framework.web.core;

import com.icepoint.framework.web.core.dao.ResponseMessageRepository;
import com.icepoint.framework.web.core.util.CoreMessage;
import org.springframework.stereotype.Component;

/**
 * @author Jiawei Zhao
 */
@Component
public class WebCoreInitializer extends AbstractApplicationInitializer<CoreMessage> {

    public WebCoreInitializer(ResponseMessageRepository messageRepository) {
        super(messageRepository);
    }

    @Override
    public void initialize() {
        initMessages(CoreMessage.values());
    }

    @Override
    protected String getModuleName() {
        return "WebSystem";
    }

    @Override
    protected String getMissingMessage(CoreMessage message) {

        switch (message) {
            case OK:
                return "成功";
            case UNKNOWN:
                return "未知异常, 失败原因: {reason}";
            case ILLEGAL_ARG:
                return "参数异常, 原因: {message}";
            case NOT_FOUND:
                return "未找到数据";
            case OPERATE_FAILED:
                return "操作失败";
            case UNDEFINED:
                return "未定义异常: {code}";
            case METHOD_NOT_SUPPORTED:
                return "不支持的请求方法: '{method}'";
            default:
                throw new IllegalStateException("Unexpected value: " + message);
        }
    }
}
