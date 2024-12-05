package openhome.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import openhome.model.UserService;

import java.io.File;
import java.io.IOException;

@WebServlet("/delete.do")
public class Delete extends HttpServlet {
    private final String LOGIN_VIEW = "index.html";
    private final String SUCCESS_VIEW = "member.view";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取全局唯一的ServletContext对象
        ServletContext context = getServletContext();
        UserService userService = (UserService) context.getAttribute("userService");
        //未登录的话重定向到登录界面
        if (req.getSession().getAttribute("login")==null){
            resp.sendRedirect(LOGIN_VIEW);
            return ;
        }
        String username =(String) req.getSession().getAttribute("login");
        String message = req.getParameter("message");
        userService.deleteMessage(userService, username, message);
        resp.sendRedirect(SUCCESS_VIEW);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
