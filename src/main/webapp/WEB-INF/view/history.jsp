<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>검색 히스토리</title>
</head>
<script type="text/javascript">
  function deleteHistory(id) {
    if (confirm("정말로 삭제하시겠습니까?")) {
      location.href = "history-delete?id=" + id;
    }
  }
</script>
<body>
<h1>위치 히스토리 목록</h1>
<br/>
<jsp:include page="/WEB-INF/view/layout/header.jsp"/>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>x좌표</th>
        <th>y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${historyList}" var="history">
        <tr>
            <td>${history.id}</td>
            <td>${history.posX}</td>
            <td>${history.posY}</td>
            <td>${history.date}</td>
            <td>
                <button onclick="deleteHistory(${history.id})">삭제
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
