package snowflake;

/**
 * @ClassName Snawflake
 * @Description TODO
 * @Author boy
 * @Date 2019/5/28 9:22 PM
 */
public class Snawflake {
    static int seq; //自增序列
    static long lastTime; //上次序列生成时间
    static int codeMv = 12;//机器码位移量
    static int timeMv = 22;//时间戳位移量
    static int code = 5;//机器码
    static long time = 1514736000000l;


    public static synchronized long getId() {
        long currentTime = System.currentTimeMillis();
        seq = getSeq(currentTime, seq);
        long id = (currentTime - time) << timeMv | code << codeMv | seq;
        return id;
    }

    public static int getSeq(long currentTime, int seq) {
        if (lastTime == currentTime) {
            seq++;
        } else if (lastTime < currentTime) {
            lastTime = currentTime;
            seq = 0;
        }
        return seq;
    }
}
