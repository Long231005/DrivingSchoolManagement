<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký khóa học</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Đăng ký khóa học</h2>
        <c:if test="${not empty message}">
            <div class="alert alert-success">${message}</div>
        </c:if>
        <div class="row">
            <div class="col-md-8">
                <h3>Danh sách khóa học</h3>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Tên khóa học</th>
                            <th>Giảng viên</th>
                            <th>Ngày bắt đầu</th>
                            <th>Ngày kết thúc</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="course" items="${courses}">
                            <tr>
                                <td>${course.courseName}</td>
                                <td>${course.teacherId}</td>
                                <td>${course.startDate}</td>
                                <td>${course.endDate}</td>
                                <td>
                                    <form action="registerCourse" method="post">
                                        <input type="hidden" name="courseId" value="${course.courseId}">
                                        <input type="hidden" name="userId" value="${user.userId}">
                                        <button type="submit" class="btn btn-primary btn-sm">Đăng ký</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="student/dashboard.jsp" class="btn btn-secondary mt-3">Quay lại</a>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>