<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
	<button onclick="getLocation()">Click the button to get your
		coordinates:</button>

	<script>
		function getLocation() {

			/*http://www.w3school.com.cn/html5/html_5_geolocation.asp*/
			if (navigator.geolocation) {
				/* 获取当前地理位置  */
				navigator.geolocation.getCurrentPosition(showPosition,
						showError, {
							enableHighAccuracy : false,
							timeout : 30000
						});

				/*持续获取地理位置  navigator.geolocation.watchPosition  */
				/*清除持续获取地理位置事件  navigator.geolocation.clearWatch */
			} else {
				alert("当前webview不支持定位");
			}
		}

		function showPosition(position) {
			alert("Lat: " + position.coords.latitude + "\nLon: "
					+ position.coords.longitude);
		}

		function showError(error) {
			alert("Error: " + error.code);
		}
	</script>
</body>
</html>
