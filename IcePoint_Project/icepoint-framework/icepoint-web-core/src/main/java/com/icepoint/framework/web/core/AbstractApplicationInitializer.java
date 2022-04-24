package com.icepoint.framework.web.core;

import com.icepoint.framework.web.core.dao.ResponseMessageRepository;
import com.icepoint.framework.web.core.entity.QResponseMessage;
import com.icepoint.framework.web.core.entity.ResponseMessage;
import com.icepoint.framework.web.core.message.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractApplicationInitializer<M extends Enum<M> & Message> implements ApplicationInitializer {

    private final ResponseMessageRepository messageRepository;

    protected final void initMessages(M[] messages) {

        QResponseMessage q = QResponseMessage.responseMessage;

        List<M> missingMessages = Arrays.stream(messages)
                .filter(message -> !messageRepository.exists(q.code.eq(message.getCode()), false))
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(missingMessages)) {
            return;
        }

        String moduleName = getModuleName();
        log.info("初始化{}数据", moduleName);

        List<ResponseMessage> missingResponseMessages = missingMessages.stream()
                .map(message -> ResponseMessage.builder()
                        .code(message.getCode())
                        .message(getMissingMessage(message))
                        .build())
                .collect(Collectors.toList());

        messageRepository.saveAll(missingResponseMessages);

        log.info("{}初始化数据完成", moduleName);

    }

    protected abstract String getModuleName();

    protected abstract String getMissingMessage(M message);
}
