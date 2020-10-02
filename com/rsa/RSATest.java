package rsa;

/**
 * @ClassName RSATest
 * @Description TODO
 * @Author boy
 * @Date 2019/8/9 5:55 PM
 */
public class RSATest {

    public static void main(String args[]) {

//        System.out.println(privateKey(3,20));
        long j = 9999786868676768l;
        long num = 0;

        for (long i = 2; i < j; i++) {

            if (j % i == 0) {
                num++;
                System.out.println(num);
            }
            System.out.println(i);
        }
        System.out.println("------------" + num);

//        System.out.println(encrypt(4,33,3));
//        System.out.println(decode(encrypt(4,33,3),33,7));
//        System.out.println(Math.pow(32,29));

    }

    private static long privateKey(long E, long X) {
        long privateKey = 0;
        for (int i = 0; i < 1000000000; i++) {
            if (((E * i) % X - 1) == 0) {
                privateKey = i;
                break;
            }

        }
        return privateKey;
    }

    private static long encrypt(long M, long N, long E) {
        return ((long) Math.pow(M, E)) % N;
    }

    private static double decode(long C, long N, long D) {
        return (Math.pow(C, D)) % N;
    }
}
