<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆</title>
    
	<script type="text/javascript" src="view/pre/js/dc.js"></script>
	<script src="view/pre/js/jquery.js"></script>

    <link href="view/pre/css/login/public.css" rel="stylesheet">
    <link href="view/pre/css/login/themes.css" rel="stylesheet">

    <script src="view/pre/js/tab.js"></script>
    <script src="view/pre/js/FormValidate.js"></script>
    <script type="text/javascript">
        
    </script>
	
  </head>
  
  <body id="body">
    <!------- 顶部工具栏开始 ------->
    <div class="w">
        

        <!------- 顶部工具栏结束 ------->
        <link href="view/pre/css/login/login.css" rel="stylesheet">


<div class="header clearfix">
    <div class="wrap">
        <div class="logo">
            <a href="" target="_blank"></a>
            <span class="h-tit">用户登录</span>
        </div>
    </div>
</div>

<script type="text/javascript">
	function login(){
		var username = $("#UserName").val().trim() ;
		var password = $("#Pwd").val().trim() ;
		
		if(username==""||password==""){
			alert("用户名或密码不能为空") ;
		}else{
			$.ajax({
				url:"kehu/kehuxx/login" ,
				type:"post" ,
				dataType:"json" ,
				data:{username:username,password:password,} ,
				success:function(data){
					if(data.success==true){
						window.location.href="view/pre/jsp/index.jsp" ;
					}else{
						alert(data.msg) ;
					}
				}
			}) ;
		}
	}

    $(function () {
    	//给整个页面设置enter事件
        $(document).keydown(function (e) {
            if (e.keyCode == 13) {
                document.getElementById("btnLogin").click(); //调用登录按钮的登录事件
            }
        });
    });
</script>

<form action="" method="post">    <div class="login">
        <div class="wrap clearfix">
            <div class="fl"><img width="666" height="418" src="view/pre/images/login/8,daf1d248-d833-4083-8c29-81288e8a302b.gif"></div>
            <div class="fr">
                <p class="right"></p>
                <div class="login-main">
                    <p><strong>欢迎登录</strong><span>还没有账号？<a class="green" href="view/pre/jsp/register.jsp">立即注册</a></span></p>

                        <div id="msg-wrap"><div> </div></div>

                    <p>
                        <span class="input-phone-icon">&nbsp;</span><input class="input input-phone" id="UserName" name="UserName" type="text" value="">
                    </p>
                    <p><span class="input-key-icon">&nbsp;</span><input class="input input-key" id="Pwd" name="Pwd" placeholder="密码" type="password"></p>
                    <p><a id="btnLogin" href="javascript:void(0)" class="btn-green-l" onclick="login();">登 &nbsp; 录</a></p>
                   <!--  <p><input id="btnLogin" type="submit" class="btn-green-l" value="登 &nbsp; 录" /> </p> -->
                </div>
            </div>
        </div>
    </div>
</form>

        <!------- 页尾开始 ------->
        <div class="footer">
            <div class="wrap">
                <div class="copyright">
                    所有图片均受著作权保护，未经许可任何单位与个人不得使用、复制、转载、摘编，违者必究法律责任。<br>
                    版权所有<a href="#"></a>
                    2005-2015 Copyright @ 2005-2015 沪ICP备09008015号<br><br>
                </div>
            </div>
        </div>
        <!------- 页尾结束 ------->

    </div>

<script type="text/javascript">


    var _adwq = _adwq || [];
    // if (getCookie('dsp') != null) {

    _adwq.push(['_setAccount', 'l7svx']);
    _adwq.push(['_setDomainName', '.yiguo.com']);
    _adwq.push(['_trackPageview']);
    //  }
</script>
<script type="text/javascript" src="view/pre/js/adw.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-20131478-1']);
        _gaq.push(['_setDomainName', 'yiguo.com']);
        _gaq.push(['_setSiteSpeedSampleRate', 50]);
        _gaq.push(['_addOrganic', 'baidu', 'word']);
        _gaq.push(['_addOrganic', 'soso', 'w']);
        _gaq.push(['_addOrganic', 'youdao', 'q']);
        _gaq.push(['_addOrganic', 'so.360.cn', 'q']);
        _gaq.push(['_addOrganic', 'sogou', 'query']);
        _gaq.push(['_setCampNameKey', 'n']);
        _gaq.push(['_setCampSourceKey', 's']);
        _gaq.push(['_setCampMediumKey', 'm']);
        _gaq.push(['_setCampTermKey', 'k']);
        _gaq.push(['_setCampContentKey', 'c']);
        _gaq.push(['_trackPageview']);

        (function () {
            var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
        })();
    });
</script>


</body>
</html>
