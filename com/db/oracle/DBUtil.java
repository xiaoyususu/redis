package db.oracle;

/**
 * @ClassName Test
 * @Description TODO
 * @Author boy
 * @Date 2019/12/3 9:20 PM
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
//    private static String username="CXPAY_SAAS_READ";
//    private static String password="wtr3p4#19";
//    private static String diver="oracle.jdbc.OracleDriver";
//    private static String url="jdbc:oracle:thin:@10.1.11.85:1521:CXPAY";//如果是服务器则这儿应该改成ip:1521:xe
    private static String username="FINANCE";
    private static String password="finecip$1126";
    private static String diver="oracle.jdbc.OracleDriver";
    private static String url="jdbc:oracle:thin:@10.1.1.81:1521:CXPAY_SAAS";//如果是服务器则这儿应该改成ip:1521:xe

    public static Connection getConnection() {
        Connection connection=null;
        try {
            Class.forName(diver);
            connection=DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("class not find!", e);
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            throw new RuntimeException("get connection error!", e2);
        }
        return connection;

    }



    public static void main(String[] args){
        Connection connection = getConnection();

    }
}