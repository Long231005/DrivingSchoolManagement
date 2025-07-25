package com.driving.school.servlet;

import com.driving.school.dao.ReportDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {
    private ReportDAO reportDAO = new ReportDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int registeredStudents = reportDAO.getRegisteredStudents();
        double passRate = reportDAO.getPassRate();

        request.setAttribute("registeredStudents", registeredStudents);
        request.setAttribute("passRate", passRate);
        request.getRequestDispatcher("report.jsp").forward(request, response);
    }
}