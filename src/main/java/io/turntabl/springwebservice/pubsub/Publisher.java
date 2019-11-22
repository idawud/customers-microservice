package io.turntabl.springwebservice.pubsub;

import redis.clients.jedis.Jedis;
import java.net.URISyntaxException;
import java.time.LocalDateTime;


public class Publisher extends RedisConnection {

    public static void publish(String message){
        try (
                Jedis jedis = getPool().getResource()){
            jedis.publish("customers", LocalDateTime.now().toString() + " : " + message);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
