package io.turntabl.springwebservice.pubsub;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.listener.ChannelTopic;

public class Topics {
    @Value("${spring.redis.topic}")
    private static String update_topic;

    @Value("${spring.redis.topic2}")
    private static String access_topic;

    @Value("${spring.redis.topic3}")
    private static String query_topic;

    static final ChannelTopic customerUpdates = new ChannelTopic(update_topic);
    public static final ChannelTopic customerAccess = new ChannelTopic(access_topic);
    public static final ChannelTopic customerDBQueriesResult = new ChannelTopic(query_topic);
}
