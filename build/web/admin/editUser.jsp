<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Sửa tài khoản</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Sửa tài khoản</h2>
        <c:if test="${not empty message}">
            <div class="alert alert-${messageType}">${message}</div>
        </c:if>
        <form action="user" method="post" class="border p-4 rounded shadow-sm">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="userId" value="${user.userId}">
            <div class="form-group">
                <label for="fullName">Họ và tên:</label>
                <input type="text" class="form-control" id="fullName" name="fullName" value="${user.fullName}" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu mới (để trống nếu không đổi):</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            <div class="form-group">
                <label for="role">Vai trò:</label>
                <select class="form-control" id="role" name="role" required>
                    <option value="Student" ${user.role == 'Student' ? 'selected' : ''}>Học sinh</option>
                    <option value="Teacher" ${user.role == 'Teacher' ? 'selected' : ''}>Giảng viên</option>
                    <option value="TrafficPolice" ${user.role == 'TrafficPolice' ? 'selected' : ''}>Cảnh sát giao thông</option>
                    <option value="Admin" ${user.role == 'Admin' ? 'selected' : ''}>Quản trị viên</option>
                </select>
            </div>
            <div class="form-group">
                <label for="className">Lớp (chỉ cho học sinh):</label>
                <input type="text" class="form-control" id="className" name="className" value="${user.className}">
            </div>
            <div class="form-group">
                <label for="school">Trường (chỉ cho học sinh):</label>
                <input type="text" class="form-control" id="school" name="school" value="${user.school}">
            </div>
            <div class="form-group">
                <label for="phone">Số điện thoại:</label>
                <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}" required>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Cập nhật tài khoản</button>
            <a href="user" class="btn btn-secondary mt-3">Quay lại</a>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>