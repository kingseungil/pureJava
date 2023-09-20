<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
<a href="${pageContext.request.contextPath}/">홈</a> |
<a href="">위치 히스토리 목록</a> |
<a href="${pageContext.request.contextPath}/load-wifi">Open API 와이파이 정보 구하기</a> |
<a href="">북마크 목록</a> |
<a href="">북마크 그룹 관리</a>
<br/>
<span>LAT: <label for="LAT"></label><input type="text" id="LAT" placeholder="0.0"/></span> ,
<span>LNT: <label for="LNT"></label><input type="text" id="LNT" placeholder="0.0"/></span>

<button onclick="getLocation()">위치 정보 가져오기</button>
<button onclick="location.href='/nearby-wifi?lat='+document.getElementById('LAT').value+'&lnt='+document.getElementById('LNT').value">
    근처 WIFI 정보 보기
</button>