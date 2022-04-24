package com.icepoint.framework.workorder.configuration.agilebpm.sys;

import com.dstz.base.core.cache.ICache;
import com.dstz.base.core.cache.impl.MemoryCache;
import com.dstz.sys.redis.RedisCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableConfigurationProperties({ RedisConfigProperties.class })
public class CacheAutoConfiguration {

    @ConditionalOnProperty(
            prefix = "ab.redis",
            name = { "use-redis-cache" },
            havingValue = "true"
    )
    @Bean
    public ICache redisCache(RedisTemplate<String, ?> redisTemplate) {
        return new RedisCache<>(redisTemplate);
    }

    @ConditionalOnProperty(
            prefix = "ab.redis",
            name = { "use-redis-cache" },
            havingValue = "false",
            matchIfMissing = true
    )
    @Bean
    public ICache memoryCache() {
        return new MemoryCache<>();
    }

    @Bean(
            name = { "taskExecutor" }
    )
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(1000);
        taskExecutor.setKeepAliveSeconds(300);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return taskExecutor;
    }
}