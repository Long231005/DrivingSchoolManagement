package com.driving.school.servlet;

import com.driving.school.dao.UserDAO;
import com.driving.school.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDAO.login(email, password);
            if (user != null) {
                request.getSession().setAttribute("user", user);
                String role = user.getRole();
                switch (role) {
                    case "Student":
                        response.sendRedirect("student/dashboard.jsp");
                        break;
                    case "Teacher":
                        response.sendRedirect("teacher/dashboard.jsp");
                        break;
                    case "TrafficPolice":
                        response.sendRedirect("trafficpolice/dashboard.jsp");
                        break;
                    case "Admin":
                        response.sendRedirect("admin/dashboard.jsp");
                        break;
                    default:
                        request.setAttribute("error", "Vai trò không hợp lệ!");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Email hoặc mật khẩu không đúng!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}