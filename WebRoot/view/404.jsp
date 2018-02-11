<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>错误界面</title>

<link href="css/error.css" rel="stylesheet" type="text/css" />

</head>
<body>


<div id="container">
	<img class="png" src="images/404.png" />
	<img class="png msg" src="images/404_msg.png" />
	<p><a href="http://www.wiseph.com/doubleselect" target="_blank"><img class="png" src="images/404_to_index.png" /></a> </p>
</div>

<div id="cloud" class="png"></div>


 
</body>
</html>