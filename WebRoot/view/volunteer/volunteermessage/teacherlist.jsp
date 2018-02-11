<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/util/all_easyui.js"></script></head>
	<script type="text/javascript" src="js/volunteer/volunteermessage/teacherlist.js"></script>
	<style>
		.textbox{
			height:20px;
			margin:0;
			padding:0 2px;
			box-sizing:content-box;
		}
	</style>
		<script type="text/javascript">
			function show(){
				var starttime=$("#starttime").val();
				var endtime=$("#endtime").val();
				var stDate = zhuanhua(starttime);
				var etDate = zhuanhua(endtime);
				var select ="填写志愿的时间为"+stDate+"到"+etDate+",请在一定时间内完成填写";
				$("#select").html(select);
			} 
			function showTimes(){
				var starttime=$("#starttime").val()*1000;
				var endtime=$("#endtime").val()*1000;
			
				//获取当前日期
				var now = new Date(new Date().getTime());
				//var time = now.getTime();    //获取当前时间(从1970.1.1开始的毫秒数)
				if(now>endtime||now<starttime){
					
					$("#teacherSelect").hide();
				}else{
					$("#teacherSelect").show();
				}
	 
			}
			
			//设置1秒调用一次showTimes函数
			setInterval("showTimes()",1000);
			
</script>
  </head>
  <input type="hidden" id="starttime" value="${manage.starttime }"/>
 <input type="hidden" id="endtime" value="${manage.endtime }"/>
 <body onload="show()">
	 <div  style="height:10%;width:100%;float:left">
	 	
	 		<ul>
	
					<li  id="select">
								
					</li>
			</ul>
	 	</div>
	 <div  style="height:90%;width:100%;float:left;display:none" id="teacherSelect">
	    <table id="studentmessage" class="easyui-datagrid" title="学生信息管理" style="width:700px;height:250px"
	            data-options="rownumbers:true,singleSelect:false,url:'student/studentmessage/search',method:'get',toolbar:'#tb',
	            pagination:'true',fit:'true',noheader:'true',fitColumns:false">
	        <thead frozen="true">
	            <tr>
	            	<th checkbox="true" />
	                <th data-options="field:'id',width:80,formatter:studentmessage,">详细信息</th>
	                <th data-options="field:'studentsno',formatter:studentmessageBysno,width:80,sortable:true">学号</th>
	                <th data-options="field:'studentname',width:100">姓名</th>
	            </tr>
	       </thead>
	       <thead>
	            <tr>
	                <th data-options="field:'studentsex',width:100,sortable:true">性别</th>
	                <th data-options="field:'studentmajor',width:80">专业</th>
	                <th data-options="field:'finaldegree',width:100">最后学历</th>
	                <th data-options="field:'graduation',width:150">毕业学校</th>
	                <th data-options="field:'expertise',width:80">专业特长</th>
	               <th data-options="field:'time',width:200">毕业时间</th>
	            </tr>
	        </thead>
	    </table>
		<!-- 工具栏 -->
	    <div id="tb" style="padding:5px;height:auto">
	        <div>
				<span>学号:</span> 
				<input id="studentsno" class="textbox" />
				<span>姓名:</span> 
				<input id="studentname" class="textbox"/>
				<span>性别:</span> 
				<select id="studentsex" class="easyui-combobox" style="width:100px;">
					<option value="男">男</option> 
				    <option value="女">女</option> 
				</select>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="dosearch()"> 查询</a>
	        </div>
	    
	        <div >
	        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addvolunteermessage()">添加志愿信息</a>
	    <!--   	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editstudentmessage()">修改学生信息</a>
	            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deletestudentmessage()">删除学生信息</a> -->
	        </div>
	    </div>
	   </div>
</body>
</html>