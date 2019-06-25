package rediscat;

import redis.clients.jedis.Jedis;

/**
 * @ClassName RedisClient
 * @Description TODO
 * @Author boy
 * @Date 2019/5/14 8:52 PM
 */
public class RedisClient {

    public static void main(String args[]){
        Jedis jedis = new Jedis("148.70.196.127");
        String key = "incrNum";
        jedis.setnx(key,"1");

        long incr = jedis.incr(key);

        System.out.println(incr);
        System.out.println(jedis.incrBy(key,2));
    }

}
