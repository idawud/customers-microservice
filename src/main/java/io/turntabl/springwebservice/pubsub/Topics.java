package io.turntabl.springwebservice.pubsub;

import org.springframework.data.redis.listener.ChannelTopic;

public class Topics {
    public static final ChannelTopic customerUpdates = new ChannelTopic("e");
    public static final ChannelTopic customerAccess = new ChannelTopic("e");
    public static final ChannelTopic customerDBQueriesResult = new ChannelTopic("e");
}
