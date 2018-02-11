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
	<script type="text/javascript" src="js/student/studentmessage/list.js"></script>
	<style>
		.textbox{
			height:20px;
			margin:0;
			padding:0 2px;
			box-sizing:content-box;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			var url_wl="student/studentmessage/export";
			$.ajax({
				url : "sys/authController/checkAuth",
				type : 'POST',
				dataType : 'json',
				data : "url="+url_wl,
				cache : false,//设置为 false 将不会从浏览器缓存中加载请求信息。
				success : function(data) {
					if (data.success == false) {  
						$("#export").css("display", "none"); 
					} 
				},
			});
			
			var url_wl1="student/studentmessage/upload";
			$.ajax({
				url : "sys/authController/checkAuth",
				type : 'POST',
				dataType : 'json',
				data : "url="+url_wl1,
				cache : false,//设置为 false 将不会从浏览器缓存中加载请求信息。
				success : function(data) {
					if (data.success == false) {  
						$("#upload").css("display", "none");  
					} 
				},
			});
		})
	
	
		function exportUser(){
			window.open("student/studentmessage/export");
		}
	 	function importUser(){
			$('#dlg2').dialog('open').dialog('setTitle', '批量添加');
		} 
 		function uploadFile() {
 			var file=$("#file").val();
 			if(file!=null&&file!=""){
				var exec = getFileExec(file) ;      
					if(exec!="xls"&&exec!="xlsx"){
						//alert("只允许上传xls/xlsx格式的文件！") ;
						 $.messager.show({
				             title: "提示",
				             msg: "只允许上传xls/xlsx格式的文件！"
				          });
					}
				else{
					$('#uploadForm').form('submit', {
						url:"student/studentmessage/upload",
						type : "POST",
						dataType :"json",
						success : function(result) {
							var result = eval('('+result+')');  
							//alert(result.success);
							if (result.success==true) { 
                    			$.messager.show({
									title : "提示信息", 
									msg: "上传成功"
								});  
								$("#dlg2").dialog("close");
								$("#studentmessage").datagrid("reload");
							} else {
								$.messager.show({
									title : "提示信息", 
									msg: result.msg
								});  
								$("#dlg2").dialog("close");
								$("#studentmessage").datagrid("reload");
							}
						}
					});
				}
			}else{
				 $.messager.show({
				        title: "提示",
				        msg: "请先上传文件"
				   });
			}
		}  
	 function getFileExec(filename){           //获取文件后缀名
	    	var lenLeft = filename.lastIndexOf(".")+1;  //"."号之前的长度+1
			var lenRight = filename.length;  
			var postf = filename.substring(lenLeft,lenRight);//后缀名  
			return postf.toLowerCase() ;         //所有转换为小写
		}

	</script>
  </head>
  
 <body>
    <table id="studentmessage" class="easyui-datagrid" title="学生信息管理" style="width:700px;height:250px"
            data-options="rownumbers:true,singleSelect:false,url:'student/studentmessage/search',method:'get',toolbar:'#tb',
            pagination:'true',fit:'true',noheader:'true',fitColumns:true">
        <thead>
            <tr>
            	<th checkbox="true" />
                <th data-options="field:'id',width:80,formatter:studentmessage">详细信息</th>
                <th data-options="field:'studentsno',formatter:studentmessageBysno,width:80,sortable:true">学号</th>
                <th data-options="field:'studentname',width:100">姓名</th>
                <th data-options="field:'studentsex',width:100,sortable:true">性别</th>
                <th data-options="field:'studentmajor',width:80">专业</th>
                <th data-options="field:'finaldegree',width:100">最后学历</th>
                <th data-options="field:'graduation',width:150">毕业学校</th>
                <th data-options="field:'expertise',width:80">专业特长</th>
               <th data-options="field:'time',width:150">毕业时间</th>
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
			<select id="studentsex" class="easyui-combobox" style="width:100px;" editable="false">
				<option value="男">男</option> 
			    <option value="女">女</option> 
			</select>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="dosearch()"> 查询</a>
        </div>
    
        <div >
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addstudentmessage()">添加学生信息</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editstudentmessage()">修改学生信息</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deletestudentmessage()">删除学生信息</a>
			 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="exportUser()" id="export">导出excel</a>
			 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-import" plain="true" onclick="importUser()" id="upload">批量导入数据</a>
        </div>
    </div>
   
    <div id="studentmessageform" class="easyui-dialog" style="width:800px;height:500px;padding:0px 20px"
            closed="true" buttons="#dlg-buttons"  data-options="iconCls:'icon-save',minimizable:true,maximizable:true">
        <div class="ftitle">学生信息</div>
	         <form id="studentmessage_form" method="post" novalidate> 
	            <div class="fitem">
	                <label>学号:</label>
	                <input name="studentsno" class="easyui-validatebox" 
	                data-options="required:false" >
	            </div>         
	            <div class="fitem">
	                <label>姓名:</label>
	                <input name="studentname" class="easyui-validatebox" 
	                data-options="required:false" >
	            </div>
	            <div class="fitem">
	                <label>性别:</label>
	                <select  class="easyui-combobox" name="studentsex" style="width:100px;" editable="false">  
			    		<option value="男">男</option>  
			    		<option value="女">女</option>   
					</select>  	            	 
				</div>	
	            <div class="fitem">
	                <label>专业:</label>
	                <input name="studentmajor"  class="easyui-validatebox" 
	                data-options="required:false" />       	 
				</div>					
	            <div class="fitem">
	                <label>最后学历:</label>
	                <input name="finaldegree" class="easyui-validatebox" 
	                data-options="required:false"/>       	 
				</div>		
				<div class="fitem">
	                <label>毕业学校:</label>
	                <input name="graduation" class="easyui-validatebox" 
	                data-options="required:false"/>       	 
				</div>	
				<div class="fitem">
	                <label>专业特长:</label>
	                <input name="expertise" class="easyui-validatebox" 
	                data-options="required:false"/>       	 
				</div>		
	            <div class="fitem">
	                <label>毕业时间:</label>
	                <input name="time" class="easyui-datetimebox" data-options="required:false" editable="false"/>       	 
				</div>				           	            		            	            	            
	        </form>
		    <div id="dlg-buttons">
		        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="save()" style="width:110px">保存信息</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#studentmessageform').dialog('close')" style="width:110px">取消操作</a>
		    </div>
    </div>
     	<div id="dlg2" class="easyui-dialog" style="width:400px;height:180px;padding:10px 20px" closed="true" buttons="#dlg-buttons2">
				<form id="uploadForm" method="post" enctype="multipart/form-data">
					<table>
						<tr>
							<td>上传文件</td>
							<td><input type="file" name="file" id="file">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div id="dlg-buttons2">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="uploadFile()">上传</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg2').dialog('close')">关闭</a>
			</div>
</body>
</html>