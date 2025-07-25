package com.driving.school.servlet;

import com.driving.school.dao.UserDAO;
import com.driving.school.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

// Lớp giả lập cấu hình hệ thống (sẽ mở rộng sau)
class SystemConfig {
    public String systemName = "Driving School Management";
    public int maxUsers = 1000;
}

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        try {
            // Quản trị viên: Hiển thị danh sách hoặc điều hướng đến các trang quản lý
            if ("Admin".equals(currentUser.getRole())) {
                if ("add".equals(action)) {
                    request.getRequestDispatcher("admin/addUser.jsp").forward(request, response);
                } else if ("edit".equals(action)) {
                    int userId = Integer.parseInt(request.getParameter("id"));
                    User user = userDAO.getUserById(userId);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("admin/editUser.jsp").forward(request, response);
                } else if ("delete".equals(action)) {
                    int userId = Integer.parseInt(request.getParameter("id"));
                    userDAO.deleteUser(userId);
                    response.sendRedirect("user");
                } else {
                    List<User> users = userDAO.getAllUsers();
                    request.setAttribute("users", users);
                    request.setAttribute("systemConfig", new SystemConfig());
                    request.getRequestDispatcher("admin/manageUsers.jsp").forward(request, response);
                }
            } else {
                // Người dùng thông thường: Hiển thị profile.jsp
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi khi xử lý yêu cầu: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        try {
            // Quản trị viên: Xử lý các hành động quản lý tài khoản
            if ("Admin".equals(currentUser.getRole())) {
                if ("add".equals(action)) {
                    String fullName = request.getParameter("fullName");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String role = request.getParameter("role");
                    String className = "Student".equals(role) ? request.getParameter("className") : null;
                    String school = "Student".equals(role) ? request.getParameter("school") : null;
                    String phone = request.getParameter("phone");

                    User user = new User();
                    user.setFullName(fullName);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setRole(role);
                    user.setClassName(className);
                    user.setSchool(school);
                    user.setPhone(phone);

                    userDAO.addUser(user);
                    request.setAttribute("message", "Thêm tài khoản thành công!");
                    request.setAttribute("messageType", "success");
                    request.getRequestDispatcher("admin/addUser.jsp").forward(request, response);
                } else if ("update".equals(action)) {
                    int userId = Integer.parseInt(request.getParameter("userId"));
                    String fullName = request.getParameter("fullName");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String role = request.getParameter("role");
                    String className = "Student".equals(role) ? request.getParameter("className") : null;
                    String school = "Student".equals(role) ? request.getParameter("school") : null;
                    String phone = request.getParameter("phone");

                    User user = userDAO.getUserById(userId);
                    user.setFullName(fullName);
                    user.setEmail(email);
                    if (password != null && !password.isEmpty()) {
                        user.setPassword(password);
                    }
                    user.setRole(role);
                    user.setClassName(className);
                    user.setSchool(school);
                    user.setPhone(phone);

                    userDAO.updateUser(user);
                    request.setAttribute("message", "Cập nhật tài khoản thành công!");
                    request.setAttribute("messageType", "success");
                    request.getRequestDispatcher("admin/editUser.jsp").forward(request, response);
                }
            }

            // Người dùng thông thường: Cập nhật thông tin cá nhân
            if (action == null || action.isEmpty()) {
                int userId = currentUser.getUserId();
                String fullName = request.getParameter("fullName");
                String phone = request.getParameter("phone");
                String className = request.getParameter("className");
                String school = request.getParameter("school");

                User updatedUser = new User(userId, fullName, null, null, null, className, school, phone);
                userDAO.updateUser(updatedUser);
                currentUser.setFullName(fullName);
                currentUser.setPhone(phone);
                currentUser.setClassName(className);
                currentUser.setSchool(school);
                session.setAttribute("user", currentUser);
                response.sendRedirect("profile.jsp");
            }
        } catch (Exception e) {
            request.setAttribute("message", "Lỗi khi xử lý yêu cầu: " + e.getMessage());
            request.setAttribute("messageType", "danger");
            if ("add".equals(action)) {
                request.getRequestDispatcher("admin/addUser.jsp").forward(request, response);
            } else if ("update".equals(action)) {
                request.getRequestDispatcher("admin/editUser.jsp").forward(request, response);
            } else {
                response.sendRedirect("profile.jsp");
            }
        }
    }
}