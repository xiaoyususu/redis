package db.oracle;

/**
 * @ClassName OracleClient
 * @Description TODO
 * @Author boy
 * @Date 2019/12/3 8:11 PM
 */
import oracle.jdbc.driver.OracleDriver;
import java.sql.*;
import java.util.Properties;
/**
 * Created by 10412 on 2016/12/27.
 * JDBC的六大步骤
 * JAVA连接Oracle的三种方式
 */
public class JdbcTest
{
    public static void main(String[] args) {
        Connection connect = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //第一步：注册驱动
            //第一种方式：类加载(常用)
            //Class.forName("oracle.jdbc.OracleDriver");

            //第二种方式：利用Driver对象
            Driver driver = new OracleDriver();
            DriverManager.deregisterDriver(driver);


            //第二步：获取连接
            //第一种方式：利用DriverManager（常用）
            //connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "你的oracle数据库用户名", "用户名密码");

            //第二种方式：直接使用Driver
            Properties pro = new Properties();
            pro.put("user", "CXPAY_SAAS_READ");
            pro.put("password", "wtr3p4#19");
            connect = driver.connect("jdbc:oracle:thin:@10.1.11.85:1521:CXPAY", pro);

            //测试connect正确与否
            System.out.println(connect);


            //第三步：获取执行sql语句对象
            //第一种方式:statement
            //statement = connect.createStatement();

            //第二种方式：PreStatement
            PreparedStatement preState = connect.prepareStatement("select  count(*) from BANK_BIND_INFO");



        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //第六步：关闭资源
            try {
                if (resultSet!=null) resultSet.close();
                if (statement!=null) statement.close();
                if (connect!=null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
