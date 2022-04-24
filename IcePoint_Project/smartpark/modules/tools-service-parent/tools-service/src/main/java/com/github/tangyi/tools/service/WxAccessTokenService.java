package com.github.tangyi.tools.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.excel.util.StringUtils;
import com.github.tangyi.common.core.exceptions.CommonException;
import com.github.tangyi.common.core.utils.redis.RedisLockUtil;
import com.github.tangyi.core.common.util.JsonUtils;
import com.github.tangyi.tools.Constants;
import com.github.tangyi.tools.dto.resp.WxMpAccessTokenResp;
import com.github.tangyi.tools.properties.WxAccessTicketProperties;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class WxAccessTokenService {

    @Autowired
    WxAccessTicketProperties wxAccessTicketProperties;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final ThreadLocal<Integer> tl = new ThreadLocal<Integer>();

    /**
     * 获取token
     * @return
     * @throws CommonException
     */
    public WxMpAccessTokenResp getAccessToken() throws CommonException {
        WxMpAccessTokenResp accessToken ;
        try {
            accessToken = this.getRetryAccessToken();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            if (e instanceof CommonException) {
                throw (CommonException) e;
            }
            throw new CommonException("获取微信公众号token失败");
        }
        return accessToken;
    }

    /**
     * token 获取
     */
    private WxMpAccessTokenResp getRetryAccessToken() {
        if (tl.get() == null) {
            tl.set(3);
            WxMpAccessTokenResp token = getAccessTokenSub();
            tl.remove();
            return token;
        }
        return null ;
    }

        private WxMpAccessTokenResp  getAccessTokenSub () {
            WxMpAccessTokenResp accessToken = null;
            String requestId = UUID.randomUUID().toString();
            String key = Constants.WX_ACCESS_LOCK_KEY + ":" + requestId;
            try {
                //如果没有获取到分布式锁，等100毫秒，继续获取
                while (!RedisLockUtil.tryLock(key, TimeUnit.SECONDS, 1, 180)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                        e.printStackTrace();
                    }
                }
                String  value = stringRedisTemplate.opsForValue().get(Constants.WX_ACCESS_TOKEN_KEY);
                if (!StringUtils.isEmpty(value)) {
                    accessToken = JsonUtils.toObject(value,WxMpAccessTokenResp.class);
                    if (!StringUtils.isEmpty(accessToken.getErrcode())) {
                        accessToken = null;
                    }
                    //调微信公众平台获取ip的api,测试access_token是否有效。为了保证access_token 一定能用。（解决accessToken没过期，但失效的问题）
                    Map<String, Object> map = (Map<String, Object>) restTemplate.getForObject(String.format(Constants.TEST_TOKEN_ADDRESS, accessToken.getAccessToken()), Map.class);
                    log.info("测试accessToken:{}", JSONObject.valueToString(map));
                    if (map.get("errcode") != null) {
                        accessToken = null ;
                    }
                }
                if (accessToken == null) {
                    String result = HttpUtil.get(String.format(Constants.TOKEN_ADDRESS, wxAccessTicketProperties.getAppId(), wxAccessTicketProperties.getAppSecret()));
                    if (!StringUtils.isEmpty(result)) {
                        accessToken = WxMpAccessTokenResp.fromJson(result);
                    }
                    if (accessToken != null && accessToken.getErrcode() != null) {
                        log.error("token 获取异常" + (accessToken.getErrmsg() == null ? accessToken.getErrcode() : accessToken.getErrmsg()));
                        throw new CommonException("token 获取异常" + (accessToken.getErrmsg() == null ? accessToken.getErrcode() : accessToken.getErrmsg()));
                    }
                    stringRedisTemplate.opsForValue().set(Constants.WX_ACCESS_TOKEN_KEY, JsonUtils.toString(accessToken), accessToken.getExpiresIn(),TimeUnit.SECONDS);
                    Integer num = tl.get();
                    num = num - 1;
                    tl.set(num);
                }

            } catch (RestClientException e) {
                log.error(e.getMessage(), e);
                throw new CommonException("获取微信公众号token失败");
            } finally {
                //释放锁
                RedisLockUtil.unlock(key);
            }
            //递归获取access_token
            if (tl.get() > 0) {
                if (accessToken == null) {
                    accessToken = getAccessTokenSub();
                }
            }
            return accessToken;
        }
}
