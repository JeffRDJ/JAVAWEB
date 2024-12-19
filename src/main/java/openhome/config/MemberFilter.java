package openhome.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// 规定拦截的请求地址
@WebFilter(urlPatterns = {"/message.do", "/delete.do", "/member.jsp", "/logout.do"},
        initParams = {
                @WebInitParam(name = "LOGIN_VIEW", value = "index.jsp")
        }
)
public class MemberFilter implements Filter {
    private String LOGIN_VIEW;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException, ServletException {
        Filter.super.init(filterConfig);
        LOGIN_VIEW = filterConfig.getInitParameter("LOGIN_VIEW");//设置登录页
        System.out.println("LOGIN_VIEW-->" + LOGIN_VIEW);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MemberFilter执行前。。。");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (session.getAttribute("login") == null) { // 会员登录检测
            // 会员未登录，跳转index.html
            resp.sendRedirect(LOGIN_VIEW);
        } else {
            // 会员已登录，放行
            filterChain.doFilter(servletRequest, servletResponse);
        }
        System.out.println("MemberFilter执行后。。。");
    }
}

