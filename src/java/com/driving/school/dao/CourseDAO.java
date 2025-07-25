package com.driving.school.dao;

import com.driving.school.model.Course;
import com.driving.school.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public void addCourse(Course course) {
        String sql = "INSERT INTO Courses (CourseName, TeacherID, StartDate, EndDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getTeacherId());
            ps.setDate(3, new java.sql.Date(course.getStartDate().getTime()));
            ps.setDate(4, new java.sql.Date(course.getEndDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course(
                    rs.getInt("CourseID"),
                    rs.getString("CourseName"),
                    rs.getInt("TeacherID"),
                    rs.getDate("StartDate"),
                    rs.getDate("EndDate")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}