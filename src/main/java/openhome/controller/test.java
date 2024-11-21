package openhome.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test.do")
public class test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username="";
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html");
        out.println("<html>");
        out.println("<head>");
        out.println("  <meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
        out.println("<title>Gossip 微网志</title>");
        out.println("<link rel='stylesheet' href='css/member.css' type='text/css'>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='leftPanel'>");
        out.println("<img src='images/caterpillar.jpg' alt='Gossip 微网志' /><br><br>");
        out.println("<a href='logout.do?username=" + username + "'>登出 " + username + "</a>");
        out.println("</div>");
        out.println("<form method='post' action='message.do'>");
        out.println("分享新鲜事...<br>");
        out.println("<textarea cols='60' rows='4' name='blabla'></textarea>");
        String blabla = req.getParameter("blabla");
        System.out.println("blabla=" + blabla);

    }
}
