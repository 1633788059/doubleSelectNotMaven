<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>修改角色</title>
  </head>
  <body>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>填写下面的内容并按提交保存！</div>
	</div>
	<div style="text-align:center;" fit='true'>

		<form id="ff" method="post">
		
			<input type="hidden" name="id" value="${role.id }"/>
			<table style="margin:0 auto;">
				<tr>
					<td>角色名称:</td>
					<td><input name="name"  class="easyui-validatebox" value="${role.name }"
						type="text" data-options="required:true" maxlength="20"></input>
					</td>			
				</tr>
				<tr height="20px"/>		
				<tr>
    				<td></td>
	    			<td align="center">
		    			<span>
		    				<a href="javascript:void(0)" data-options="iconCls:'icon-save'"
		    				 	class="easyui-linkbutton" onclick="submitRoleEdit()">保存修改</a>
		    			</span>
 					</td>
    			</tr>					
			</table>
		</form>
	</div>
	<script type="text/javascript" src="js/sys/role/edit.js"></script>
  </body>
</html>
