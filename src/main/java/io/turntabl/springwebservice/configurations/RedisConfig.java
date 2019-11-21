package io.turntabl.springwebservice.configurations;

import io.turntabl.springwebservice.models.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    @Bean
    public RedisConnectionFactory jedisConnectionFactory(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.maxActive = 10;
        poolConfig.maxIdle = 5;
        poolConfig.minIdle = 1;
        poolConfig.testOnBorrow = true;
        poolConfig.testOnReturn = true;
        poolConfig.testWhileIdle = true;
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig);
        return jedisConnectionFactory;
    }


    @Bean
    RedisTemplate<String, Customer> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Customer> template = new RedisTemplate<String, Customer>();
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }

    @Bean
    public ChannelTopic topic() {
        return RedisTopics.customerUpdates;
    }

}
