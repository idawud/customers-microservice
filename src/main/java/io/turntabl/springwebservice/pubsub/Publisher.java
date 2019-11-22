package io.turntabl.springwebservice.pubsub;

import redis.clients.jedis.Jedis;

import java.net.URISyntaxException;


public class Publisher extends RedisConnection {

    public static void publish(String message){
        try (
                Jedis jedis = getPool().getResource()){
            jedis.publish("customers",  "Message : " + message);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
