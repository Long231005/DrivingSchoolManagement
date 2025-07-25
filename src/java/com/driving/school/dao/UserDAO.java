package com.driving.school.dao;

import com.driving.school.model.User;
import com.driving.school.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public User login(String email, String password) {
        String sql = "SELECT * FROM Users WHERE Email = ? AND Password = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("UserID"),
                    rs.getString("FullName"),
                    rs.getString("Email"),
                    rs.getString("Password"),
                    rs.getString("Role"),
                    rs.getString("Class"),
                    rs.getString("School"),
                    rs.getString("Phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (FullName, Email, Password, Role, Class, School, Phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getClassName());
            ps.setString(6, user.getSchool());
            ps.setString(7, user.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Lỗi khi thêm người dùng: " + e.getMessage(), e);
        }
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE Users SET FullName = ?, Email = ?, Password = ?, Role = ?, Class = ?, School = ?, Phone = ? WHERE UserID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword() != null && !user.getPassword().isEmpty() ? user.getPassword() : null);
            ps.setString(4, user.getRole());
            ps.setString(5, user.getClassName());
            ps.setString(6, user.getSchool());
            ps.setString(7, user.getPhone());
            ps.setInt(8, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Lỗi khi cập nhật người dùng: " + e.getMessage(), e);
        }
    }

    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM Users WHERE UserID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Lỗi khi xóa người dùng: " + e.getMessage(), e);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("UserID"),
                    rs.getString("FullName"),
                    rs.getString("Email"),
                    rs.getString("Password"),
                    rs.getString("Role"),
                    rs.getString("Class"),
                    rs.getString("School"),
                    rs.getString("Phone")
                ));
            }
        } catch (SQLException e) {
            throw new SQLException("Lỗi khi lấy danh sách người dùng: " + e.getMessage(), e);
        }
        return users;
    }

    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("UserID"),
                        rs.getString("FullName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Role"),
                        rs.getString("Class"),
                        rs.getString("School"),
                        rs.getString("Phone")
                    );
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Lỗi khi lấy thông tin người dùng: " + e.getMessage(), e);
        }
        return null;
    }
}