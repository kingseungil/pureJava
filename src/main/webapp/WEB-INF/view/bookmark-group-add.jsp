<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookmark Group Add</title>
</head>
<body>
<h1>북마크 그룹 추가</h1>
<jsp:include page="/WEB-INF/view/layout/header.jsp"/>
<form action="/bookmark-group-add" method="post">
    <table>
        <tr>
            <td>그룹명</td>
            <td><input type="text" name="name" required/></td>
        </tr>
        <tr>
            <td>순서</td>
            <td><input type="text" name="rank" pattern="\d+" title="숫자만 입력해주세요" required/></td>
        </tr>
        <tr>
            <td><input type="submit" value="추가"/></td>
        </tr>
    </table>
</form>
</body>
</html>
