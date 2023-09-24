<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Bookmark group</title>
</head>
<script type="text/javascript">
  function deleteBookmarkGroup(id) {
    if (confirm("정말로 삭제하시겠습니까?")) {
      location.href = "bookmark-group-delete?id=" + id;
    }
  }
</script>
<body>
<h1>북마크 그룹</h1>
<jsp:include page="/WEB-INF/view/layout/header.jsp"/>
<br/>
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
                <button onclick="location.href='/bookmark-group-update?id=${bookmarkGroup.id}'">수정</button>
                <button onclick="deleteBookmarkGroup(${bookmarkGroup.id})">삭제</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
