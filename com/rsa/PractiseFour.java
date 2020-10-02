package rsa;

/**
 * @ClassName Test2
 * @Description TODO
 * @Author boy
 * @Date 2019/8/12 6:06 PM
 */
public class PractiseFour {
    public static void main(String args[]) {
        int numInput = 900;
        factor(numInput);
    }

    public static void factor(int num) {
        double sqr = Math.sqrt(num);
        System.out.println("因数分解结果：");
        for (int i = 2; i <= sqr; i++) {
            if (num % i == 0) {
                System.out.print(i + " ");
                num /= i;
                i--;
            }
        }
    }
}
