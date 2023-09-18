<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<script>
  // 위치 정보 가져오기
  function getLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(showPosition);
    } else {
      alert("이 브라우저에서는 Geolocation이 지원되지 않습니다.");
    }
  }

  // 위치 정보 화면에 표시
  function showPosition(position) {
    const lat = position.coords.latitude;
    const lnt = position.coords.longitude;
    document.getElementById("LAT").value = lat;
    document.getElementById("LNT").value = lnt;
  }
</script>
<body>
<h1><%= "전주시 와이파이 정보 구하기" %>
</h1>
<br/>
<a href="index.jsp">홈</a> |
<a href="history.jsp">위치 히스토리 목록</a> |
<a href="load-wifi.jsp">Open API 와이파이 정보 구하기</a> |
<a href="bookmark-list.jsp">북마크 목록</a> |
<a href="bookmark-group.jsp">북마크 그룹 관리</a>
<br/>
<span>LAT: <label for="LAT"></label><input type="text" id="LAT" placeholder="0.0"/></span>,
<span>LNT: <label for="LNT"></label><input type="text" id="LNT" placeholder="0.0"/></span>

<button onclick="getLocation()">위치 정보 가져오기</button>
<button onclick="location.href='/?lat='+document.getElementById('LAT').value+'&lnt='+document.getElementById('LNT').value">
    근처 WIFI 정보 보기
</button>
</body>
</html>