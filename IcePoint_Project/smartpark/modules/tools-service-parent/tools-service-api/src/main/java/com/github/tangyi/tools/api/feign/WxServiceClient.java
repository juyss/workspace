package com.github.tangyi.tools.api.feign;

import com.github.tangyi.common.core.constant.ServiceConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.model.req.ParkNewsReq;
import com.github.tangyi.tools.api.feign.factory.WxServiceClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 消息中心服务
 *
 * @author gaokx
 * @date 2020/03/23 16:09
 */
@FeignClient(value = ServiceConstant.TOOLS_SERVICE, configuration = WxServiceClient.class, fallbackFactory = WxServiceClientFallbackFactory.class)
public interface WxServiceClient {

    /**
     * 新闻咨询推送
     *
     * @return ResponseBean
     * @author gaokx
     * @date 2020/03/23 09:57:27
     */
    @PostMapping("/v1/tools/wx/pushNews")
    ResponseBean<?> pushNews(@RequestBody ParkNewsReq parkNewsReq);
}
