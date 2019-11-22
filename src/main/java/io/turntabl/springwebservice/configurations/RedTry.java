package io.turntabl.springwebservice.configurations;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;
import java.net.URISyntaxException;

public class RedTry {
    public static JedisPool getPool() throws URISyntaxException {
        URI redisURI = new URI(System.getenv("REDIS_URL"));
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(5);
        poolConfig.setMinIdle(1);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        JedisPool pool = new JedisPool(poolConfig, redisURI);
        return pool;
    }

    public static void publish(String message){
        try (
                Jedis jedis = getPool().getResource()){
            //jedis.set("foo", "bar");
            jedis.publish("customers", message);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
