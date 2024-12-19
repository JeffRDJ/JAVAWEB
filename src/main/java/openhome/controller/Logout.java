package openhome.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout.do")
public class Logout extends HttpServlet {
    // 定义登录的路径
      private final String LOGIN_VIEW = "index.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 判断是否登录 检查当前会话（session）中是否存在名为"login"的属性
        if (req.getSession().getAttribute("login")!=null){
            // 如果存在，说明用户已经登录，现在需要注销
            // 使当前会话失效，这将清除会话中的所有属性，并在下一次请求时创建一个新的会话
            req.getSession().invalidate();
        }
        // 重定向到登录页面
        resp.sendRedirect(LOGIN_VIEW);
    }
}
