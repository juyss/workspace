package com.github.tangyi.file.api.feign.fallback;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.file.api.feign.BaseServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消息中心服务断路器
 *
 * @author tangyi
 * @date 2019/07/02 16:09
 */
@Slf4j
@Component
public class BaseServiceClientFallbackImpl implements BaseServiceClient {

    private Throwable throwable;


    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }


    @Override
    public String institutionHistoryPage(List<String> docNoList) {
        log.error("Feign send message failed: {}", docNoList, throwable);
        return null;
    }

    @Override
    public ResponseBean<?> field() {
        log.error("Feign send message failed: ", throwable);
        return null;
    }
}
