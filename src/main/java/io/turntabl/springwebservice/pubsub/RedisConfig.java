package io.turntabl.springwebservice.pubsub;

import io.turntabl.springwebservice.SpringWebServiceApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.topic}")
    String update_topic;

    @Value("${spring.redis.topic2}")
    public String access_topic;

    public RedisConfig() {
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }

    @Bean
    public ChannelTopic topic() {
        return new ChannelTopic(update_topic);
    }

    @Bean
    public Publisher redisMessagePublisher(){
        String args = null;
        ConfigurableApplicationContext context = SpringApplication.run(SpringWebServiceApplication.class, args);
        Publisher redisMessagePublisher = context.getBean(Publisher.class);
        return redisMessagePublisher;
    }
}
