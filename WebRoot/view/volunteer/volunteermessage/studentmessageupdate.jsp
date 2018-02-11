<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String studentSno=(String)request.getAttribute("studentSno");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>志愿信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/util/all_easyui.js"></script></head>
	<script type="text/javascript" src="js/volunteer/volunteermessage/studentmessageupdate.js"></script>
	<style>
		.textbox{
			height:20px;
			margin:0;
			padding:0 2px;
			box-sizing:content-box;
		}
	</style>
	<script type="text/javascript">
			function showMessage(){
			var studentsno = $("#sno").val();
			var starttime=$("#starttime").val();
			var endtime=$("#endtime").val();
			var stDate = zhuanhua(starttime);
			var etDate = zhuanhua(endtime);
			var select ="修改志愿的时间为"+stDate+"到"+etDate+",请在一定时间内完成填写";
			$("#select").html(select);
				var starttime1=$("#starttime").val()*1000;
				var endtime1=$("#endtime").val()*1000;
			
				//获取当前日期
				var now = new Date(new Date().getTime());
				//var time = now.getTime();    //获取当前时间(从1970.1.1开始的毫秒数)
				if(now>endtime1||now<starttime1){
					
				}else{
					$("#volunteermessage").datagrid({
						url:'volunteer/volunteermessage/search?studentsno='+studentsno+"&rank=5", 
					});	
				}
			}
			function showTimes(){
				var starttime=$("#starttime").val()*1000;
				var endtime=$("#endtime").val()*1000;
			
				//获取当前日期
				var now = new Date(new Date().getTime());
				//var time = now.getTime();    //获取当前时间(从1970.1.1开始的毫秒数)
				if(now>endtime||now<starttime){
					
					$("#studentUpdate").hide();
				}else{
					$("#studentUpdate").show();
				}
	 
			}
			
			//设置1秒调用一次showTimes函数
			setInterval("showTimes()",1000);
			
</script>
  </head>
    <input type="hidden" id="sno" value="<%=studentSno %>"/>
    
 <input type="hidden" id="starttime" value="${manage.starttime }"/>
 <input type="hidden" id="endtime" value="${manage.endtime }"/>
 <body onload="showMessage()">
 	<div  style="height:10%;width:100%;float:left">
 	
 		<ul>

				<li  id="select">
							
				</li>
		</ul>
 	</div>
	 <div  style="height:90%;width:100%;float:left;display:none" id="studentUpdate" >
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
			<span>教师工号:</span> 
			<input id="teachersno" class="textbox"/>
			<span>志愿:</span> 
			<select id="rank" class="easyui-combobox" style="width:100px;" editable="false">
				<option value="1">学生第一志愿</option> 
			    <option value="2">学生第二志愿</option>
			    <option value="3">学生第三志愿</option> 
			</select>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="dosearch()"> 查询</a>
        </div>
    
        <div >
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editvolunteermessage()">修改志愿信息</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deletevolunteermessage()">删除志愿信息</a>
        </div>
    </div>
   
     <div id="volunteermessageform" class="easyui-dialog" style="width:700px;height:500px;padding:0px 20px"
            closed="true" buttons="#dlg-buttons"  data-options="iconCls:'icon-save',minimizable:true,maximizable:true">
        <div class="ftitle">学生信息</div>
	         <form id="volunteermessage_form" method="post" novalidate> 
	            <div class="fitem" style="display:none">
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
	            <div class="fitem" style="display:none">
	                <label>志愿选择:</label>
	                <select  class="easyui-combobox" name="rank" style="width:150px;" editable="false" onfocus="this.defaultIndex=this.selectedIndex;" onchange="this.selectedIndex=this.defaultIndex;">
			    			<option value="1">学生第一志愿</option> 
						    <option value="2">学生第二志愿</option>
						    <option value="3">学生第三志愿</option> 
						    <option value="4">导师志愿</option>    
					</select>  	            	 
				</div>				           	            		            	            	            
	        </form>
		    <div id="dlg-buttons">
		        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="save()" style="width:110px">保存信息</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#volunteermessageform').dialog('close')" style="width:110px">取消操作</a>
		    </div>
   	 </div>
    </div>
</body>
</html>