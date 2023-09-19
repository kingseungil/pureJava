<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wifi 데이터 가져오기</title>
</head>
<body>
<div style="text-align: center">
    <h1 style="margin-top: 30px">
        <%= request.getAttribute("message") %>
    </h1>
    <a href="${pageContext.request.contextPath}/">홈으로 가기</a>
</div>
</body>
</html>
