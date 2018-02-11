<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>设置角色</title>
  </head>
  <body>
  
  	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>填写下面的内容并按提交保存！</div>
	</div>
	<div style="margin:10px 0;"></div>
	
	<div style="text-align:center;">
		<span>
			<a href="javascript:void(0)" data-options="iconCls:'icon-ok',plain:true"
					style="margin:0 auto;text-align:center;" class="easyui-linkbutton" 
					onclick="submitUpdateRole()">提交</a>
		</span>
		<form id="ff" method="post" style="text-align:center;">
			
				${roleText }
		
		</form>
		
	</div>
  </body>
</html>
