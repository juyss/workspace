package com.github.tangyi.common.basic.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tangyi.common.basic.cache.CustomRedisCacheWriter;
import com.github.tangyi.common.basic.cache.MultitenantCacheManager;
import com.github.tangyi.common.core.utils.redis.DistributedLocker;
import com.github.tangyi.common.core.utils.redis.RedisLockUtil;
import com.github.tangyi.common.core.utils.redis.RedissonDistributedLocker;
import com.github.tangyi.core.common.util.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizers;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * redis配置
 *
 * @author tangyi
 * @date 2019/3/16 20:40
 */
@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(objectMapper);
        // json序列化方式 替换jdk默认序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        //redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }


    /**
     * 多租户cacheManager
     * @modify : 2021-01-09
     * @description :  json序列化方式 替换jdk默认序列化方式
     * @author : gaokx
     * @return RedisCacheManager
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory, CacheManagerCustomizers customizerInvoker) {
        RedisCacheWriter redisCacheWriter = new CustomRedisCacheWriter(connectionFactory);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();

        //json序列化方式 替换jdk默认序列化方式
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(objectMapper);
        RedisCacheConfiguration customRedisCacheConfiguration = redisCacheConfiguration.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringSerializer))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));

        Map<String, RedisCacheConfiguration> initialCacheConfigurations = new LinkedHashMap<>();
        // 多租户cacheManager
        RedisCacheManager cacheManager = new MultitenantCacheManager(redisCacheWriter, customRedisCacheConfiguration, initialCacheConfigurations, true);
        cacheManager.setTransactionAware(false);
        return customizerInvoker.customize(cacheManager);
    }

    @Bean
    public CacheManagerCustomizers cacheManagerCustomizers(
            ObjectProvider<List<CacheManagerCustomizer<?>>> customizers) {
        return new CacheManagerCustomizers(customizers.getIfAvailable());
    }

    @Value("${REDIS_HOST:127.0.0.1}")
    private String redisHost;
    @Value("${REDIS_PORT:6379}")
    private String redisPort;
    @Value("${REDIS_PASSWORD:}")
    private String redisPassWord;

    /**
     * 装配locker类，并将实例注入到Util中
     */

    @Bean
    public RedissonClient config() {
        // 创建配置实例
        Config config = new Config();
        // 传输模式
        config.setTransportMode(TransportMode.NIO);
        // 设置服务节点部署模式: 集群、单一节点/主从模式/哨兵模式
        config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort)
            .setKeepAlive(true);
        if (!org.springframework.util.StringUtils.isEmpty(redisPassWord)) {
            config.useSingleServer().setPassword(redisPassWord);
        }
        RedissonClient redissonClient =  Redisson.create(config);
        DistributedLocker locker = new RedissonDistributedLocker();
        ((RedissonDistributedLocker) locker).setRedissonClient(redissonClient);
        //设置lock
        RedisLockUtil.setLocker(locker);
        return redissonClient ;
    }

}
