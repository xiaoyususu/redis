package snowflake;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName SnawflakeThreadTest
 * @Description 多线程测试snowflake算法
 * @Author boy
 * @Date 2019/5/29 8:56 PM
 */
public class SnowflakeThreadTest {
    public static void main(String[] args){
        long num = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<num;i++) {
            executorService.execute(new SnowflakeThread());
        }
        executorService.shutdown();
    }
}

class SnowflakeThread implements Runnable{
    public void run() {
        System.out.println(Snowflake.getNextId(1,2));
    }
}
