package openhome.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


@WebServlet("/login.do")
public class Login extends HttpServlet {
    private final String USERS = "D:\\Intellij IDEA Community   IJ\\endingwork\\users";
    private final String SUCCESS_VIEW = "member.view";
    private final String ERROR_VIEW = "index.html";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String page = ERROR_VIEW;
        if (checkLogin(username, password)) {
            req.getSession().setAttribute("login", username);
            page = SUCCESS_VIEW;
        }
        resp.sendRedirect(page);


    }

    private boolean checkLogin(String username, String password) throws IOException {
        if (username != null && password != null) {
            for (String file : new File(USERS).list()) {
                if (file.equals(username)) {
                    BufferedReader reader = new BufferedReader(new FileReader(USERS + "/" +file+ "/profile"));
                    String passwd = reader.readLine().split("\t")[1];
                    if (passwd.equals(password)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
