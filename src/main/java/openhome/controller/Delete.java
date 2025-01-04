package openhome.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import openhome.model.Blah;
import openhome.model.UserService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/delete.do")
public class Delete extends HttpServlet {
    private final String LOGIN_VIEW = "index.jsp";
    private final String SUCCESS_VIEW = "member.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取全局唯一的ServletContext对象
//        ServletContext context = getServletContext();
//
////        //未登录的话重定向到登录界面
//        if (req.getSession().getAttribute("login")==null){
//            resp.sendRedirect(LOGIN_VIEW);
//            return ;
//        }
        String username =(String) req.getSession().getAttribute("login");
        String message = req.getParameter("message");
        UserService userService = (UserService) getServletContext().getAttribute("userService");

        Blah blah = new Blah();
        blah.setName(username);
        blah.setDate(new Date(Long.parseLong(message)));
        // userService.deleteMessage(username, message);
        try {
            userService.deleteBlah(blah);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            req.getSession().setAttribute("blahs", userService.getBlahs(blah));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(SUCCESS_VIEW);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
