package snowflake;

/**
 * @ClassName SnowflakeTest
 * @Description 单线程测试snowflake算法的性能
 * @Author boy
 * @Date 2019/6/11 7:40 PM
 */
public class SnowflakeTest {

    public static void main(String args[]){
        long startTime = System.currentTimeMillis();
        System.out.println("startTime---------------------------------------" + startTime);
        long num = 100000000l;
        for(int i=0;i<num;i++){
            Snowflake.getNextId(1,2);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("endTime---------------------------------------" + endTime);
        long timeQuantum = startTime - endTime;
        System.out.println("(startTime-endTime)---------------------------------------" + timeQuantum);
        System.out.println("(num/(startTime-endTime))---------------------------------------" + num/timeQuantum);
    }
}
