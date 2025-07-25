<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Phê duyệt kỳ thi</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Phê duyệt kỳ thi</h2>

        <!-- Danh sách kỳ thi -->
        <h3>Danh sách kỳ thi</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Khóa học</th>
                    <th>Ngày thi</th>
                    <th>Phòng thi</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="exam" items="${exams}">
                    <tr>
                        <td>${exam.course.courseName}</td>
                        <td>${exam.date}</td>
                        <td>${exam.room}</td>
                        <td>
                            <c:choose>
                                <c:when test="${exam.status == 'Pending'}">Chờ phê duyệt</c:when>
                                <c:when test="${exam.status == 'Approved'}">Đã phê duyệt</c:when>
                                <c:otherwise>Đã từ chối</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:if test="${exam.status == 'Pending'}">
                                <form action="exam" method="post" style="display:inline;">
                                    <input type="hidden" name="examId" value="${exam.examId}">
                                    <input type="hidden" name="action" value="approve">
                                    <button type="submit" class="btn btn-success btn-sm">Phê duyệt</button>
                                </form>
                                <form action="exam" method="post" style="display:inline;">
                                    <input type="hidden" name="examId" value="${exam.examId}">
                                    <input type="hidden" name="action" value="reject">
                                    <button type="submit" class="btn btn-danger btn-sm">Từ chối</button>
                                </form>
                            </c:if>
                            <c:if test="${exam.status == 'Approved'}">
                                <a href="exam?action=results&examId=${exam.examId}" class="btn btn-info btn-sm">Xem kết quả</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Danh sách kết quả thi (nếu có) -->
        <c:if test="${not empty results}">
            <h3>Danh sách kết quả thi</h3>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Tên học sinh</th>
                        <th>Điểm số</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="result" items="${results}">
                        <tr>
                            <td>${result.user.fullName}</td>
                            <td>${result.score}</td>
                            <td>${result.passStatus ? 'Đậu' : 'Rớt'}</td>
                            <td>
                                <c:if test="${result.passStatus && empty result.certificate}">
                                    <form action="certificate" method="post" style="display:inline;">
                                        <input type="hidden" name="resultId" value="${result.resultId}">
                                        <input type="hidden" name="action" value="issue">
                                        <button type="submit" class="btn btn-primary btn-sm">Cấp chứng chỉ</button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <a href="trafficpolice/dashboard.jsp" class="btn btn-secondary mt-3">Quay lại</a>
    </div>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>