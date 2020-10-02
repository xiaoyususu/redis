package rediscat;

import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * @ClassName RedisTest
 * @Description TODO
 * @Author boy
 * @Date 2019/11/8 4:24 PM
 */
public class RedisTest {

    public void testLock(){
        Jedis jedis = RedisClientService.pool.getResource();
        String key = "lockKey";
        String value = UUID.randomUUID().toString();
        int expireTime = 100;
        boolean b = RedisUtils.lockKey(jedis,key,value,expireTime);
        System.out.println("加锁 " + value + " " + b);
        jedis.close();


        Jedis jedis1 = RedisClientService.pool.getResource();
        boolean bb = RedisUtils.unLockKey(jedis,key,value);
        System.out.println("解锁 " + value + " " + bb);
        jedis1.close();
    }

    public static void main(String[] args){
//        for(int i=0;i<400;i++){
//            RedisTest redisTest =  new RedisTest();
//            redisTest.testLock();
//        }
        Jedis jedis1 = RedisClientService.pool.getResource();
        String s = jedis1.ping();
        System.out.println(s);
    }
}
