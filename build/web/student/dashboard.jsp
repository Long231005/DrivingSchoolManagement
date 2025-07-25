<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard Học sinh</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Chào mừng, ${user.fullName}</h2>
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Thông tin cá nhân</h5>
                        <p>Email: ${user.email}</p>
                        <p>Lớp: ${user.className}</p>
                        <p>Trường: ${user.school}</p>
                        <p>Số điện thoại: ${user.phone}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <h3>Các chức năng</h3>
                <div class="list-group">
                    <a href="registerCourse" class="list-group-item list-group-item-action">Đăng ký khóa học</a>
                    <a href="exam?action=studentResults" class="list-group-item list-group-item-action">Xem kết quả thi</a>
                    <a href="certificate?action=studentCertificates" class="list-group-item list-group-item-action">Xem chứng chỉ</a>
                    <a href="notifications" class="list-group-item list-group-item-action">Xem thông báo</a>
                    <a href="logout" class="list-group-item list-group-item-action text-danger">Đăng xuất</a>
                </div>
            </div>
        </div>
        <!-- Hiển thị thông báo -->
        <c:if test="${not empty notifications}">
            <h3 class="mt-4">Thông báo</h3>
            <ul class="list-group">
                <c:forEach var="notification" items="${notifications}">
                    <li class="list-group-item ${notification.isRead ? '' : 'font-weight-bold'}">
                        ${notification.message} (Gửi lúc: ${notification.sentDate})
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>