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

<meta name="viewport" content="height=100, width=200, initial-scale=1">

<link rel="stylesheet" href="css/jquery.mobile-1.1.1.min.css" />
<script src="js/jquery.js"></script>
<script src="js/jquery.mobile-1.1.1.min.js"></script>
</head>
<link rel="stylesheet"
	media="screen and (-webkit-device-pixel-ratio: 1.5)" href="hdpi.css" />
<link rel="stylesheet"
	media="screen and (-webkit-device-pixel-ratio: 1.0)" href="mdpi.css" />
<body>
		<br /> <label >使用sessionStorage存储数据，关闭浏览器数据被清空</label> <br /> 
	<div style="border: 2px dashed #ccc;width:320px;text-align:center;">
		<label for="user_name">姓名：</label> 
		<input type="text" id="user_name" name="user_name" class="text" /> <br /> 
		<label for="mobilephone">手机：</label>
		<input type="text" id="mobilephone" name="mobilephone" /> <br /> 
		<input type="button" onclick="save()" value="新增记录" /><br /> 
		<input type="button" onclick="loadAll()" value="查询所有记录" />
		<hr />
		<label for="search_phone">输入手机号：</label> 
		<input type="text" id="search_phone" name="search_phone" /> 
		<input type="button" onclick="find()" value="查找机主" />
		<p id="find_result">
			<br />
		</p>
	</div>
	<br />
	<div id="list"></div>

	<script>
		//保存数据 
		function save() {
			var mobilephone = document.getElementById("mobilephone").value;
			var user_name = document.getElementById("user_name").value;
			sessionStorage.setItem(mobilephone, user_name);
		}

		//查找数据 
		function find() {
			var search_phone = document.getElementById("search_phone").value;
			var name = sessionStorage.getItem(search_phone);
			var find_result = document.getElementById("find_result");
			find_result.innerHTML = search_phone + "的机主是：" + name;
		}

		//将所有存储在sessionStorage中的对象提取出来，并展现到界面上 
		function loadAll() {
			var list = document.getElementById("list");
			if (sessionStorage.length > 0) {
				var result = "<table border='1'>";
				result += "<tr><td>姓名</td><td>手机号码</td></tr>";
				for (var i = 0; i < sessionStorage.length; i++) {
					var mobilephone = sessionStorage.key(i);
					var name = sessionStorage.getItem(mobilephone);
					result += "<tr><td>" + name + "</td><td>" + mobilephone
							+ "</td></tr>";
				}
				result += "</table>";
				list.innerHTML = result;
			} else {
				list.innerHTML = "目前数据为空，赶紧开始加入联系人吧";
			}
		}
	</script>
</body>
</html>
