<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>登录系统</title>
    
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript" src="js/jslib/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/util/all_easyui.js"></script></head>    
    <script type="text/javascript">
	    window.onload = function (){
			document.getElementById("name").focus();
		};
    	function login(){
			if($("input[name='name']").val() == ""){
				alert("用户名不能为空！");
				$("input[name='name']").focus();
				return;
			}
			if($("input[name='pwd']").val() == ""){
				alert("密码不能为空！");
				$("input[name='pwd']").focus();
				return;
			}
			$.post("login", {'name' : $("input[name='name']").val(),'pwd':$("input[name='pwd']").val()}, function(data) {
				if(data){
					window.location.href="/doubleselect";
				}else{
					alert("用户名或密码不正确！");
				}
					
			}, "json");
		}
    	function keyDown(){
		    if (event.keyCode == 13){
		        event.returnValue=false;
		        event.cancel = true;
		        login();
		    }
		}
    </script>
  </head>
  <body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理平台</span>    
 <!--    <ul>
    	<li><a href="view/pre/jsp/index.jsp">回首页</a></li>
    </ul>   -->  
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
	    <ul>
		    <li style="line-height:48px;font-size:15px">账户:<input name="name" id="name" type="text" class="loginuser" value="1" onclick="JavaScript:this.value=''"/></li>
		    <li style="line-height:48px;font-size:15px">密码:<input name="pwd" id="pwd" type="password" class="loginpwd" value="1" onclick="JavaScript:this.value=''"/></li>
		    <li>
		    	<input name="" type="button" class="loginbtn" value="登录"  onclick="login()"  />
		    	<input type="reset" class="loginbtn" value="重置"/>
		    </li>
	    </ul>
    </div>
    
    </div>
    
    
    
    <div class="loginbm">版权所有  2015  <a href=""></a> </div>
	
    
</body>

</html>
