<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String teacherSno=(String)request.getAttribute("teacherSno");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>选择教师学生信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
		
	<script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/util/all_easyui.js"></script></head>
	<script type="text/javascript" src="js/volunteer/volunteermessage/teacherselect.js"></script>
	<style>
		.textbox{
			height:20px;
			margin:0;
			padding:0 2px;
			box-sizing:content-box;
		}
	</style>
  </head>
  <input type="hidden" id="sno" value="<%=teacherSno %>"/>
 <body onload="showMessage()">
    <table id="volunteermessage" class="easyui-datagrid" title="志愿信息管理" style="width:700px;height:250px"
            data-options="rownumbers:true,singleSelect:false,method:'get',toolbar:'#tb',
            pagination:'true',fit:'true',noheader:'true',fitColumns:true">
        <thead>
            <tr>
            	<th checkbox="true" />
                <th data-options="field:'studentsno',formatter:studentmessageBysno,width:80,sortable:true">学生学号</th>
                <th data-options="field:'teachersno',formatter:teachermessageBysno,width:100,sortable:true">教师工号</th>
                <th data-options="field:'rank',width:100,sortable:true,formatter:volunteer">志愿</th>
            </tr>
        </thead>
    </table>
	<!-- 工具栏 -->
    <div id="tb" style="padding:5px;height:auto">
        <div>
			<span>学生学号:</span> 
			<input id="studentsno" class="textbox" />
			<span>志愿:</span> 
			<select id="rank" class="easyui-combobox" style="width:100px;" editable="false">
				<option value="1">第一志愿</option> 
			    <option value="2">第二志愿</option>
			    <option value="3">第三志愿</option> 
			</select>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="dosearch()"> 查询</a>
        </div>
    </div>
</body>
</html>