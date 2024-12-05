package openhome.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import openhome.model.UserService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/register.do")
public class Register extends HttpServlet {
    private final String SUCCESS_VIEW = "success.view";
    private final String ERROR_VIEW = "error.view";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收数据
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmedPasswd = req.getParameter("confirmedPasswd");
        //获取全局唯一的ServletContext对象
        ServletContext context = getServletContext();
        UserService userService = (UserService) context.getAttribute("userService");

        List<String> errors = new ArrayList<>();// 创建错误记录列表
        //验证邮件格式
        if (userService.isInvalidEmail(email)) {
            errors.add("未填写邮件或者邮件格式不正确");
        }
        //验证用户名
        if (userService.isInvalidUsername(username)) {
            errors.add("使用者用户名为空或者用户名已存在");
        }
        //验证密码
        if (userService.isInvalidPassword(password, confirmedPasswd)) {
            errors.add("请确认密码符合格式并再度确认密码");
        }
        //返回错误信息
        String resultPage = ERROR_VIEW;
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
        } else {
            resultPage = SUCCESS_VIEW;
            userService.createUserData(email, username, password);

        }
        //请求转发
        req.getRequestDispatcher(resultPage).forward(req, resp);

    }


}
