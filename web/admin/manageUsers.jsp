<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý tài khoản</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Quản lý tài khoản người dùng</h2>

        <!-- Nút thêm tài khoản mới -->
        <a href="user?action=add" class="btn btn-primary mb-3">Thêm tài khoản mới</a>

        <!-- Danh sách tài khoản -->
        <h3>Danh sách tài khoản</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Họ và tên</th>
                    <th>Email</th>
                    <th>Vai trò</th>
                    <th>Lớp</th>
                    <th>Trường</th>
                    <th>Số điện thoại</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.fullName}</td>
                        <td>${user.email}</td>
                        <td>${user.role}</td>
                        <td>${user.className}</td>
                        <td>${user.school}</td>
                        <td>${user.phone}</td>
                        <td>
                            <a href="user?action=edit&id=${user.userId}" class="btn btn-warning btn-sm">Sửa</a>
                            <a href="user?action=delete&id=${user.userId}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa tài khoản này?')">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Cấu hình hệ thống -->
        <h3>Cấu hình hệ thống</h3>
        <form action="user" method="post">
            <input type="hidden" name="action" value="updateConfig">
            <div class="form-group">
                <label for="systemName">Tên hệ thống:</label>
                <input type="text" class="form-control" id="systemName" name="systemName" value="${systemConfig.systemName}">
            </div>
            <div class="form-group">
                <label for="maxUsers">Số lượng người dùng tối đa:</label>
                <input type="number" class="form-control" id="maxUsers" name="maxUsers" value="${systemConfig.maxUsers}">
            </div>
            <button type="submit" class="btn btn-primary mt-3">Cập nhật cấu hình</button>
        </form>

        <a href="${pageContext.request.contextPath}/admin/dashboard.jsp" class="btn btn-secondary mt-3">Quay lại</a>
    </div>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>