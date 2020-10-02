package ip;

/**
 * @ClassName Ipv6
 * @Description TODO
 * @Author boy
 * @Date 2020/8/11 3:32 PM
 */

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IPv6 {
    public static void main(String[] args) {
        /*获取本机所有ip地址(包括保留地址，ipv4,ipv6  如果安装了虚拟机会更多其他的地址)
         * try {
            InetAddress ads = null;
            Enumeration<NetworkInterface>   adds = NetworkInterface.getNetworkInterfaces();
            while(adds.hasMoreElements()) {
            Enumeration<InetAddress> inetAds = adds.nextElement().getInetAddresses();
                while(inetAds.hasMoreElements()) {
                    ads = inetAds.nextElement();
                    System.out.println(ads.getHostAddress());
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/




    }


}