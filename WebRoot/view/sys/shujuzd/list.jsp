<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据字典</title>
     <script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jslib/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
	<link rel="stylesheet" href="js/jslib/jquery-easyui-1.3.3/themes/default/easyui.css"  id="swicth-style" type="text/css"></link>
	<link rel="stylesheet" href="js/jslib/jquery-easyui-1.3.3/themes/icon.css" type="text/css"></link>
	<script type="text/javascript" src="js/jslib/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/jslib/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
	
	
	<link rel="stylesheet" href="css/index.css" type="text/css"></link>
	
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
  </head>
  
  <body>
  
  	<table id="dg" class="easyui-datagrid" fit="true" toolbar="#tb" striped="true" 
    	pagination="true" rownumbers="true" singleSelect="false"
    	fitColumns="false" striped='true' remoteSort="false">
    	
		<thead>
			<tr>
				<th data-options="field:'id'" hidden="true">id</th>
				<th field="zdlb" width="180" editor="{type:'validatebox',options:{required:true}}">字典类别</th>
                <th field="zddm" width="180" editor="{type:'validatebox',options:{required:true}}">字典代码</th>
                <th field="zdms" width="250" editor="{type:'validatebox',options:{required:false}}">字典描述</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="margin:0 auto;height:auto" fit="true">
		
			字典类别:<input id="zdlb" maxlength="20" 
				style="line-height:20px;border:1px solid #ccc" size="20"/>
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'icon-search'" onclick="ds()">开始查找</a>
			 
			<br/>
			
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">添加</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">提交</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">撤销</a>
				
		
	</div>
	
	<script type="text/javascript">
        $(function(){
            $('#dg').edatagrid({
                url: 'sys/shujuzdController/search',
                saveUrl: 'sys/shujuzdController/add',
                updateUrl: 'sys/shujuzdController/update',
                destroyUrl: 'sys/shujuzdController/delete'
            });
        });
        function ds(){
			var zdlb = $('#zdlb').val(); 
			$('#dg').datagrid({
				pageNumber : 1,
				queryParams : {
					zdlb : zdlb
				}
			});
		}
    </script>
  </body>
</html>
