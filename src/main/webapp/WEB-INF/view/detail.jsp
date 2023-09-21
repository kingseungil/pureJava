<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>와이파이 상세페이지</title>
</head>
<body>
<h1>상세페이지</h1>
<jsp:include page="/WEB-INF/view/layout/header.jsp"/>
<br/>
<select id="bookmarkGroup">
    <option value="0">북마크 그룹 이름 선택</option>
    <c:forEach items="${bookmarkGroupList}" var="bookmarkGroup">
        <option value="${bookmarkGroup.id}">${bookmarkGroup.name}</option>
    </c:forEach>
</select>
<button onclick="addBookmark()">북마크 추가</button>
<table>
    <tr>
        <th>거리</th>
        <td>${wifiData.distance}</td>
    </tr>
    <tr>
        <th>관리기관</th>
        <td>${wifiData.adminNm}</td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td>${wifiData.roadAdd}</td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td>${wifiData.detailPlace}</td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td>${wifiData.instfacType}</td>
    </tr>
    <tr>
        <th>설치장소</th>
        <td>${wifiData.instplaceNm}</td>
    </tr>
    <tr>
        <th>설치날짜</th>
        <td>${wifiData.standtData}</td>
    </tr>
    <tr>
        <th>x좌표</th>
        <td>${wifiData.posX}</td>
    </tr>
    <tr>
        <th>y좌표</th>
        <td>${wifiData.posY}</td>
    </tr>
    <tr>
        <th>서비스제공사</th>
        <td>${wifiData.seviceNm}</td>
    </tr>
</table>
</body>
</html>
