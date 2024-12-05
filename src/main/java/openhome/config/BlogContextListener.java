package openhome.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import openhome.model.UserService;


@WebListener
public class BlogContextListener implements ServletContextListener {
    @Override//初始化监听器
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("BlogContextListener contextInitialized...");
        // 获取上下文对象(用户数据文件夹)
        ServletContext context = sce.getServletContext();
        String USERS = (String) context.getInitParameter("USERS");
        System.out.println("USERS" + USERS);
        //第二步  实例化UserService
        UserService userService = new UserService(USERS);
        //第三步 将实例化的userService对象保存到ServletContext对象中
        context.setAttribute("userService", userService);


    }
}
