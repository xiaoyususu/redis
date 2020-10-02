package rediscat;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import java.util.Collections;

/**
 * @ClassName RedisUtils
 * @Description redis分布式锁实现
 * @Author boy
 * @Date 2019/11/8 2:24 PM
 */
public class RedisUtils {

    private static final String LOCK_SUCCESS = "OK";//加锁成功
    private static final Long UNLOCK_SUCCESS = 1L;//解锁成功

    /*
     * @Author boy
     * @Description 加锁
     * @Date 2019/11/8 8:20 PM
     * @Param [jedis, key, value, expireTime]
     * @return boolean
     */
    public static boolean lockKey(Jedis jedis,String key,String value,int expireTime){
        SetParams params = SetParams.setParams();
        params.px(expireTime); //过期时间，单位是ms
        params.nx();//意思是SET IF NOT EXIST，即当key不存在时，进行set操作；若key已经存在，则不做任何操作
        String result = jedis.set(key,value, params);
        if (LOCK_SUCCESS.equals(result)){
            return true;
        }
        return false;
    }

    /*
     * @Author boy
     * @Description 解锁
     * @Date 2019/11/8 8:21 PM
     * @Param [jedis, lockKey, value]
     * @return boolean
     */
    public static boolean unLockKey(Jedis jedis, String lockKey, String value) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey),Collections.singletonList(value));
        if (UNLOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }


}
