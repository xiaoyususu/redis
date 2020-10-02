package ip;

import java.net.InetAddress;

/**
 * @ClassName IpTest
 * @Description TODO
 * @Author boy
 * @Date 2020/8/11 3:27 PM
 */
public class IpTest {


        public static void main(String[] args) {
            // TODO Auto-generated method stub
            InetAddress ia=null;
            try {
                ia=ia.getLocalHost();

                String localname=ia.getHostName();
                String localip=ia.getHostAddress();
                System.out.println("本机名称是："+ localname);
                System.out.println("本机的ip是 ："+localip);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


}
