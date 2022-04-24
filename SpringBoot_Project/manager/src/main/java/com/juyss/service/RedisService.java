package com.juyss.service;

import com.alibaba.druid.util.StringUtils;
import com.juyss.common.exception.BusinessException;
import com.juyss.common.exception.code.BaseResponseCode;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: RedisService
 * @Desc: Redis服务接口
 * @package com.juyss.service
 * @project manager
 * @date 2021/1/20 16:45
 */
@Service
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 判断key是否存在
     * @param key 待判断的key
     * @return boolean
     */
    public boolean exists(String key){
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey == null) {
            throw new BusinessException(BaseResponseCode.DATA_ERROR.getCode(), "返回值为null");
        }
        return hasKey;
    }

    /**
     * 返回key还有多少秒过期
     * @param key 待判断的key
     * @return Long
     */
    public long getExpire(String key){
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        if (expire == null){
            throw new BusinessException(BaseResponseCode.DATA_ERROR.getCode(), "key 在事务使用中");
        }
        return expire;
    }

    /**
     * 设置键值对
     * @param key 键
     * @param value 值
     */
    public void set(String key,String value){
        if (StringUtils.isEmpty(key) && StringUtils.isEmpty(value)){
            throw new BusinessException(BaseResponseCode.METHODARGUMENTNOTVALIDEXCEPTION.getCode(), "键值对不能为空");
        }
        redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 获取键的值
     * @param key 目标键
     * @return 目标值
     */
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除目标键值对
     * @param key 目标键
     */
    public void del(String key) {
        if (this.exists(key)) {
            this.redisTemplate.delete(key);
        }

    }

    /**
     * 设置键值对并添加过期时间
     * @param key 目标键
     * @param value 目标值
     * @param seconds 目标过期时间
     */
    public void setAndExpire(String key, String value, long seconds) {
        this.redisTemplate.opsForValue().set(key, value);
        this.redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 查找匹配的键值对
     * @param pattern 目标键
     * @return 目标值集合
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys("*" + pattern);
    }

    /**
     * 删除匹配的目标键值
     * @param pattern 匹配字符
     */
    public void delKeys(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (!CollectionUtils.isEmpty(keys)) {
            this.redisTemplate.delete(keys);
        }
    }


}
