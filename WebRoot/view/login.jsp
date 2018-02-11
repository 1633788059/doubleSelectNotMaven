<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>后台登陆界面</title>
 <link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/util/all_easyui.js"></script>
	<script type="text/javascript" src="js/jiami/md5.js"></script>
    <script type="text/javascript">
	    window.onload = function (){
			document.getElementById("name").focus();
			$(document).keydown(function (e) {
	            if (e.keyCode == 13) {
	                document.getElementById("submit").click(); //调用登录按钮的登录事件
	            }
        	});
        
			  input = $("#pwd");
			  $("#togglePassword").bind("click", function() {
		            "password" == $( input ).attr( "type" ) ?
		                $( input ).attr( "type", "text" ) :
		                $( input ).attr( "type", "password" );
		      });
		};
		function refresh(obj) { //点击刷新验证码
        	obj.src = "code?datetime="+new Date()+Math.random();
    	}
		
    	function login(){
			if($("input[name='name']").val() == ""){
				showMessage("请输入用户名!");
				$("input[name='name']").focus();
				return;
			}
			if($("input[name='pwd']").val() == ""){
				showMessage("请输入密码!");
				$("input[name='pwd']").focus();
				return;
			}
			if($("input[name='code']").val().trim().replace(" ","")==""){
				showMessage("请输入验证码!");
				$("input[name='code']").focus();
				return;
			}
			//判断是否选择下次自动登录
			var remember=$("#remember").is(":checked");
			var username = $("#name").val();
			var password = $("#pwd").val();
			var randnumber = $("#randnumber").val();
			password = hex_md5(hex_md5(password)+hex_md5(randnumber.toLowerCase()));
			$.post("login", {'name' : $("input[name='name']").val(),'pwd':password,'code':$("input[name='code']").val().trim().replace(" ",""),'remember':remember}, function(data) {
				if(data.success){
					showMessage(data.msg);
					//window.location.href="/doubleselect";
					setTimeout("window.location.href='/doubleselect';",1500);
				}else{
					showMessage(data.msg);
					$("#img").attr("src","code?datetime="+new Date());
					$("input[name='name']").val("");
					$("input[name='pwd']").val("");
					$("input[name='code']").val("");
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
		function showMessage(message){
				$("#msg").html(message);
				setTimeout("$('#msg').html('');",15000);
			}
    </script>
<link rel="stylesheet" href="css/stylelogin.css" />

<body>

<div class="login-container">
	<h1>后台登陆界面</h1>
	
	<div class="connect">
		<p>www.wiseph.com</p>
	</div>
	
	<form action="" method="post" id="loginForm">
		<div>
			<input id="name" type="text" name="name" class="username" placeholder="用户名" autocomplete="off"/>
		</div>
		<div>
			<input id="pwd" type="password" name="pwd" class="password" placeholder="密码"  />
		</div>
		
		<div style="height:69px;display:block;float:left">
			<div style="width:220px;float:left;display:block;"> 
			<input  name="code" type="text" class="username" placeholder="验证码" style="width:150px;margin-left:0px;" id="randnumber"></div>
			<div style="float:right;display:block;">
				<img style="height:40px;width:80px;line-height:30px;margin-top:25px" alt="点击更换" title="点击更换" src="code" id="img" onclick="javascript:refresh(this);">
			</div>
		</div>
		
		<div style="font-size:15px;display:block;line-height:30px;height:95px">
			<input style="width:13px;height:13px;margin:0 3px 0 10px;vertical-align:middle;" type="checkbox" id="remember"><label for="remember">记住我</label> <input  style="width:13px;height:13px;margin:0 3px 0 10px;vertical-align:middle;" type="checkbox" id="togglePassword"><label for="togglePassword">显示密码</label>
		</div>
		<div style="display:block;color:red;align:center;line-height:30px;height:30px" id="msg">
		</div>
		<button id="submit" type="button" onclick="login()">登 陆</button>
	</form>
	<!-- <a href="/register.html">
		<button type="button" class="register-tis">还有没有账号？</button>
	</a>  -->

</div>

<script src="js/loginpage/jquery.min.js"></script>
<script src="js/loginpage/common.js"></script>
<!--背景图片自动更换-->
<script src="js/loginpage/supersized.3.2.7.min.js"></script>
<script src="js/loginpage/supersized-init.js"></script>
<!--表单验证-->
<script src="js/loginpage/jquery.validate.min.js?var1.14.0"></script>

</body>
</html>