
package com.github.tangyi.gateway.filters;

import com.github.tangyi.common.core.exceptions.SensitiveWordsException;
import com.github.tangyi.common.core.utils.Assert;
import com.github.tangyi.gateway.constants.GatewayConstant;
import com.github.tangyi.gateway.utils.SensitiveWord;
import io.netty.buffer.ByteBufAllocator;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * @author gaokx
 * @Description 敏感词过滤
 * @create 2021-03-10 20:46
 **/

@Slf4j
@Configuration
public class SensitiveWordsFilter implements GlobalFilter , Ordered ,InitializingBean {

  static SensitiveWord sw;

  @PostConstruct
  public void initWord() {
    sw = SensitiveWord.getInstance();
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    if (isCheckMethod(request)) {
      return DataBufferUtils.join(exchange.getRequest().getBody())
          .flatMap(dataBuffer -> {
            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            try {
              String bodyString = new String(bytes, "utf-8");
              String sensitiveWord = sw.getSensitiveWordStr(bodyString);
              if (!StringUtils.isEmpty(sensitiveWord)) {
                throw new SensitiveWordsException(GatewayConstant.SENSITIVE_WORDS_ERROR +" ["
                    + sensitiveWord + "]");
              }
            } catch (UnsupportedEncodingException e) {
              e.printStackTrace();
            }
            DataBufferUtils.release(dataBuffer);
            Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
              DataBuffer buffer = exchange.getResponse().bufferFactory()
                  .wrap(bytes);
              return Mono.just(buffer);
            });

            ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
                exchange.getRequest()) {
              @Override
              public Flux<DataBuffer> getBody() {
                return cachedFlux;
              }
            };
            return chain.filter(exchange.mutate().request(mutatedRequest)
                .build());
          });
    }
    return chain.filter(exchange);
  }

  /**
   * 是否需要校验的方法类型
   */
  private boolean isCheckMethod(ServerHttpRequest request) {
    String method = request.getMethodValue();
    String contentType = request.getHeaders().getFirst("Content-Type");
    if (HttpMethod.POST.matches(method) || HttpMethod.PUT.matches(method)) {
      return (MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(contentType)
          || MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType)
          || MediaType.APPLICATION_FORM_URLENCODED_VALUE.equalsIgnoreCase(contentType));
    }
    return false;
  }

  @Override
  public int getOrder() {
    return -1;
  }

  @Override
  public void afterPropertiesSet() {
    Assert.notNull(sw, "敏感词不能为空");
    Assert.notNull(CollectionUtils.isEmpty(sw.getArrayList()), "敏感词不能为空");
  }

}

