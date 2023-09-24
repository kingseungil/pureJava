<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="<c:url value="/resources/css/styles.css"/>">
<a href="<c:url value="/"/>">홈</a> |
<a href="<c:url value="/history"/>">위치 히스토리 목록</a> |
<a href="<c:url value="/load-wifi"/>">Open API 와이파이 정보 구하기</a> |
<a href="<c:url value="/bookmark-list"/>">북마크 목록</a> |
<a href="<c:url value="/bookmark-group"/>">북마크 그룹 관리</a>
<br/>
<span>LAT: <label for="LAT"></label><input type="text" id="LAT" placeholder="0.0"/></span> ,
<span>LNT: <label for="LNT"></label><input type="text" id="LNT" placeholder="0.0"/></span>

<button onclick="getLocation()">위치 정보 가져오기</button>
<button onclick="nearbyWifi()">근처 WIFI 정보 보기</button>
<script src="<c:url value="/resources/js/location.js"/>"></script>
