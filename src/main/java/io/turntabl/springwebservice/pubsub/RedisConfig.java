package io.turntabl.springwebservice.pubsub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    String redisHost;

    @Value("${spring.redis.port}")
    String redisPort;

    @Value("${spring.redis.topic}")
    String update_topic;

    @Value("${spring.redis.topic2}")
    String access_topic;
}
