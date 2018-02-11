<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
	<script type="text/javascript" src="view/pre/js/dc.js"></script>
	<script src="view/pre/js/jquery.js"></script>

    <link href="view/pre/css/login/public.css" rel="stylesheet">
    <link href="view/pre/css/login/themes.css" rel="stylesheet">

    <script src="view/pre/js/tab.js"></script>
    <script src="view/pre/js/FormValidate.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $.ajax({
            	url:"kehu/kehuh/searchs" ,
            	type:"get" ,
            	dataType:"json" ,
            	success:function(data){
            		if(data.success==true){
            			var str = "<SELECT id='kaihuh_id' NAME='kaihuh_id'>" ;
            			$.each(data.obj, function(i, item) {
            				str+="<OPTION VALUE="+item.id+">"+item.name ;
						});
						str+="</select>" ;
            			$(str).appendTo("#select") ;
            		}
            	}
            }) ;
        });
   		
   		function register(){                            //用户注册
   			var username = $("#username").val().trim() ;
   			var password = $("#password").val().trim() ;
   			var repassword = $("#repassword").val().trim() ;
   			var shenfenz = $("#shenfenz").val().trim() ;
   			var xingming = $("#xingming").val().trim() ;
   			var xingbie = $("input[name='SEX']:checked").val() ; 
   			var kaihuh_id = $("#kaihuh_id").val() ;
   			var tel = $("#tel").val().trim() ;
   			
   			if(username==""||password==""||repassword==""||shenfenz==""||xingming==""||tel==""){
   				alert("用户信息不能为空！") ;
   			}else if(password!=repassword){
   				alert("密码与确认密码不一致！") ;
   			}else{
   				$.ajax({
   					url:"kehu/kehuxx/register" ,
   					type:"post",
   					dataType:"json" ,
   					data:{username:username,password:password,shenfenz:shenfenz,xingming:xingming,
   						xingbie:xingbie,kaihuh_id:kaihuh_id,tel:tel,
   					} ,
   					success:function(data){
   						alert(data.msg) ;
   					}
   				}) ;
   			}
   		}
    </script>
  </head>
  
  <body id="body">
    <!------- 顶部工具栏开始 ------->
    <div class="w">
        

        <!------- 顶部工具栏结束 ------->
        <script src="view/pre/js/Reg.js"></script>

<link href="view/pre/css/register/register.css" rel="stylesheet">


<div class="header clearfix">
    <div class="wrap">
        <div class="logo">
            <a href="" target="_blank"></a>
            <a href="" target="_blank" class="l2 goTop"></a>
            <span class="h-tit">用户注册</span>
        </div>
    </div>
</div>
<form action="" method="post">    <div class="register">
        <div class="wrap">
            <div class="tabbable">
                <!-- tabs -->
                <ul class="nav-tabs">
                    <li id="tab2li" class=""><a href="#" data-toggle="tab">填写用户信息</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane clearfix active" id="tab1">
                        <div class="fl">
                            <p>
                                <span class="field-name">用户姓名</span>
                                <input class="input input-phone" id="username" name="username" placeholder="请输入您的用户姓名" type="text" value="">
                                <span></span>
                            </p>
                            <p>
                                <span class="field-name">设置密码</span>
                                <input class="input input-key" id="password" name="password" placeholder="6-15位字母、符号或数字" type="password">
                                <span></span>
                            </p>
                            <p>
                                <span class="field-name">确认密码</span>
                                <input class="input input-key" id="repassword" name="repassword" placeholder="再次输入密码" type="password">
                                <span></span>
                            </p>
                            <p>
                                <span class="field-name">身份证号</span>
                                <input class="input input-phone" id="shenfenz" name="shenfenz" placeholder="请输入您的身份证号" type="text" value="">
                                <span></span>
                            </p>
                            <p>
                                <span class="field-name">真实姓名</span>
                                <input class="input input-phone" id="xingming" name="xingming" placeholder="请输入您的真实姓名" type="text" value="">
                                <span></span>
                            </p>
                             <p>
                                <span class="field-name">电话号码</span>
                                <input class="input input-phone" id="tel" name="tel" placeholder="请输入您的电话号码" type="text" value="">
                                <span></span>
                            </p>
                            <p>
                                <span class="field-name">性别</span>
								男 <INPUT TYPE="RADIO" NAME="SEX" checked VALUE="男"/>
								女 <INPUT TYPE="RADIO" NAME="SEX" VALUE="女"/>                              
                                
                                
                                <span class="field-name" style="border:1px;width:88px;">开户银行</span>
                                <span id="select"></span>
                            </p>
                            <p>
                                
                            </p>
                            <span></span>
                            <p><span class="field-name"></span><a href="#" class="btn-green-l" id="PhoneReg" onclick="register();">立即注册</a></p>
                        </div>
                        <div class="fr">
                            
                        </div>
                    </div>
                </div>
                <div class="right">
                    我已经注册，现在就 <a class="orange" href="view/pre/jsp/login.jsp">登录</a>
                    
                </div>
            </div>
        </div>
    </div>
<input id="HdAction" name="HdAction" type="hidden" value=""><input value="1813cb42-c692-4c09-8b29-6646b16d1a72" id="UserID" name="UserID" type="hidden"></form>


<script type="text/javascript">
    Reg.ErrorString = {
        NoPassword: '密码不能为空',
        PasswordMinLength: '密码不能小于6位',
        InconPassword: '两次密码不一致',
        NoEmail: '邮箱不能为空',
        EmailPatternWrong: '邮箱地址格式不正确',
        EmailAlreadyExists: '邮箱已经存在',
        NoMobile: '手机号不能为空',
        MobilePatternWrong: '手机号格式不正确',
        MobileAlExists: '手机号已经存在',
        NoVerifyCode: '验证码不能为空',
        VerifyCodePatternWrong: '请正确输入验证码',
        SendSMSFail: '短信发送失败',
        AgreementWrong: '请接受服务协议',
        SendVerifyCode: '获取验证码',
        VerifyCodeRemainTime: '还剩下',
        VerifyCodeSecond: '秒'
    }
</script>

        <!------- 页尾开始 ------->
        <div class="footer">
            <div class="wrap">
                
                <div class="copyright">
                    所有图片均受著作权保护，未经许可任何单位与个人不得使用、复制、转载、摘编，违者必究法律责任。<br>
                    版权所有<a href="#"></a>
                    2005-2015 Copyright @ 2005-2015 All rights reserved 沪ICP备09008015号<br><br>
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


</body></html>
