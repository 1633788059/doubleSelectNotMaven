<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>添加用户</title>

  </head>
  <body>
		<div class="demo-info">
			<div class="demo-tip icon-tip"></div>
			<div>填写下面的内容并按提交保存！</div>
		</div>
		<div style="margin:10px 0;"></div>
		<div style="margin:0px auto;">
			<form id="ff" method="post" style="margin:0 auto;">
				<table style="margin:0 auto;">
					<tr>
						<td>用户名:</td>
						<td><input name="name"  class="easyui-validatebox" type="text" 
							 data-options="required:true,validType:'length[3,10]'" maxlength="20"></input>
						</td>	
						<td style="padding-left:40px;">用户密码:</td>
						<td><input name="pwd"  class="easyui-validatebox" type="text"
							 data-options="required:true,validType:'length[6,15]'" maxlength="20"></input>
						</td>			
					</tr>
					<tr height="20px"/>		
					<tr>
						<td colspan="4" align="center">
							<span>
								<a href="javascript:void(0)" class="easyui-linkbutton"
									data-options="iconCls:'icon-ok'" onclick="submitAdd()">提交</a>
							</span> 
							<span style="margin-left:20px;">
								<a href="javascript:void(0)" class="easyui-linkbutton"
									data-options="iconCls:'icon-cancel'" onclick="clearForm()">清空</a>
							</span>
						</td>
					</tr>
					<tr height="20px"></tr>
				</table>
			
			</form>
		<script type="text/javascript" src="js/sys/user/add.js"></script>	
	</div>
  </body>
</html>
