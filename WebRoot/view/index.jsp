<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>系统首页</title>

<script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
<link rel="stylesheet" href="js/jslib/jquery-easyui-1.3.3/themes/default/easyui.css" type="text/css" id="swicth-style"></link>
<link rel="stylesheet" href="css/index.css" type="text/css"></link>
<link rel="stylesheet" href="js/jslib/jquery-easyui-1.3.3/themes/icon.css" type="text/css"></link>

<script type="text/javascript" src="js/jslib/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/jslib/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="css/green.css" type="text/css"></link>
		<script type="text/javascript">
		
			// 计算数据库服务器与客户端的时间差
			/* var dbtime = parseInt("1460732965000");
			var clienttime = new Date().getTime();
			var difftime = dbtime-clienttime ; */ 

			function showTimes(){
			
				//获取当前日期
				var now = new Date(new Date().getTime());
				
				/* var now = new Date(new Date().getTime() + difftime); */
				//var time = now.getTime();    //获取当前时间(从1970.1.1开始的毫秒数)
					 
			 	//定义星期
				var week;
				switch (now.getDay()){
					case 1: week="星期一"; break;
					case 2: week="星期二"; break;
					case 3: week="星期三"; break;
					case 4: week="星期四"; break;
					case 5: week="星期五"; break;
					case 6: week="星期六"; break;
					default:week="星期天"; break;
				}
			
				var year = now.getFullYear();
			
				//月日时分秒判断,小于10，前面补0
				var month = now.getMonth()+1;
				if(month<10){
					month="0"+month;
				}
			
				var day = now.getDate();
			   	if(day<10){
			  		day="0"+day;
			   	}
			
				var hours =now.getHours();
				if(hours<10){
					hours="0"+hours;
				}
			
				var minutes =now.getMinutes();
				if(minutes<10){
			    	minutes="0"+minutes;
				}
			
				var seconds=now.getSeconds();
			  	if(seconds<10){
			    	seconds="0"+seconds;
				}
			
				//拼接年月日时分秒
				var date_str = year+"年"+month+"月"+day+"日 "+hours+":"+minutes+":"+seconds+" "+week;
			
			 	//显示在id为showtimes的容器里
				document.getElementById("divDate").innerHTML= date_str;
			}
			
			//设置1秒调用一次showTimes函数
			setInterval("showTimes()",1000);
		
		</script>

</head>

<body class="easyui-layout">
	<div region="north" border="true" class="cs-north" split="false">
		<div class="cs-north-bg">
		
		<table width="100%" height="78px" border=0 cellspacing="0" cellpadding="0">
			<tr>
				<td width="290px" nowrap class="bg-banner-left">
					<br>
				</td>
				<td width="25%" class="bg-banner-center-l"><br/></td>
				<td width="500px" nowrap valign="middle" class="bg-banner-center" >	
					<br/>
				</td>
				<td width="20%" class="bg-banner-center-r"><br/></td>
				
				<td width="230px" nowrap align="left" valign="middle"  class="bg-banner-right">
					<span style="font-size:12px; font-weight:normal; color:white" id="divDate" title="教务网络管理系统时间"></span>&nbsp;			          		
					<table border=0 height="40px" cellpadding=0 cellspacing=0 width=230 style="display:none;">		
						<tr>
			          		<td align=right valign=bottom nowrap height="15">
			          		<span style="font-size:9pt;color:white;display: none;" id=divRS>在线人数：<a href="javascript:void(0);" onclick="showOnlineUsers();">4</a></span>&nbsp;
						</td>
						</tr>        
						<tr>
			          		<td align=right valign=bottom nowrap height="15">
						</td>
						</tr>
					</table>		
				</td>
			</tr>
		</table>
			
			<div style="position: absolute; right: 0px; bottom: 0px;">
				<a href="javascript:void(0);" class="easyui-menubutton" 
						data-options="menu:'#layout_north_pfMenu',iconCls:'cog'">更换皮肤</a> 
			    <a href="javascript:void(0);" class="easyui-menubutton" 
			    		data-options="menu:'#layout_north_zxMenu',iconCls:'cog'">控制面板</a>
			</div>
			<div id="layout_north_pfMenu" style="width: 120px; display: none;">
				<div class="li-skinitem" title="default">
					<span class="default" rel="default">默认</span>
				</div>
				<div class="li-skinitem" title="bootstrap">
					<span class="bootstrap" rel="bootstrap">bootstrap</span>
				</div>
				<div class="li-skinitem" title="sunny">
					<span class="sunny" rel="sunny">淡黄主题</span>
				</div>
				<div class="li-skinitem" title="cupertino">
					<span class="cupertino" rel="cupertino">淡蓝主题</span>
				</div>
			</div>
			<div id="layout_north_zxMenu" style="width: 100px; display: none;">
				<div 
				id="editpass">修改密码
				</div>
				<div class="menu-sep"></div>
				<div>锁定窗口</div>
				<div class="menu-sep"></div>
				<div href="javascript:void(0)" onclick="logout()" style="text-decoration:none;">退出系统</div>
			</div>
			
		</div>
	</div>
	<div region="west" border="true" split="true" title="功能导航" class="cs-west">
		<div class="easyui-accordion" fit="true" border="false">
			${menu }	 
		</div>
	</div>
	
	
	<div id="mainPanle" region="center" border="true" border="false">
		 <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
                <div title="导航主页">
					<div class="cs-home-remark">
						<h1>首页面</h1>
					</div>
				</div>
         </div>
	</div>

	<div region="south" border="false" class="cs-south">双选项目</div>
	
	 <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" iconCls="icon-save"  style="width: 300px; height: 170px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td style="font-size:12px">新密码:</td>
                        <td><input id="txtNewPass" type="Password" class="txt01" /></td>
                    </tr>
                    <tr>
                         <td style="font-size:12px">确认密码:</td>
                        <td><input id="txtRePass" type="Password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                    确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
            </div>
        </div>
    </div>
	
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div id="mm-tabcloseall">关闭全部</div>
		<div class="menu-sep"></div>
        <div id="mm-tabcloseright">当前页右侧全部关闭</div>
        <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
	</div>
	
</body>
</html>
