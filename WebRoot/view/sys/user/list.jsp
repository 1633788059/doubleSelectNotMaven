<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>用户管理</title>

<script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jslib/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="js/jslib/jquery-easyui-1.3.3/themes/default/easyui.css"  id="swicth-style" type="text/css"></link>
<link rel="stylesheet" href="js/jslib/jquery-easyui-1.3.3/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="js/jslib/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<link rel="stylesheet" href="css/index.css" type="text/css"></link>

<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/index.js"></script>
	

<script type="text/javascript" src="js/sys/user/list.js"></script>


</head>
<body>

	<table id="listUser" class="easyui-datagrid" fit="true" toolbar="#tb" striped="true" 
    	pagination="true" url="sys/userController/search"  rownumbers="true" 
    	fitColumns="false" striped='true' remoteSort="false" idField="id">
    	
		<thead>
			<tr>
				<th checkbox="true" />
				<th field="id" width="140" formatter="addCol" align="center">操作</th>
				<th field="name" width="150" align="center" sortable="true">用户名</th>
				<th field="pwd" width="150" align="center">密码</th>
				<th field="roleIds" width="150" align="center" hidden="true">角色id</th>
				<th field="roleNames" width="250" align="center" sortable="true">所属角色名称</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="margin:0 auto;" fit="true">
		
			<span>用户名:</span> <input id="name" maxlength="20" 
				style="line-height:20px;border:1px solid #ccc" size="20"/>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'" onclick="doSearch()">开始查找</a>
			
			<br/>
			
			<a href="javascript:void(0);" data-options="plain:true,iconCls:'icon-add'"
				class="easyui-linkbutton" onclick="formAdd()">添加</a>
			<a href="javascript:void(0);" data-options="plain:true,iconCls:'icon-remove'"
				class="easyui-linkbutton" onclick="cancleChoice()">取消已选</a>
			<a href="javascript:void(0);" data-options="plain:true,iconCls:'icon-cancel'"
				class="easyui-linkbutton" onclick="deleteData()">批量删除</a>
		
	</div>
	
	<div id="win" style="width:400px;height:200px;"></div>
  
</body>
</html>
