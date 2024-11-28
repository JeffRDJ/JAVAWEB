package openhome.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

@WebServlet("/message.do")
public class Message extends HttpServlet {
    private final String USERS = "D:\\Intellij IDEA Community   IJ\\endingwork\\users";
    private final String LOGIN_VIEW = "index.html";
    private final String SUCCESS_VIEW = "member.view";
    private final String ERROR_VIEW = "member.view";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        // 檢查是否已登入
        if (req.getSession().getAttribute("login") == null) {
            resp.sendRedirect(LOGIN_VIEW);
            return;
        }

        String blabla = req.getParameter("blabla");

        if (blabla != null && blabla.length() != 0) {
            // 留言字數是否超过140字
            if (blabla.length() < 140) {
                // 获取登录的用户名
                String username = (String) req.getSession().getAttribute("login");
                //微博信息持久化
                addMessage(username, blabla);
                //返回登录成功界面
                resp.sendRedirect(SUCCESS_VIEW);
            }// 超过140字返回登录成功界面
            else {
                req.getRequestDispatcher(ERROR_VIEW).forward(req, resp);
            }
        } else {
            req.getRequestDispatcher(ERROR_VIEW).forward(req, resp);
        }
    }

    private void addMessage(String username, String blabla) throws IOException {
        String file = USERS + "/" + username + "/" + new Date().getTime() + ".txt";
        BufferedWriter write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        write.write(blabla);
        write.close();
    }


}
