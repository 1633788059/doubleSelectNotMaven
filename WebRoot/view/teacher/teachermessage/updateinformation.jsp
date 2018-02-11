<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String sno=(String)request.getAttribute("teacherSno");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
    
    <title>个人管理</title>
    
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">	
		<meta http-equiv="Content-Language" content="zh-cn" />
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<link rel="stylesheet" href="css/Form.css" type="text/css" />
		<script type="text/javascript" src='js/Validate.js'></script>
		<script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/util/all_easyui.js"></script></head>
		<style type="text/css">
			.tdbt {
				text-align: center;
				width: 20%;
				height: 18px;
				border: 1px solid #89BFA7;
				font-size: 9pt;
			}
			table {
			margin: 0 auto;
			border: 1px solid #89BFA7;
			border-collapse: collapse;
			border-spacing: 0;
			empty-cells: show;
			width:760px;
			}
		
		tbody td {
			border: 1px solid #89BFA7;
			font-size: 9pt;
			padding-left: 2px;
			padding-right: 2px;
			line-height:5mm;
			height:6mm;
		}
		
		thead td, tfoot td {
			text-align: center;
			background-color:#BFE5D5;
			line-height:3mm;
			height:3mm;
			}
			.tdnr {
				text-align: left;
				width: 80%;
				height: 18px;
				border: 1px solid #89BFA7;
				font-size: 9pt;
			}
			
			.foot {
				text-align: center;
				background-color: #BFE5D5;
				line-height: 3mm;
			}
			
			.button {
				border: 1px solid #CCCCCC;
				background-color: #F5FAFF; /*F5FAFF*/
				padding: 2px 2px 0px 2px;
			}
		</style>

		<script type="text/javascript">
			$(function(){
				$("#teachersex").val("${teacher.teachersex}");
				$("#degree").val("${teacher.degree}");
			})
		/* 	function check_dzyx(obj) {
				if (obj.value == null || obj.value == "") {
					return true;
				} else if ((!IsEmail(obj.value)) || obj.length > 50) {
					alert("电子邮箱输入错误或者长度超过了50个字符！");
					obj.select();
					obj.focus();
					return false;
				}
			}
			function check_dh(obj) {
				if(obj.value == null || obj.value == "") {
					return true;
				}else if ((!IsMobileTel(obj.value))) {
					alert("电话号码输入错误！");
					obj.select();
					obj.focus();
					return false;
				}
			} */
		/* 	function check_sfzjh(obj) {
				if (obj.value == null || obj.value == "") {
					return true;
				} else if (!isValidIdCardNo(obj.value)) {
					//(!IsInteger(obj.value)) || (obj.value.length != 15 && obj.value.length != 18)
					alert("请输入有效的身份证号!");
					obj.select();
					obj.focus();
					return false;
				}
			} */
		/* 	function check_csrq() {
				if (document.getElementById("csrq") != null) {
					if (document.getElementById("csrq").value == "") {
						alert("出生日期不能为空！");
						return false;
					} else
						return true;
				} else
					return true;
			} */	
			function savaData(){
				var id=Trim($("#id").val());
				var teachersno=Trim($("#teachersno").val());
				var teachersex=Trim($("#teachersex").val());
				var teachermajor=Trim($("#teachermajor").val());
				var degree=Trim($("#degree").val());
				var guidemin=Trim($("#guidemin").val());
				var teachername=Trim($("#teachername").val());
				var guidemax=Trim($("#guidemax").val());
				//$("#time").datebox('getValue'); 也可以得到datebox的数值
				if(id==""||teachersno==""||teachersex==""||teachermajor==""||degree==""||guidemin==""||guidemax==""||teachername==""){
					 $.messager.show({
				           title: '提示',
				           timeout:5000,
				           msg:'请完善信息'
				       });
					return false;
				}else{
					$.ajax({
						type:"post" ,
						url:"teacher/teachermessage/update" ,
						dataType:"json" ,
						data:{id:id,teachersno:teachersno,teachersex:teachersex,teachername:teachername,degree:degree,teachermajor:teachermajor,guidemin:guidemin,guidemax:guidemax},
						success:function(data){
							if(data.success==true){
								$.messager.alert('提示',data.msg,'info');
								
								setTimeout("window.location.reload()",5000);
							}else{
								 $.messager.show({
							           title: '提示',
							           timeout:5000,
							           msg:'请完善信息'
							       });
							}
						}
					}) ;
				}
			}
		</script>
	
  </head>
  
 <body>
		<div id='thePageArea'>
			<table border=0 width="780px" height="98%" cellspacing="0" cellpadding="0" align="center">
				<tbody>
					<tr>
						<td align="center" height='26px'>
							<div id="theOptArea">
								<div id="divTitle">增改基本信息</div>
								
								<div id="divButton">
									<input type="button" id="btnFi" value="家庭成员" class="button" style="display: none;"  />
								</div>		
								<div id="divLine"></div>		
							</div>							
						</td>
					</tr>
					<tr>
						<td>
							<div id="time_time">时间区段：2014-11-17 00:00:00.0---2019-12-31 16:49:00.0</div>
						</td>
					</tr>
					<tr>
						<td align="center" >
						<form method="post" id="ActionForm" >
								<input type="hidden" id="id" name="id" value="${teacher.id}"/>
								<input type="hidden" id="teachersno" name="teachersno"  value="${teacher.teachersno}"/>
								<input type="hidden" id="guidemin"   value="${teacher.guidemin}"/>
								<input type="hidden" id="guidemax"   value="${teacher.guidemax}"/>
							<table style="width: 400px; border: 1px solid #89BFA7; border-collapse: collapse;">
								<thead style="height: 15px; background-color: #BFE5D5">
									<tr>
										<td colspan="2"></td>
									</tr>
								</thead>
								<tr>
									<td style="width:20%" class="tdbt">姓名</td>
									<td style="width:80%" class="tdnr">
									<input type="text" name="teachername" class="input" id="teachername" value="${teacher.teachername}" style="width:100%"/>
									</td>
								</tr>
								<tr>
									<td class="tdbt">性别</td>
									<td class="tdnr">
									<select name="teachersex" id="teachersex" style="width:100%" >
										<option value="男">男</option>  
			    						<option value="女">女</option>   
									</select>
									</td>
								</tr>
								<tr>
									<td class="tdbt">专业</td>
									<td class="tdnr">
									<input type="text" name="teachermajor" class="input" id="teachermajor"  style="width:100%" value="${teacher.teachermajor}"/>
									</td>
								</tr>
				
								<tr>
									<td class="tdbt">学历</td>
									<td class="tdnr">
									
										<select name="degree" id="degree" style="width:100%" >
											<option value="博士">博士</option>  
				    						<option value="硕士">硕士</option> 
				    						<option value="学士">学士</option>    
										</select>
									</td>
								</tr>
				
						
								<tr>
									<td style="text-align:center;padding-top: 10px; padding-bottom: 10px;" colspan="2">
										<input type="button" value="保存" onclick="savaData()" name="saveBtn" class="button" />
									</td>
								</tr>				
				
								<tfoot style="height: 15px; background-color: #BFE5D5">
									<tr>
										<td colspan="2"></td>
									</tr>
								</tfoot>
							</table>
						</form>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
