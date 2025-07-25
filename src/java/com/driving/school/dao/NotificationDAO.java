package com.driving.school.dao;

import com.driving.school.model.Notification;
import com.driving.school.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    public void sendNotification(Notification notification) {
        String sql = "INSERT INTO Notifications (UserID, Message, SentDate, IsRead) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, notification.getUserId());
            ps.setString(2, notification.getMessage());
            ps.setTimestamp(3, new java.sql.Timestamp(notification.getSentDate().getTime()));
            ps.setBoolean(4, notification.isRead());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Notification> getNotificationsByUser(int userId) {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM Notifications WHERE UserID = ? ORDER BY SentDate DESC";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Notification notification = new Notification(
                    rs.getInt("NotificationID"),
                    rs.getInt("UserID"),
                    rs.getString("Message"),
                    rs.getTimestamp("SentDate"),
                    rs.getBoolean("IsRead")
                );
                notifications.add(notification);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }
}