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
        <td></td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td></td>
    </tr>
    <tr>
        <th>등록일자</th>
        <td></td>
    </tr>
    <tr>
        <td>
            <a href="/bookmarklist">돌아가기</a> |
            <button onclick="">삭제</button>
        </td>
    </tr>
</table>
</body>
</html>
