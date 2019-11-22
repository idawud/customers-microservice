package io.turntabl.springwebservice.configurations;

import io.turntabl.springwebservice.models.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RedisConfig {
    /*
    @Bean
    public Jedis getConnection() throws URISyntaxException {
        URI redisURI = new URI(System.getenv("REDIS_URL"));
        Jedis jedis = new Jedis(redisURI);
        return jedis;
    }*/

    /*
    @Bean
    private JedisConnectionFactory jedisConnectionFactory(){

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
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Value("${spring.redis.topic}")
    private String customers_topic;

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic(customers_topic);
    }
    */
}
