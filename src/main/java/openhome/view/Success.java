package openhome.view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/success.view")
public class Success extends HttpServlet {
    @Override
    //注册成功时进行反馈
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("  <meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
        out.println("  <title>会员注册成功</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>会员 " + req.getParameter("username") + " 注册成功</h1>");
        out.println("<a href='index.html'>回首页登入</a>");
        out.println("</body>");
        out.println("</html>");

        out.close();


    }
}
