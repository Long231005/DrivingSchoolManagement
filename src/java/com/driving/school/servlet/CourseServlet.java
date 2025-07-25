package com.driving.school.servlet;

import com.driving.school.dao.CourseDAO;
import com.driving.school.model.Course;
import com.driving.school.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet("/addCourse")
public class CourseServlet extends HttpServlet {
    private CourseDAO courseDAO = new CourseDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && "Teacher".equals(user.getRole())) {
            int teacherId = user.getUserId();
            String courseName = request.getParameter("courseName");
            Date startDate = java.sql.Date.valueOf(request.getParameter("startDate"));
            Date endDate = java.sql.Date.valueOf(request.getParameter("endDate"));

            Course course = new Course(0, courseName, teacherId, startDate, endDate);
            courseDAO.addCourse(course);
            response.sendRedirect("teacherDashboard.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}