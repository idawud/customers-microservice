package io.turntabl.springwebservice.pubsub;

import io.turntabl.springwebservice.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class Publisher implements MessagePublisher {

    private final RedisTemplate< String, Object > template;
    private final ChannelTopic topic;

    @Autowired
    public Publisher(final RedisTemplate< String, Object > template,
                     ChannelTopic topic) {
        this.template = template;
        this.topic = topic;
    }

    @Override
    public void publish(Customer customer) {
        template.convertAndSend(topic.getTopic(), customer);
    }
}