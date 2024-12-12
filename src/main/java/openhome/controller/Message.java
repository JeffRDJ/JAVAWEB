package openhome.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import openhome.model.Blah;
import openhome.model.UserService;

import java.io.*;
import java.util.*;

@WebServlet("/message.do")
public class Message extends HttpServlet {
    private final String LOGIN_VIEW = "index.html";
    private final String SUCCESS_VIEW = "member.jsp";
    private final String ERROR_VIEW = "member.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取全局唯一的ServletContext对象
        ServletContext context = getServletContext();
        UserService userService = (UserService) context.getAttribute("userService");

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
                Blah blah = new Blah(username, new Date(), blabla);
                userService.addBlah(blah);
                //更行登录用户微博信息
                req.getSession().setAttribute("blahs", userService.getBlahs(blah));
                //返回登录成功界面
//                resp.sendRedirect(SUCCESS_VIEW);
                req.getRequestDispatcher(SUCCESS_VIEW).forward(req, resp);
            }// 超过140字返回登录成功界面
            else {
                req.getRequestDispatcher(ERROR_VIEW).forward(req, resp);
            }
        } else {
            req.getRequestDispatcher(ERROR_VIEW).forward(req, resp);
        }
    }


}
