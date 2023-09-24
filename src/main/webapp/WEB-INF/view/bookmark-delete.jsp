<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookmark Delete</title>
</head>
<body>
<h1>북마크 삭제</h1>
<jsp:include page="/WEB-INF/view/layout/header.jsp"/>
<br/>
<table>
    <tr>
        <th>북마크이름</th>
        <td>${bookmark.group_name}</td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td>${bookmark.roadAdd}</td>
    </tr>
    <tr>
        <th>등록일자</th>
        <td>${bookmark.date}</td>
    </tr>
</table>
<br/>
<form id="deleteForm" action="<c:url value="/bookmark-delete"/>" method="post">
    <input type="hidden" name="id" value="${bookmark.id}"/>
    <a href="<c:url value="/bookmark-list"/>">돌아가기</a> |
    <button type="button" onclick="
    if(confirm('정말 삭제하시겠습니까?')) {document.getElementById('deleteForm').submit();}">삭제
    </button>
</form>
</body>
</html>
