<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>내 근처 와이파이</title>
</head>

<body>
<h1><%= "전주시 와이파이 정보 구하기" %>
</h1>
<br/>
<jsp:include page="/WEB-INF/view/layout/header.jsp"/>
<br/>
<table>
    <thead>
    <tr>
        <th>거리(Km)</th>
        <th>관리기관</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치유형</th>
        <th>설치장소</th>
        <th>설치날짜</th>
        <th>x좌표</th>
        <th>y좌표</th>
        <th>서비스제공사</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${nearbyWifiSpots}" var="wifiSpot">
        <tr>
            <td>${wifiSpot.distance}</td>
            <td>${wifiSpot.adminNm}</td>
            <td>${wifiSpot.roadAdd}</td>
            <td>${wifiSpot.detailPlace}</td>
            <td>${wifiSpot.instfacType}</td>
            <td>${wifiSpot.instplaceNm}</td>
            <td>${wifiSpot.standtData}</td>
            <td>${wifiSpot.posX}</td>
            <td>${wifiSpot.posY}</td>
            <td>${wifiSpot.seviceNm}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>