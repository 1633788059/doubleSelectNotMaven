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
	<link rel="stylesheet" href="css/tabs.css" type="text/css" />
	<style type="text/css">
		div.tab_container table td{text-align:left;}
		textarea{padding:2px;}
		tr.E {background-color: #E5EBF1;cursor:pointer;}
		.tab_container {float: left;width: 100%;height: 100%;}
		div#theSearchArea2 input{border:0px}
		.tableBorder{ border-bottom: 1px solid black;border-right: 1px solid black; }
		.tableBorder td{border-left:1px solid black;border-top:1px solid black;border-right:0px;border-bottom:0px;text-align: center}
		.tableBorder th{border-left:1px solid black;border-top:1px solid black;border-right:0px;border-bottom:0px;}
		.ryinfo td{border-left: 1px solid silver;border-bottom: 1px solid silver;padding:0px;height:28px;}
		.indicatorText{color:#333399;text-indent:8px;}
		.valueText{text-indent:6px;}
		.textbox{
			height:20px;
			margin:0;
			padding:0 2px;
			box-sizing:content-box;
		}
		table {
				margin: 0 auto;
				border: 1px solid #89BFA7;
				border-collapse: collapse;
				border-spacing: 0;
				empty-cells: show;
			}
			
			td {
				text-align: left;
				border: 1px solid #89BFA7;
				font-size: 9pt;
				padding-left: 2px;
				padding-right: 2px;
				line-height:5mm;
			}
			
			thead td, tfoot td {
				text-align: center;
				background-color:#BFE5D5;
				line-height:3mm;
			}
	</style>
	<script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/util/all_easyui.js"></script></head>
	<script type="text/javascript">
	/*Ext.onReady(function(){
		Ext.select('.tab-buttons-panel').on('click', function(e, t) {
    		Ext.fly(t).radioClass('tab-show');
    		var id = t.id.slice(3);
			Ext.fly('content' + id).radioClass('tab-content-show');
			// 重新刷新iframe
			var $frame = j$("#frame" + id);
			var src = $frame.attr("src");
			$frame.attr("src", src);
	    }, null, {delegate: 'li'});
	});*/
	var cindex="1";
	function switchMenu(index){
		var urls=window.document.getElementById("urls").value.split(",");
		if(cindex==index){
			
		}else {
			window.document.getElementById("content_"+cindex).className="tab-content";
			window.document.getElementById("content_"+index).className="tab-content tab-content-show";
			
			window.document.getElementById("tab_"+cindex).className="";
			window.document.getElementById("tab_"+index).className="tab-show";
			
			
		}
		cindex=index;
		window.document.getElementById("frame_"+index).contentWindow.location.replace(urls[parseInt(index)-1]);
	}
	</script>
	
  </head>
  
  <body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="0">
  	<table border=0 width="99%" height="99%" align="center" cellpadding="0" cellspacing="0" >
		<tr>
			<td align="center" colspan="2" valign="top" height="95%">
				<div class="tab_container" style="text-align:left;" >
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td class="tab-buttons-panel">
								<ul>
								<li id='tab_1'  class='tab-show' ><span onclick="switchMenu('1')" >个人信息</span></li> 
								<li id='tab_2'   ><span onclick="switchMenu('2')" >增改个人信息</span></li> 
								<!-- <li id='tab_3'   ><span onclick="switchMenu('3')" >奖惩信息</span></li> 
								<li id='tab_4'   ><span onclick="switchMenu('4')" >注册信息</span></li>  -->

							</ul>
							</td>
						</tr>
					</table>
					<div id='content_1' class='tab-content tab-content-show' > 
						<iframe id='frame_1' width='99%' height='99%' src='teacher/teachermessage/teachermessage?teacherSno=<%=sno %>&role=1' frameborder='0' scrolling=''></iframe>
						</div>
						<div id='content_2' class='tab-content' > 
						<iframe id='frame_2' width='99%' height='99%' src='' frameborder='0' scrolling=''></iframe>
						</div>
						<div id='content_3' class='tab-content' > 
						<iframe id='frame_3' width='99%' height='99%' src='' frameborder='0' scrolling=''></iframe>
						</div>
						<div id='content_4' class='tab-content' > 
						<iframe id='frame_4' width='99%' height='99%' src='' frameborder='0' scrolling=''></iframe>
						</div>

					<input  type="hidden" id="urls" name="urls" value="teacher/teachermessage/teachermessage?teacherSno=<%=sno %>&role=1,teacher/teachermessage/updateinformation?teacherSno=<%=sno %>,/teacher/stu.xsxj.xjda.jcxx.html?menucode=JW13020105,/teacher/stu.xsxj.zcxx.html?menucode=JW130202" />
				</div>
			</td>
		</tr>
	</table>		
  </body>
</html>

	