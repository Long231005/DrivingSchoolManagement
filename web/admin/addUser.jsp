<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm tài khoản</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Thêm tài khoản mới</h2>
        <c:if test="${not empty message}">
            <div class="alert alert-${messageType}">${message}</div>
        </c:if>
        <form action="user" method="post" class="border p-4 rounded shadow-sm">
            <input type="hidden" name="action" value="add">
            <div class="form-group">
                <label for="fullName">Họ và tên:</label>
                <input type="text" class="form-control" id="fullName" name="fullName" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="role">Vai trò:</label>
                <select class="form-control" id="role" name="role" required>
                    <option value="Student">Học sinh</option>
                    <option value="Teacher">Giảng viên</option>
                    <option value="TrafficPolice">Cảnh sát giao thông</option>
                    <option value="Admin">Quản trị viên</option>
                </select>
            </div>
            <div class="form-group">
                <label for="className">Lớp (chỉ cho học sinh):</label>
                <input type="text" class="form-control" id="className" name="className">
            </div>
            <div class="form-group">
                <label for="school">Trường (chỉ cho học sinh):</label>
                <input type="text" class="form-control" id="school" name="school">
            </div>
            <div class="form-group">
                <label for="phone">Số điện thoại:</label>
                <input type="text" class="form-control" id="phone" name="phone" required>
            </div>
            <button type="submit" class="btn btn-primary mt-3">Thêm tài khoản</button>
            <a href="user" class="btn btn-secondary mt-3">Quay lại</a>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>