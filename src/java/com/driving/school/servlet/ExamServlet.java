package com.driving.school.servlet;

import com.driving.school.dao.ExamDAO;
import com.driving.school.model.Exam;
import com.driving.school.model.Result;
import com.driving.school.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

@WebServlet("/addExam")
public class ExamServlet extends HttpServlet {
    private ExamDAO examDAO = new ExamDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && ("Teacher".equals(user.getRole()) || "TrafficPolice".equals(user.getRole()))) {
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            Date date = java.sql.Date.valueOf(request.getParameter("date"));
            String room = request.getParameter("room");

            Exam exam = new Exam(0, courseId, date, room);
            examDAO.addExam(exam);

            // Ví dụ: Thêm kết quả (có thể mở rộng)
            int examId = getLatestExamId(); // Giả định phương thức lấy ExamID mới nhất
            int userId = Integer.parseInt(request.getParameter("userId"));
            double score = Double.parseDouble(request.getParameter("score"));
            boolean passStatus = Boolean.parseBoolean(request.getParameter("passStatus"));
            Result result = new Result(0, examId, userId, score, passStatus);
            examDAO.addResult(result);

            response.sendRedirect("teacherDashboard.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private int getLatestExamId() {
        // Giả định phương thức lấy ExamID mới nhất (cần bổ sung logic thực tế)
        return 1; // Placeholder
    }
}