package rediscat;

import redis.clients.jedis.Jedis;

/**
 * @ClassName RedisUtil
 * @Description TODO
 * @Author boy
 * @Date 2019/5/19 4:12 PM
 */
public class RedisUtil {
    public static long getInc(Jedis jedis, String key) {
        return jedis.incr(key);
    }
}
