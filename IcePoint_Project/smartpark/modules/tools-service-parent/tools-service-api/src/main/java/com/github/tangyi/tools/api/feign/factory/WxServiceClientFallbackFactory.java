package com.github.tangyi.tools.api.feign.factory;

import com.github.tangyi.tools.api.feign.WxServiceClient;
import com.github.tangyi.tools.api.feign.fallback.WxServiceClientFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 消息中心服务断路器工厂
 *
 * @author gaokx
 * @date 2020/03/23 16:08
 */
@Component
public class WxServiceClientFallbackFactory implements FallbackFactory<WxServiceClient> {

    @Override
    public WxServiceClient create(Throwable throwable) {
        WxServiceClientFallbackImpl toolsServiceClientFallback = new WxServiceClientFallbackImpl();
        toolsServiceClientFallback.setThrowable(throwable);
        return toolsServiceClientFallback;
    }
}
