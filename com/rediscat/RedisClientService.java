package rediscat;


import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * @ClassName RedisClientService
 * @Description TODO
 * @Author boy
 * @Date 2019/11/8 4:05 PM
 */


public class RedisClientService {
    public static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(500);
        // 设置最大空闲数
        config.setMaxIdle(100);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "www.diaosiit.com", 6379, 300000);
    }

}
