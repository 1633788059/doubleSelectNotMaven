<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>匹配信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
		
	<script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/util/all_easyui.js"></script></head>
	<script type="text/javascript" src="js/match/matchmessage/list.js"></script>
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
    <table id="matchmessage" class="easyui-datagrid" title="志愿信息管理" style="width:700px;height:250px"
            data-options="rownumbers:true,singleSelect:false,url:'match/matchmessage/search',method:'get',toolbar:'#tb',
            pagination:'true',fit:'true',noheader:'true',fitColumns:true">
        <thead>
            <tr>
            	<th checkbox="true" />
                <th data-options="field:'studentsno',formatter:studentmessageBysno,width:80,sortable:true">学生学号</th>
                <th data-options="field:'teachersno',formatter:teachermessageBysno,width:100,sortable:true">教师工号</th>
            </tr>
        </thead>
    </table>
	<!-- 工具栏 -->
    <div id="tb" style="padding:5px;height:auto">
        <div>
			<span>学生学号:</span> 
			<input id="studentsno" class="textbox" />
			<span>教师工号:</span> 
			<input id="teachersno" class="textbox"/>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="dosearch()"> 查询</a>
        </div>
    
        <div >
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addmatchmessage()">添加匹配信息</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editmatchmessage()">修改匹配信息</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deletematchmessage()">删除匹配信息</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="startmatch()">开始匹配信息</a>
            <!--   <a href="volunteer/volunteermessage/test" class="easyui-linkbutton" iconCls="icon-cut" plain="true">删除学生</a> -->
        </div>
    </div>
   
     <div id="matchmessageform" class="easyui-dialog" style="width:700px;height:500px;padding:0px 20px"
            closed="true" buttons="#dlg-buttons"  data-options="iconCls:'icon-save',minimizable:true,maximizable:true">
        <div class="ftitle">学生信息</div>
	         <form id="matchmessage_form" method="post" novalidate> 
	            <div class="fitem">
	                <label>学生学号:</label>
	                <input name="studentsno" class="easyui-searchbox" readonly="true"
					data-options="searcher:dosearchstudent,prompt:'请输入学生学号',menu:'#mm',required:true"/>
	            </div>
	            <div id="chaxunstudent" style="height:auto;display:none ;top:235px;background-color: #fff;">
						<div style="height:auto;width:497px;border:1px solid #95B8E7;">
							 <div id="closestudentTab" style="width:496px;height: 25px">
						 	<span style="margin-left: 10px;color:red">提示：双击选择的数据所在的行即可。</span>
							<span style="margin-left: 207px">
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="closestudent()">关闭</a>
							</span>
							 </div>
						    <table id="studentTab">
							</table>
							<div style="margin-top:10px;"></div>
						</div>
				</div>	    
	            <div class="fitem">
	                <label>教师学号:</label>
	                <input name="teachersno" class="easyui-searchbox" readonly="true"
					data-options="searcher:dosearchteacher,prompt:'请输入老师工号',menu:'#mm',required:true" />
	            </div>
	            <div id="chaxunteacher" style="height:auto;display:none ;top:235px;background-color: #fff;">
						<div style="height:auto;width:497px;border:1px solid #95B8E7;">
							 <div id="closeteacherTab" style="width:496px;height: 25px">
						 	<span style="margin-left: 10px;color:red">提示：双击选择的数据所在的行即可。</span>
							<span style="margin-left: 207px">
								<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="closeteacher()">关闭</a>
							</span>
							 </div>
						    <table id="teacherTab">
							</table>
							<div style="margin-top:10px;"></div>
						</div>
				</div>	   				           	            		            	            	            
	        </form>
		    <div id="dlg-buttons">
		        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="save()" style="width:110px">保存信息</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#matchmessageform').dialog('close')" style="width:110px">取消操作</a>
		    </div>
    </div>
</body>
</html>