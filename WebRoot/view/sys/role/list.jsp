<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>角色管理</title>
    <script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jslib/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<link rel="stylesheet" href="js/jslib/jquery-easyui-1.3.3/themes/default/easyui.css"  id="swicth-style" type="text/css"></link>
	<link rel="stylesheet" href="js/jslib/jquery-easyui-1.3.3/themes/icon.css" type="text/css"></link>
	<script type="text/javascript" src="js/jslib/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
	
	<link rel="stylesheet" href="css/index.css" type="text/css"></link>
	
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	
	<script type="text/javascript" src="js/sys/role/list.js"></script>
	
	
  </head>
  
  <body>
    <table id="listRole" class="easyui-datagrid" fit="true" remoteSort="false"
		url="sys/roleController/search" toolbar="#tb" rownumbers="true" 
		pagination="true" fitColumns="false" striped='true'>
		
		<thead>
			<tr>
				<th checkbox="true" />
				<th field="id" width="160" formatter="addCol" align="center" sortable="true">操作</th>
				<th field="name" width="400" align="center" sortable="true">角色名称</th>			
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="margin:0 auto;" fit="true">
		
			<span>角色名:</span> <input id="name" maxlength="20" 
				style="line-height:20px;border:1px solid #ccc" size="20"/>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'" onclick="doSearch()">开始查找</a>
		
			<br/>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				 iconCls="icon-add" plain="true" onclick="add()">添加</a> 
			<a href="javascript:void(0);"  data-options="plain:true,iconCls:'icon-remove'"
				class="easyui-linkbutton" onclick="clearRoleSelections()">取消已选</a>	
			<a href="javascript:void(0);" data-options="iconCls:'icon-cancel'" plain="true"
				class="easyui-linkbutton" onclick="deleteData()">批量删除</a>
		
	</div>
	
	<div id="win" style="width:400px;height:200px;"></div>
  </body>
</html>
