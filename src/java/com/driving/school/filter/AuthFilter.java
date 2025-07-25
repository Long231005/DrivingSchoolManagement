package com.driving.school.filter;

import com.driving.school.model.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Không cần khởi tạo gì đặc biệt
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String uri = httpRequest.getRequestURI();
        // Cho phép truy cập các trang công khai (login, register, static resources)
        if (uri.endsWith("login.jsp") || uri.endsWith("/login") || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png")) {
            chain.doFilter(request, response);
            return;
        }

        // Kiểm tra đăng nhập
        if (session == null || session.getAttribute("user") == null) {
            httpResponse.sendRedirect("login.jsp");
            return;
        }

        // Kiểm tra quyền truy cập dựa trên vai trò
        User user = (User) session.getAttribute("user");
        String role = user.getRole();
        boolean isAuthorized = false;

        if (uri.contains("student") && "Student".equals(role)) {
            isAuthorized = true;
        } else if (uri.contains("teacher") && "Teacher".equals(role)) {
            isAuthorized = true;
        } else if (uri.contains("police") && "TrafficPolice".equals(role)) {
            isAuthorized = true;
        } else if (uri.contains("report") || uri.contains("admin")) { // Admin có thể xem báo cáo
            isAuthorized = true; // Có thể mở rộng để kiểm tra vai trò Admin
        }

        if (isAuthorized) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect("accessDenied.jsp");
        }
    }

    @Override
    public void destroy() {
        // Không cần dọn dẹp gì đặc biệt
    }
}