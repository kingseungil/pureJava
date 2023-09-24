<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bookmark List</title>
</head>
<body>
<h1>북마크 목록</h1>
<br/>
<jsp:include page="/WEB-INF/view/layout/header.jsp"/>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>도로명 주소</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bookmarkList}" var="bookmark">
        <tr>
            <td>${bookmark.id}</td>
            <td>${bookmark.group_name}</td>
            <td>${bookmark.roadAdd}</td>
            <td>${bookmark.date}</td>
            <td>
                <button onclick="location.href='/bookmark-delete?id=${bookmark.id}'">삭제</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>