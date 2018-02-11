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
    
    <title>申请志愿</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/util/all_easyui.js"></script></head>
	<script language='javascript' src='bootstrap/js/bootstrap.min.js'></script>
	<link href='bootstrap/css/bootstrap.min.css' rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="js/volunteer/volunteermessage/studentlist.js"></script>
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
					
					$("#studentSelect").hide();
				}else{
					$("#studentSelect").show();
				}
	 
			}
			
			//设置1秒调用一次showTimes函数
			setInterval("showTimes()",1000);
			
</script>
  </head>
 <input type="hidden" id="sno" value="<%=studentSno %>"/>
 <input type="hidden" id="starttime" value="${manage.starttime }"/>
 <input type="hidden" id="endtime" value="${manage.endtime }"/>
 <body onload="show()">
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="container-fluid text-center lead">
				<div class="row-fluid">
					<div class="span12">
						<p class="text-left lead text-success">
							提示：
						</p>
						<ul>
							<li class="text-left lead text-success">
								各志愿导师在确定知道学生时具备同等优先级，因此一旦你填报任意一志愿导师确定了你，原则上你就不允许在改动。
							</li>
							<li class="text-left lead text-success">
								如果你已经提前与某导师沟通好，在填报志愿时请只将该导师填写在第一志愿，不要在填写第二、第三志愿，以免造成不必要的麻烦.
							</li>
							<li class="text-left lead text-success" id="select">
							
							</li>
						</ul>
						<p class="text-left text-success">
						</p>
					</div>
				</div>
			</div>
			<div class="row-fluid" id="studentSelect" style="display:none">
				<div class="span12">
					<table align="center" border="1" cellpadding="2" cellspacing="2" style="width:800px;height:200px">
						<caption>
						<h1><span dir="rtl">请选择你申请志愿的导师</span></h1>
						</caption>
						<tbody align="center"> 
							<tr style="width:800px;height:50px">
								<td>志愿选择</td>
								<td>教师工号</td>
							</tr>
							<tr style="width:800px;height:50px">
								<td style="width:400px;height:50px">第一志愿</td>
								<td>
								 <div>
	                				<label>教师学号:</label>
					                <input name="teachersno1" class="easyui-searchbox" style="line-height:50px;vertical-align: middle; height:25px"
									data-options="searcher:dosearchteacher1,prompt:'请输入老师工号',menu:'#mm',required:true" />
					            </div>
					            <div id="chaxunteacher1" style="height:auto;display:none ;top:235px;background-color: #fff;">
									<div style="height:auto;width:497px;border:1px solid #95B8E7;">
										 <div id="closeteacherTab1" style="width:496px;height: 25px">
									 	<span style="margin-left: 10px;color:red">提示：双击选择的数据所在的行即可。</span>
										<span style="margin-left: 20px">
											<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="closeteacher1()">关闭</a>
										</span>
										 </div>
									    <table id="teacherTab1">
										</table>
										<div style="margin-top:10px;"></div>
									</div>
								</div>
								</td>
							</tr>
							<tr style="width:800px;height:50px">
								<td style="width:400px;height:50px">第二志愿</td>
								<td>
								 <div>
	                				<label>教师学号:</label>
					                <input name="teachersno2" class="easyui-searchbox" style="line-height:50px;vertical-align: middle; height:25px"
									data-options="searcher:dosearchteacher2,prompt:'请输入老师工号',menu:'#mm',required:true" />
					            </div>
					            <div id="chaxunteacher2" style="height:auto;display:none ;top:235px;background-color: #fff;">
								<div style="height:auto;width:497px;border:1px solid #95B8E7;">
									 <div id="closeteacherTab2" style="width:496px;height: 25px">
								 	<span style="margin-left: 10px;color:red">提示：双击选择的数据所在的行即可。</span>
									<span style="margin-left: 20px">
										<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="closeteacher2()">关闭</a>
									</span>
									 </div>
								    <table id="teacherTab2">
									</table>
									<div style="margin-top:10px;"></div>
								</div>
								</div>
								</td>
							</tr>
							<tr style="width:800px;height:50px">
								<td style="width:400px;height:50px">第三志愿</td>
								<td>
								 <div>
	                				<label>教师学号:</label>
					                <input name="teachersno3" class="easyui-searchbox" style="line-height:50px;vertical-align: middle; height:25px"
									data-options="searcher:dosearchteacher3,prompt:'请输入老师工号',menu:'#mm',required:true" />
					            </div>
					            <div id="chaxunteacher3" style="height:auto;display:none ;top:235px;background-color: #fff;">
								<div style="height:auto;width:497px;border:1px solid #95B8E7;">
									 <div id="closeteacherTab3" style="width:496px;height: 25px">
								 	<span style="margin-left: 10px;color:red">提示：双击选择的数据所在的行即可。</span>
									<span style="margin-left: 20px">
										<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="closeteacher3()">关闭</a>
									</span>
									 </div>
								    <table id="teacherTab3">
									</table>
									<div style="margin-top:10px;"></div>
								</div>
								</div>
								</td>
							</tr>
							<tr style="width:800px;height:50px">
								<td colspan="2" align="right">
									 <button class="btn btn-primary" type="button" onclick="addvolunteer()">提交</button>
									 &nbsp;&nbsp;&nbsp;								
								</td>
							</tr>
						</tbody>
					</table>
					
					<p>&nbsp;</p>
				</div>
			</div>
		</div>
	</div>
</div>
   
</body>
</html>