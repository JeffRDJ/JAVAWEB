package openhome.view;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.text.DateFormat;
import java.util.*;

//@WebServlet("/member.view")
//public class Member extends HttpServlet {
//    private final String USERS="D:\\Intellij IDEA Community   IJ\\endingwork\\users";// 用户目录
//    private final String LOGIN_VIEW="index.html";// 前端页面的路径
//    private void processRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
//
//
//
//    }
//}
@WebServlet("/member.view")
public class Member extends HttpServlet {
    private final String USERS = "c:/workspace/Gossip/users";
    private final String LOGIN_VIEW = "index.html";

    protected void processRequest(HttpServletRequest req,
                                  HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("login") == null) {
            resp.sendRedirect(LOGIN_VIEW);
            return;
        }

        String username = (String) req.getSession().getAttribute("login");

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
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
        out.println("<a href='logout.do?username=" + username + "'>登出 " + username + "</a>");
        out.println("</div>");
        out.println("<form method='post' action='message.do'>");
        out.println("分享新鲜事...<br>");

        String blabla = req.getParameter("blabla");
        if (blabla == null) {
            blabla = "";
        } else {
            out.println("讯息要 140 字以内<br>");
        }
        out.println("<textarea cols='60' rows='4' name='blabla'>" + blabla + "</textarea>");
        out.println("<br>");
        out.println("<button type='submit'>送出</button>");
        out.println("</form>");
        out.println("<table style='text-align: left; width: 510px; height: 88px;' border='0' cellpadding='2' cellspacing='2'>");
        out.println("<thead>");
        out.println("<tr><th><hr></th></tr>");
        out.println("</thead>");
        out.println("<tbody>");


        Map<Date, String> messages = readMessage(username);
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, Locale.TAIWAN);

        for (Date date : messages.keySet()) {
            out.println("<tr><td style='vertical-align: top;'>");
            out.println(username + "<br>");
            out.println(messages.get(date) + "<br>");
            out.println(dateFormat.format(date));
            out.println("<a href='delete.do?message=" + date.getTime() + "'>删除</a>");
            out.println("<hr></td></tr>");
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("<hr style='width: 100%; height: 1px;'>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }

    private class TxtFilenameFilter implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".txt");
        }
    }

    private TxtFilenameFilter filenameFilter = new TxtFilenameFilter();

    private class DateComparator implements Comparator<Date> {
        @Override
        public int compare(Date d1, Date d2) {
            return -d1.compareTo(d2);
        }
    }

    private DateComparator comparator = new DateComparator();

    private Map<Date, String> readMessage(String username) throws IOException {
        File border = new File(USERS + "/" + username);
        String[] txts = border.list(filenameFilter);

        Map<Date, String> messages = new TreeMap<Date, String>(comparator);
        for (String txt : txts) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(USERS + "/" + username + "/" + txt), "UTF-8"));
            String text = null;
            StringBuilder builder = new StringBuilder();
            while ((text = reader.readLine()) != null) {
                builder.append(text);
            }
            Date date = new Date(Long.parseLong(txt.substring(0, txt.indexOf(".txt"))));
            messages.put(date, builder.toString());
            reader.close();
        }

        return messages;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}

