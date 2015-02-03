<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Page Title</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1 user-scalable=yes">

<link rel="stylesheet" href="css/jquery.mobile-1.1.1.min.css" />
<script src="js/jquery.js"></script>
<script src="js/jquery.mobile-1.1.1.min.js"></script>
</head>
<body>
	<div data-role="page">
		<div data-role="header">
			<h1>Page title here</h1>
		</div>
	</div>

	<input id="button01" type="button" value="JS调用android中的方法"
		onClick="showAndroidToast('Hello Android...')">
	<input id="button02" type="button" value="JS获取android中的数据"
		onClick="getAndroidTxt()">

	<a href="https://one.gtja.com/yht/view/login/SafeLoginSetup.exe">国泰君安</a>
	<script type="text/javascript">
		/*在JS中调用原生android的方法  */
		function showAndroidToast(toast) {
			Android.showToast("density is : " + window.devicePixelRatio);
		}

		/*JS调用android代码获取文本内容   */
		function getAndroidTxt() {
			var str = Android.getAndroidTxt() + "";
			var bt = document.getElementById("button02");
			bt.value = str;
		}

		/*android中调用JS代码   */
		function showJSDialog() {
			alert("原生android调用JS方法.");
		}
	</script>
</html>
