package com.github.tangyi.file.api.feign.factory;

import com.github.tangyi.file.api.feign.AttachmentServiceClient;
import com.github.tangyi.file.api.feign.fallback.AttachmentServiceClientFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 文件服务断路器工厂
 *
 * @author gaokx
 * @date 2020/11/30 13:07
 */
@Component
public class AttachmentServiceClientFallbackFactory implements FallbackFactory<AttachmentServiceClient> {

    @Override
    public AttachmentServiceClient create(Throwable throwable) {
        AttachmentServiceClientFallbackImpl mscServiceClientFallback = new AttachmentServiceClientFallbackImpl();
        mscServiceClientFallback.setThrowable(throwable);
        return mscServiceClientFallback;
    }
}
