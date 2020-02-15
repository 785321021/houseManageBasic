package com.coins.configure;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
//在这里设置Session过期时间，单位：秒
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 7200)
public class RedisSessionConfig {
		@Bean
		CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        /* 默认配置， 默认超时时间为30分钟 */
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration
                .ofSeconds(1800L)).disableCachingNullValues();
        RedisCacheManager cacheManager = RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter
                (connectionFactory)).cacheDefaults(defaultCacheConfig).transactionAware().build();
        return cacheManager;
    }

}
