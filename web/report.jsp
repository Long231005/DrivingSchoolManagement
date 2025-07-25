<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Báo cáo và thống kê</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Báo cáo và thống kê</h2>
        <h3>Thống kê đăng ký</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Khóa học</th>
                    <th>Số lượng đăng ký</th>
                    <th>Tỷ lệ phê duyệt</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="report" items="${registrationReports}">
                    <tr>
                        <td>${report.courseName}</td>
                        <td>${report.totalRegistrations}</td>
                        <td>${report.approvalRate}%</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <h3>Thống kê kết quả thi</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Khóa học</th>
                    <th>Số lượng thi</th>
                    <th>Tỷ lệ đậu</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="report" items="${examReports}">
                    <tr>
                        <td>${report.courseName}</td>
                        <td>${report.totalExams}</td>
                        <td>${report.passRate}%</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/${user.role.toLowerCase()}/dashboard.jsp" class="btn btn-secondary mt-3">Quay lại</a>
    </div>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>