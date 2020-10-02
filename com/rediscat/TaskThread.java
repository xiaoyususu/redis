package rediscat;

/**
 * @ClassName TaskThread
 * @Description TODO
 * @Author boy
 * @Date 2019/11/8 8:27 PM
 */


public class TaskThread extends Thread {
    private RedisTest redisTest;

    public TaskThread(RedisTest redisTest) {
        this.redisTest = redisTest;
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                redisTest.testLock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RedisTest service = new RedisTest();
        for (int i = 0; i < 400; i++) {
            TaskThread thread = new TaskThread(service);
            thread.start();
        }
    }

}