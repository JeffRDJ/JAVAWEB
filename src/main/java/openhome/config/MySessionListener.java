package openhome.config;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;



public  class MySessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("HttpSession 已创建 ");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("HttpSession 已销毁");
    }
}
