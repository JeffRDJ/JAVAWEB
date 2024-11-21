package openhome.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@WebServlet("/register.do")
public class Register extends HttpServlet {
    private final String USERS = "D:\\Intellij IDEA Community   IJ\\endingwork\\users";
    private final String SUCCESS_VIEW = "success.view";
    private final String ERROR_VIEW = "error.view";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收数据
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmedPasswd = req.getParameter("confirmedPasswd");
        List<String> errors = new ArrayList<>();// 创建错误记录列表
        //验证邮件格式
        if (isInvalidEmail(email)) {
            errors.add("未填写邮件或者邮件格式不正确");
        }
        //验证用户名
        if (isInvalidUsername(username)) {
            errors.add("使用者用户名为空或者用户名已存在");
        }
        //验证密码
        if (isInvalidPassword(password, confirmedPasswd)) {
            errors.add("请确认密码符合格式并再度确认密码");
        }
        //返回错误信息
        String resultPage = ERROR_VIEW;
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
        } else {
            resultPage = SUCCESS_VIEW;
            createUserData(email, username, password);

        }
        //请求转发
        req.getRequestDispatcher(resultPage).forward(req, resp);

    }
    //校验邮件格式
    private boolean isInvalidEmail(String email) {
        return email == null
                || !email.matches("^[_a-z0-9-]+([.]"
                + "[_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
    }
    //校验用户名
    private boolean isInvalidUsername(String username) {
        for (String file : new File(USERS).list()) {
            if (file.equals(username)) {
                return true;
            }
        }
        return false;
    }
    //校验密码
    private boolean isInvalidPassword(String password, String confirmedPasswd) {
        return password == null
                || password.length() < 6
                || password.length() > 16
                || !password.equals(confirmedPasswd);
    }
    //注册成功后创建用户数据文件夹
    private void createUserData(String email, String username, String password) throws IOException {
        File userhome = new File(USERS + "/" + username);  //每个注册的用户创建一个文件夹
        userhome.mkdir();
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(userhome + "/profile")
        );
        writer.write(email + "\t" + password);
        writer.close();
    }

}
