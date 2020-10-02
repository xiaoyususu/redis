package rsa;

import java.util.Base64;

/**
 * @ClassName TestBase64
 * @Description TODO
 * @Author boy
 * @Date 2020/9/3 4:29 PM
 */
public class TestBase64 {
    public static byte[] decode(String content){
        return Base64.getDecoder().decode(content);
    }
    public static void main(String[] args){
        System.out.println(decode("     中国 人"));
    }
}
