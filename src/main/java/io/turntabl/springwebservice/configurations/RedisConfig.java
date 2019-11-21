package io.turntabl.springwebservice.configurations;

import io.turntabl.springwebservice.models.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RedisConfig {
    private static Jedis getConnection() throws URISyntaxException {
        URI redisURI = new URI(System.getenv("REDIS_URL"));
        Jedis jedis = new Jedis(redisURI);
        return jedis;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(5);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(poolConfig); //(sentinel,pool)
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPort(6379);
        return jedisConnectionFactory;

    }


    @Bean
    RedisTemplate<String, Customer> redisTemplate() {
        RedisTemplate<String, Customer> template = new RedisTemplate<String, Customer>();
        //template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    public ChannelTopic topic() {
        return RedisTopics.customerUpdates;
    }

}
