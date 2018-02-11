<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教师信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
		
	<script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/util/all_easyui.js"></script></head>
	<script type="text/javascript" src="js/teacher/teachermessage/list.js"></script>
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
			var url_wl="teacher/teachermessage/export";
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
			
			var url_wl1="teacher/teachermessage/upload";
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
			window.open("teacher/teachermessage/export");
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
						url:"teacher/teachermessage/upload",
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
								$("#teachermessage").datagrid("reload");
							} else {
								$.messager.show({
									title : "提示信息", 
									msg: result.msg
								});  
								$("#dlg2").dialog("close");
								$("#teachermessage").datagrid("reload");
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
    <table id="teachermessage" class="easyui-datagrid" title="教师信息管理" style="width:700px;height:250px"
            data-options="rownumbers:true,singleSelect:false,url:'teacher/teachermessage/search',method:'get',toolbar:'#tb',
            pagination:'true',fit:'true',noheader:'true',fitColumns:true">
        <thead>
            <tr>
            	<th checkbox="true" />
                <th data-options="field:'id',width:80,formatter:teachermessage">详细信息</th>
                <th data-options="field:'teachersno',formatter:teachermessageBysno,width:80,sortable:true">学号</th>
                <th data-options="field:'teachername',width:100">姓名</th>
                <th data-options="field:'teachersex',width:100,sortable:true">性别</th>
                <th data-options="field:'teachermajor',width:80">专业</th>
                <th data-options="field:'degree',width:100">最后学历</th>
                <th data-options="field:'guidemin',width:100">指导最少个数</th>
                <th data-options="field:'guidemax',width:100">指导最多个数</th>
            </tr>
        </thead>
    </table>
	<!-- 工具栏 -->
    <div id="tb" style="padding:5px;height:auto">
        <div>
			<span>学号:</span> 
			<input id="teachersno" class="textbox" />
			<span>姓名:</span> 
			<input id="teachername" class="textbox"/>
			<span>性别:</span> 
			<select id="teachersex" class="easyui-combobox" style="width:100px;" editable="false">
				<option value="男">男</option> 
			    <option value="女">女</option> 
			</select>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="dosearch()"> 查询</a>
        </div>
    
        <div >
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addteachermessage()">添加教师信息</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editteachermessage()">修改教师信息</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="deleteteachermessage()">删除教师信息</a>
             <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="exportUser()" id="export">导出excel</a>
			 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-import" plain="true" onclick="importUser()" id="upload">批量导入数据</a>
        </div>
    </div>
   
    <div id="teachermessageform" class="easyui-dialog" style="width:700px;height:500px;padding:0px 20px"
            closed="true" buttons="#dlg-buttons"  data-options="iconCls:'icon-save',minimizable:true,maximizable:true">
        <div class="ftitle">教师信息</div>
	         <form id="teachermessage_form" method="post" novalidate> 
	            <div class="fitem">
	                <label>学号:</label>
	                <input name="teachersno" class="easyui-validatebox" 
	                data-options="required:false" >
	            </div>         
	            <div class="fitem">
	                <label>姓名:</label>
	                <input name="teachername" class="easyui-validatebox" 
	                data-options="required:false" >
	            </div>
	            <div class="fitem">
	                <label>性别:</label>
	                <select  class="easyui-combobox" name="teachersex" style="width:150px;" editable="false">  
			    		<option value="男">男</option>  
			    		<option value="女">女</option>   
					</select>  	            	 
				</div>	
	            <div class="fitem">
	                <label>专业:</label>
	                <input name="teachermajor"  class="easyui-validatebox" 
	                data-options="required:false" />       	 
				</div>					
	            <div class="fitem">
	                <label>学历:</label>
	                <select  class="easyui-combobox" name="degree" style="width:150px;" editable="false">  
			    		<option value="博士">博士</option>  
			    		<option value="硕士">硕士</option>
			    		<option value="学士">学士</option>    
					</select>       	 
				</div>		
				<div class="fitem">
	                <label>指导最大个数:</label>
	                <input name="guidemax" class="easyui-validatebox"  data-options="required:false"/>       	 
				</div>				           	            		            	            	            
	        </form>
		    <div id="dlg-buttons">
		        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="save()" style="width:110px">保存信息</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#teachermessageform').dialog('close')" style="width:110px">取消操作</a>
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