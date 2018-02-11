<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>添加角色</title>
    
  </head>
  <body>
    
    <div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>填写下面的内容并按提交保存！</div>
	</div>
	
	<div style="text-align:center;">
		<form id="ff" method="post" style="margin:0 auto;">
			<table style="margin:auto auto;">
				<tr>
					<td>角色名称:</td>
					<td><input name="name"  class="easyui-validatebox"
						type="text" data-options="required:true,validType:'length[1,10]'"  maxlength="11"></input>
					</td>			
				</tr>
				<tr height="20px"/>			
				<tr>
					<td colspan="4" align="center">
						<span><a href="javascript:void(0)" class="easyui-linkbutton"
							data-options="iconCls:'icon-ok'" onclick="submitRole()">提交</a>
						</span>
						 <span style="margin-left:20px;"><a href="javascript:void(0)" class="easyui-linkbutton"
							data-options="iconCls:'icon-cancel'" onclick="clearForm()">清空</a>
						</span>
					</td>
				</tr>
				<tr height="70px"></tr>
			</table>
		</form>
	</div> 
	<script type="text/javascript" src="js/sys/role/add.js"></script>
  </body>
</html>
