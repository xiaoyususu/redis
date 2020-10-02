package rsa;

/**
 * @ClassName TestPrime
 * @Description TODO
 * @Author boy
 * @Date 2019/8/12 4:07 PM
 */

import java.util.Scanner;

public class TestPrime {

    /**
     * 判断素数
     *
     * @param i 要判断的数
     * @return 是素数返回true
     */
    private static boolean isPrime(long i) {

        boolean isPrime = true;

        //除到i的平方根就可以判断
        for (long k = 2; k <= Math.sqrt(i); k++) {

            if (i % k == 0) {
                isPrime = false;
            }

        }
        return isPrime;
    }

    public static void main(String[] args) {

        System.out.println(Math.pow(2, 29));
        System.out.println(3600 * 24);

//        Scanner in = new Scanner(System.in);
//
//        int n = in.nextInt();
//
        long n = 9999999999999999l;
        StringBuilder out = new StringBuilder(n + "=");


        if (isPrime(n)) {
            // 当读到的就是素数时，输出它本身：n=n
            out.append(n);

        } else {
            // 当读到的不是素数时，输出它的质因数分解式：n=axbxcxd
            while (n != 1) {
                for (int j = 2; j <= n; j++) {

                    // 对最后一个进行特殊处理
                    if (j == n) {
                        n = 1;
                        out.append(j);
                        break;
                    }
                    // j为当前n的第一个因数时，一定是n的质因数
                    if (n % j == 0) {
                        n = n / j;
                        out.append(j).append("x");
                        break;
                    }
                }
            }
        }
        System.out.println(out);
//        in.close();
    }

}
