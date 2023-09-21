<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookmark group</title>
</head>
<body>
<h1>북마크 그룹</h1>
<jsp:include page="/WEB-INF/view/layout/header.jsp"/>
<br/>
<%--TODO bookmark-group-add 페이지로 이동--%>
<button onclick="location.href='/bookmark-group-add'">북마크 그룹 이름 추가</button>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>순서</th>
        <th>등록일자</th>
        <th>수정일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${bookmarkGroupList}" var="bookmarkGroup">
        <tr>
            <td>${bookmarkGroup.id}</td>
            <td>${bookmarkGroup.name}</td>
            <td>${bookmarkGroup.rank}</td>
            <td>${bookmarkGroup.created_date}</td>
            <td>${bookmarkGroup.updated_date}</td>
            <td>
                    <%--TODO bookmark-group-update 페이지로 이동--%>
                <button onclick="location.href='/bookmark-group-update?id=${bookmarkGroup.id}'">수정</button>
                    <%--TODO 바로 삭제시키기 (history처럼)--%>
                <button onclick="deleteBookmarkGroup(${bookmarkGroup.id})">삭제</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
