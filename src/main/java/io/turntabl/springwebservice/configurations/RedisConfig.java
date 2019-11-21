package io.turntabl.springwebservice.configurations;

import io.turntabl.springwebservice.models.Customer;
import io.turntabl.springwebservice.pubsub.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RedisConfig {
    @Bean
    public JedisPool getJedisPool() {
        try {
            URI redisURI = new URI(System.getenv("REDISTOGO_URL"));
            return new JedisPool(new JedisPoolConfig(),
                    redisURI.getHost(),
                    redisURI.getPort(),
                    Protocol.DEFAULT_TIMEOUT,
                    redisURI.getUserInfo().split(":",2)[1]);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Redis couldn't be configured from URL in REDISTOGO_URL env var:"+
                    System.getenv("REDISTOGO_URL"));
        }
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {

        try {
            String redistogoUrl = System.getenv("REDISTOGO_URL");
            URI redistogoUri = new URI(redistogoUrl);

            JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();

            jedisConnFactory.setUsePool(true);
            jedisConnFactory.setHostName(redistogoUri.getHost());
            jedisConnFactory.setPort(redistogoUri.getPort());
            jedisConnFactory.setTimeout(Protocol.DEFAULT_TIMEOUT);
            jedisConnFactory.setPassword(redistogoUri.getUserInfo().split(":", 2)[1]);

            return jedisConnFactory;

        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    RedisTemplate< String, Customer> redisTemplate() {
        final RedisTemplate< String, Customer > template =  new RedisTemplate< String, Customer >();
        template.setConnectionFactory( redisConnectionFactory() );
        template.setKeySerializer( new StringRedisSerializer() );
        template.setHashValueSerializer( new GenericToStringSerializer< Customer >( Customer.class ) );
        template.setValueSerializer( new GenericToStringSerializer< Customer >( Customer.class ) );
        return template;
    }

    @Bean
    public ChannelTopic topic() {
        return RedisTopics.customerUpdates;
    }

}
