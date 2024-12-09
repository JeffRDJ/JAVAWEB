package openhome.controller;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import openhome.model.UserService;

import java.io.*;


@WebServlet("/login.do")
public class Login extends HttpServlet {
    private final String SUCCESS_VIEW = "member.view";
    private final String ERROR_VIEW = "index.html";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String page = ERROR_VIEW;
        //获取全局唯一的ServletContext对象
        ServletContext context = getServletContext();
        UserService userService = (UserService) context.getAttribute("userService");
        //校验用户名和密码
        if (userService.checkLogin(username, password)) {
            req.getSession().setAttribute("login", username);
            //
            page = SUCCESS_VIEW;
            resp.sendRedirect(page);
        } else {
            //sendRedirect：这是 HttpServletResponse 接口的一个方法，用于发送一个即时的重定向命令到客户端。
            out.println("用户名或密码错误！");
            resp.sendRedirect("index.html");
        }


    }



}
