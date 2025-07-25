package com.driving.school.servlet;

import com.driving.school.dao.NotificationDAO;
import com.driving.school.model.Notification;
import com.driving.school.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet("/sendNotification")
public class NotificationServlet extends HttpServlet {
    private NotificationDAO notificationDAO = new NotificationDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && ("Teacher".equals(user.getRole()) || "TrafficPolice".equals(user.getRole()))) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String message = request.getParameter("message");

            Notification notification = new Notification(0, userId, message, new Date(), false);
            notificationDAO.sendNotification(notification);
            response.sendRedirect("notifications.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}