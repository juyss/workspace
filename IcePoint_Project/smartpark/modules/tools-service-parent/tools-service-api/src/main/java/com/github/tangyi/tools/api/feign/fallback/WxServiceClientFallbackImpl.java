package com.github.tangyi.tools.api.feign.fallback;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.model.req.ParkNewsReq;
import com.github.tangyi.tools.api.feign.WxServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 工具服务断路器
 *
 * @author gaokx
 * @date 2020/03/23 16:09
 */
@Slf4j
@Component
public class WxServiceClientFallbackImpl implements WxServiceClient {

  private Throwable throwable;

  @Override
  public ResponseBean<?> pushNews(ParkNewsReq parkNewsReq) {
    log.error("Feign send message failed:  {},{} ", parkNewsReq, throwable);
    return null;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public void setThrowable(Throwable throwable) {
    this.throwable = throwable;
  }
}
