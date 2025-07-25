package com.driving.school.servlet;

import com.driving.school.dao.RegistrationDAO;
import com.driving.school.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/registerCourse")
public class RegistrationServlet extends HttpServlet {
    private RegistrationDAO registrationDAO = new RegistrationDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && "Student".equals(user.getRole())) {
            int userId = user.getUserId();
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            registrationDAO.registerCourse(userId, courseId);
            response.sendRedirect("studentDashboard.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}