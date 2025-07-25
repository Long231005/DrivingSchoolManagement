<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thông báo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Thông báo</h2>
        <c:if test="${empty notifications}">
            <p>Không có thông báo nào.</p>
        </c:if>
        <c:if test="${not empty notifications}">
            <ul class="list-group">
                <c:forEach var="notification" items="${notifications}">
                    <li class="list-group-item ${notification.isRead ? '' : 'font-weight-bold'}">
                        ${notification.message} (Gửi lúc: ${notification.sentDate})
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <a href="${pageContext.request.contextPath}/${user.role.toLowerCase()}/dashboard.jsp" class="btn btn-secondary mt-3">Quay lại</a>
    </div>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>