package openhome.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import openhome.model.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


@WebListener
public class BlogContextListener implements ServletContextListener {
//    @Override//初始化监听器
//    public void contextInitialized(ServletContextEvent sce) {
//        System.out.println("BlogContextListener contextInitialized...");
//        // 获取上下文对象(用户数据文件夹)
//        ServletContext context = sce.getServletContext();
//        String USERS = (String) context.getInitParameter("USERS");
//        System.out.println("USERS" + USERS);
//        //第二步  实例化UserService
//        AccountDAO accountDAO = new AccountDAOFileImpl(USERS);
//        UserService userService = new UserService(USERS);
//        //第三步 将实例化的userService对象保存到ServletContext对象中
//        context.setAttribute("userService", userService);
//
//
//
//        //第一步 初始化users目录（数据库存储）
//
//    }

    public void contextInitialized(ServletContextEvent sce)  {
        ServletContext context = sce.getServletContext();
//         String USERS = context.getInitParameter("USERS");
        //第1步： USERS测试(使用文件存储)
//         System.out.println("USERS-->"+USERS);

        //第1步： 建立与数据库的连接，实例化Connection类 (使用数据库存储，JDBC)
        // 加载 db.properties 文件
        Properties dbProperties = new Properties();
        Connection conn = null;
        try (InputStream input = context.getResourceAsStream("/WEB-INF/db.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties.");
                return;
            }
            dbProperties.load(input);
            System.out.println("Database properties loaded successfully." );
            String url = dbProperties.getProperty("db.url");
            String username = dbProperties.getProperty("db.username");
            String password = dbProperties.getProperty("db.password");
            // 1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");// 推荐使用这种方式来加载驱动
            // 2.获取与数据库的链接
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功！ conn = " + conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //第2步： 实例化UserService
        // DAO重构后，暂时使用文件存取数据
        /*AccountDAO accountDAO = new AccountDAOFileImpl(USERS);
        BlahDAOFileImpl blahDAO = new BlahDAOFileImpl(USERS);*/
        // 使用JDBC实现类
        AccountDAO accountDAO =  new AccoutDAOJdbcImpl(conn);
        BlahDAO blahDAO = new BlahDAOJdbcImpl(conn);
        //初始化UserService
        UserService userService = new UserService(accountDAO, blahDAO);

        // 第3步： 将userService对象放入ServletContext中
        context.setAttribute("userService", userService);
    }
}

