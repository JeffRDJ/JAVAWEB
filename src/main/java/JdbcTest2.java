import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTest2 {
    public void initConnection() throws Exception
    {   //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取数据库的链接
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/blog", "root", "1234");
        System.out.println("数据库连接成功！ conn = " + conn);

    }

    public static void main(String[] args) throws Exception {
        JdbcTest2 jdbc = new JdbcTest2();
        jdbc.initConnection();
    }
}
