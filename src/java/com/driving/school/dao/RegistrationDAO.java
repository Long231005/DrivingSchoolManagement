package com.driving.school.dao;

import com.driving.school.model.Registration;
import com.driving.school.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {
    public void registerCourse(int userId, int courseId) {
        String sql = "INSERT INTO Registrations (UserID, CourseID, Status) VALUES (?, ?, 'Pending')";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Registration> getPendingRegistrations() {
        List<Registration> registrations = new ArrayList<>();
        String sql = "SELECT * FROM Registrations WHERE Status = 'Pending'";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Registration reg = new Registration(
                    rs.getInt("RegistrationID"),
                    rs.getInt("UserID"),
                    rs.getInt("CourseID"),
                    rs.getString("Status"),
                    rs.getString("Comments")
                );
                registrations.add(reg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrations;
    }

    public void updateStatus(int registrationId, String status) {
        String sql = "UPDATE Registrations SET Status = ? WHERE RegistrationID = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, registrationId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}