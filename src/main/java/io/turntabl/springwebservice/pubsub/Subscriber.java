package io.turntabl.springwebservice.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.net.URISyntaxException;

public class Subscriber extends RedisConnection{

    public static void onMessage() throws URISyntaxException {

        Jedis jSubscriber = getPool().getResource();
        jSubscriber.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                // handle message
                System.out.println("log me.......");
            }
        }, "customers","projects");
    }
}
