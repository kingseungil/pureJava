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