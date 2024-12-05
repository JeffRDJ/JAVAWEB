package openhome.view;



import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import openhome.model.UserService;

@WebServlet("/member.view")
public class Member extends HttpServlet {
    private final String LOGIN_VIEW = "index.html";

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("login") == null) {
            response.sendRedirect(LOGIN_VIEW);
            return;
        }
        //获取全局唯一的ServletContext对象
        ServletContext context = getServletContext();
        UserService userService = (UserService) context.getAttribute("userService");
        //获取当前登录用户名
        String username = (String) request.getSession().getAttribute("login");
        // 显示图片
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
        out.println("<html>");
        out.println("<head>");
        out.println("  <meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
        out.println("<title>Gossip 微网志</title>");
        out.println("<link rel='stylesheet' href='css/member.css' type='text/css'>");
        out.println("</head>");
        out.println("<body>");


        out.println("<div class='leftPanel'>");
        out.println("<img src='images/caterpillar.jpg' alt='Gossip 微网志' /><br><br>");
        /* 注销链接*/
        out.println("<a href='logout.do?username="+username+"'>注销"+username+"</a><br>");
        out.println("</div>");

        out.println("<div style=\"float: left;\">");
        out.println("<form method='post' action='message.do'>");
        out.println("分享新鲜事...<br>");
        // 文本框
        out.println("讯息要 140 字以内<br>");
        out.println("<textarea cols='40' rows='5' name='blabla'></textarea>");
//        /*  实作步骤2 */
//      //发布按钮
        out.println("<br>");
        out.println("<button type='submit'>送出</button>");
        out.println("</form><p>");

          //以下区域显示聊天记录
        out.println("<table style='text-align: left; width: 510px; height: 88px;' border='0' cellpadding='2' cellspacing='2'>");
        out.println("<tbody>");
//        /*  实作步骤3 */
        /*  从users文件夹下读取当前会员的所有信息 */
        Map<Date, String> messages = userService.readMessage(username);
        for(Map.Entry<Date, String> entry : messages.entrySet()) {
            Date date = entry.getKey();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String strDate = sdf.format(date);
            String message = entry.getValue();
            //删除信息
            out.println("<hr><tr>" + username + "<br>"+ message + "<br>" + strDate +"<a href = 'delete.do?message="+date.getTime()+"'>  q删除</a>" +"</tr><p>");
        }

        out.println("<hr></tbody>");
        out.println("</table>");
        out.println("</dir>");
        out.println("<hr style='width:100%; height:1px;'>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
