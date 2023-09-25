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
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>wifi접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${nearbyWifiSpots}" var="wifiSpot">
        <tr>
            <input type="hidden" id="id" name="id" value="${wifiSpot.id}"/>
            <td>${wifiSpot.distance}</td>
            <td>${wifiSpot.mgrNo}</td>
            <td>${wifiSpot.wrdofc}</td>
            <td><a href='detail?id=${wifiSpot.id}'>${wifiSpot.mainNm}</a></td>
            <td>${wifiSpot.roadAdd}</td>
            <td>${wifiSpot.roadAddDetail}</td>
            <td>${wifiSpot.instlFloor}</td>
            <td>${wifiSpot.instlTy}</td>
            <td>${wifiSpot.instlMby}</td>
            <td>${wifiSpot.svcSe}</td>
            <td>${wifiSpot.cmcwr}</td>
            <td>${wifiSpot.cnstcYear}</td>
            <td>${wifiSpot.inoutDoor}</td>
            <td>${wifiSpot.remars3}</td>
            <td>${wifiSpot.lnt}</td>
            <td>${wifiSpot.lat}</td>
            <td>${wifiSpot.workDttm}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>