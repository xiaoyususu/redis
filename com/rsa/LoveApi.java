package rsa;

/**
 * @ClassName LoveApi
 * @Description TODO
 * @Author boy
 * @Date 2019/8/5 4:38 PM
 */
public class LoveApi {

    public static void main(String[] args) {
        String s1 = "\\u4e0e\\u4f60\\u76f8\\u9047\\uff0c\\u597d\\u5e78\\u8fd0\\u3002\\ua\\ua\\u676d\\u5dde\\u20\\u32\\u30\\u31\\u39\\u2e\\u35\\u2e\\u31\\u31";
        String s2 = unicode2String(s1);
        System.out.println(s2);
    }

    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

}
