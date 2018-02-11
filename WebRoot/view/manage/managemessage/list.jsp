<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>时间管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
		
	<script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/util/all_easyui.js"></script></head>
	<script type="text/javascript" src="js/manage/managemessage/list.js"></script>
	<style>
		.textbox{
			height:20px;
			margin:0;
			padding:0 2px;
			box-sizing:content-box;
			}
	</style>
  </head>
  
 <body>
    <table id="managemessage" class="easyui-datagrid" title="时间管理" style="width:700px;height:250px"
            data-options="rownumbers:true,singleSelect:false,url:'manage/managemessage/search',method:'get',toolbar:'#tb',
            pagination:'true',fit:'true',noheader:'true',fitColumns:true">
        <thead>
            <tr>
            	<th checkbox="true" /><!-- 
                <th data-options="field:'id',width:80">详细信息</th> -->
                <th data-options="field:'starttime',width:80,sortable:true,formatter:zhuanhua">起始时间</th>
                <th data-options="field:'endtime',width:100,formatter:zhuanhua">终止时间</th>
                <th data-options="field:'value',width:100,sortable:true">方向</th>
                <th data-options="field:'note',width:80">说明</th>
            </tr>
        </thead>
    </table>
	<!-- 工具栏 -->
    <div id="tb" style="padding:5px;height:auto">
        <div>
			<span>时间段:</span> 
			<input id="starttime"  class="easyui-datetimebox" />
			<span>-</span> 
			<input id="endtime" class="easyui-datetimebox"/>
			<span>(注释:请按照时间前后顺序填写)</span> 
			<span>方向:</span> 
			<input id="value" class="textbox"/>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="dosearch()"> 查询</a>
        </div>
    
        <div >
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addmanagemessage()">添加时间管理</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editmanagemessage()">修改时间管理</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deletemanagemessage()">删除时间管理</a>
        </div>
    </div>
   
    <div id="managemessageform" class="easyui-dialog" style="width:700px;height:500px;padding:0px 20px"
            closed="true" buttons="#dlg-buttons"  data-options="iconCls:'icon-save',minimizable:true,maximizable:true">
        <div class="ftitle">时间管理信息</div>
	         <form id="managemessage_form" method="post" novalidate> 
	         	<!-- <input name="id" hidden/> -->
	            <div class="fitem">
	                <label>开始时间:</label>
	                <input name="starttime" class="easyui-datetimebox" 
	                data-options="required:false" >
	            </div>         
	            <div class="fitem">
	                <label>终止时间:</label>
	                <input name="endtime" class="easyui-datetimebox"
	                data-options="required:false" >
	            </div>
	            <div class="fitem">
	                <label>方向:</label>
	                <input name="value"  class="easyui-validatebox" 
	                data-options="required:false" />             	 
				</div>	
	            <div class="fitem">
	                <label>说明:</label>
	                <input name="note"  class="easyui-validatebox" 
	                data-options="required:false" />       	 
				</div>		 	            		            	            	            
	        </form>
		    <div id="dlg-buttons">
		        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="save()" style="width:110px">保存信息</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#managemessageform').dialog('close')" style="width:110px">取消操作</a>
		    </div>
    </div>
</body>
</html>