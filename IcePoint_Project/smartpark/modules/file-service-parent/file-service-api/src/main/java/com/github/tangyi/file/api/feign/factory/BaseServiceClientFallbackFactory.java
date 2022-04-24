package com.github.tangyi.file.api.feign.factory;


import com.github.tangyi.file.api.feign.BaseServiceClient;
import com.github.tangyi.file.api.feign.fallback.BaseServiceClientFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 消息中心服务断路器工厂
 *
 * @author tangyi
 * @date 2019/07/02 16:08
 */
@Component
public class BaseServiceClientFallbackFactory implements FallbackFactory<BaseServiceClient> {

    @Override
    public BaseServiceClient create(Throwable throwable) {
        BaseServiceClientFallbackImpl baseServiceClientFallback = new BaseServiceClientFallbackImpl();
        baseServiceClientFallback.setThrowable(throwable);
        return baseServiceClientFallback;
    }
}
