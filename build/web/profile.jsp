<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thông tin cá nhân</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Thông tin cá nhân</h2>
        <c:if test="${not empty message}">
            <div class="alert alert-${messageType}">${message}</div>
        </c:if>
        <c:if test="${user != null}">
            <form action="user" method="post" class="border p-4 rounded shadow-sm">
                <div class="form-group">
                    <label for="fullName">Họ và tên:</label>
                    <input type="text" class="form-control" id="fullName" name="fullName" value="${user.fullName}" required>
                </div>
                <div class="form-group">
                    <label for="phone">Số điện thoại:</label>
                    <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}" required>
                </div>
                <c:if test="${user.role == 'Student'}">
                    <div class="form-group">
                        <label for="className">Lớp:</label>
                        <input type="text" class="form-control" id="className" name="className" value="${user.className}">
                    </div>
                    <div class="form-group">
                        <label for="school">Trường:</label>
                        <input type="text" class="form-control" id="school" name="school" value="${user.school}">
                    </div>
                </c:if>
                <button type="submit" class="btn btn-primary mt-3">Cập nhật thông tin</button>
                <a href="${pageContext.request.contextPath}/${user.role.toLowerCase()}/dashboard.jsp" class="btn btn-secondary mt-3">Quay lại</a>
            </form>
        </c:if>
        <c:if test="${user == null}">
            <p class="text-danger">Bạn cần đăng nhập để xem thông tin cá nhân!</p>
            <a href="login.jsp" class="btn btn-primary">Đăng nhập</a>
        </c:if>
    </div>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>