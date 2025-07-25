package com.driving.school.dao;

import com.driving.school.model.Exam;
import com.driving.school.model.Result;
import com.driving.school.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamDAO {
    public void addExam(Exam exam) {
        String sql = "INSERT INTO Exams (CourseID, Date, Room) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, exam.getCourseId());
            ps.setDate(2, new java.sql.Date(exam.getDate().getTime()));
            ps.setString(3, exam.getRoom());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Exam> getExamsByCourse(int courseId) {
        List<Exam> exams = new ArrayList<>();
        String sql = "SELECT * FROM Exams WHERE CourseID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Exam exam = new Exam(
                    rs.getInt("ExamID"),
                    rs.getInt("CourseID"),
                    rs.getDate("Date"),
                    rs.getString("Room")
                );
                exams.add(exam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exams;
    }

    public void addResult(Result result) {
        String sql = "INSERT INTO Results (ExamID, UserID, Score, PassStatus) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, result.getExamId());
            ps.setInt(2, result.getUserId());
            ps.setDouble(3, result.getScore());
            ps.setBoolean(4, result.isPassStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}