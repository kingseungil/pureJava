<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>와이파이 상세페이지</title>
</head>
<body>
<h1>상세페이지</h1>
<jsp:include page="/WEB-INF/view/layout/header.jsp"/>
<br/>
<form action="<c:url value='/bookmark-add'/>" method="post">
    <input type="hidden" name="wifiDataId" value="${wifiData.id}">
    <label>
        <select name="groupId">
            <option value="">북마크 그룹 이름 선택</option>
            <c:forEach items="${bookmarkGroupList}" var="bookmarkGroup">
                <option value="${bookmarkGroup.id}">${bookmarkGroup.name}</option>
            </c:forEach>
        </select>
    </label>
    <button type='submit'>북마크 추가</button>
</form>
<table>
    <tr>
        <th>거리(km)</th>
        <td>${wifiData.distance}</td>
    </tr>
    <tr>
        <th>관리번호</th>
        <td>${wifiData.mgrNo}</td>
    </tr>
    <tr>
        <th>자치구</th>
        <td>${wifiData.wrdofc}</td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td>${wifiData.mainNm}</td>
    </tr>
    <tr>
        <th>도로명주소</th>
        <td>${wifiData.roadAdd}</td>
    </tr>
    <tr>
        <th>상세주소</th>
        <td>${wifiData.roadAddDetail}</td>
    </tr>
    <tr>
        <th>설치위치(층)</th>
        <td>${wifiData.instlFloor}</td>
    </tr>
    <tr>
        <th>설치유형</th>
        <td>${wifiData.instlTy}</td>
    </tr>
    <tr>
        <th>설치기관</th>
        <td>${wifiData.instlMby}</td>
    </tr>
    <tr>
        <th>서비스구분</th>
        <td>${wifiData.svcSe}</td>
    </tr>
    <tr>
        <th>망종류</th>
        <td>${wifiData.cmcwr}</td>
    </tr>
    <tr>
        <th>설치년도</th>
        <td>${wifiData.cnstcYear}</td>
    </tr>
    <tr>
        <th>실내외구분</th>
        <td>${wifiData.inoutDoor}</td>
    </tr>
    <tr>
        <th>wifi접속환경</th>
        <td>${wifiData.remars3}</td>
    </tr>
    <tr>
        <th>X좌표</th>
        <td>${wifiData.lnt}</td>
    </tr>
    <tr>
        <th>Y좌표</th>
        <td>${wifiData.lat}</td>
    </tr>
    <tr>
        <th>작업일자</th>
        <td>${wifiData.workDttm}</td>
    </tr>
</table>
</body>
</html>
