<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>首页</title>

<link type="text/css" rel="stylesheet" href="view/pre/css/index/public.css">
<link type="text/css" rel="Stylesheet" href="view/pre/css/index/yiguo.css">
<link type="text/css" rel="Stylesheet" href="view/pre/css/index/default.css">
<script type="text/javascript" src="view/pre/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="view/pre/js/Common.js"></script>
<script type="text/javascript" src="view/pre/js/kv.js"></script>
<script type="text/javascript" src="view/pre/js/jquery.superslide.2.1.1.js"></script>
<script type="text/javascript" src="view/pre/js/default.js"></script>

<script type="text/javaScript">
	$(document).ready(function(){
		//显示上部分图片
		$.ajax({
			type:"get" ,
			url:"kehu/wupin/getWupins",
			dataType:"json" ,
			data:{location:"上",limit:6} ,
			success:function(data){
				if(data.success==true){
					var str = "" ;
					$.each(data.obj, function(i, item) {
						str+=
						"<a href='view/pre/jsp/detaial.jsp?"+item.id+"' target='_blank' style=''>"+ 
						"<img src='"+item.imgPath+"' border='0' width='1110' height='310'/>"+
						"</a>" ;
						
						$("#imgDesc"+i).html("<b>·</b>"+item.wupinDescribe) ;
					});
					$(str).appendTo("#wupinsShang");
				}
			}
		}) ;
	
		//显示中间部分图片
		$.ajax({
			type:"get" ,
			url:"kehu/wupin/getWupins" ,
			dataType:"json" ,
			data:{location:"中",limit:6} ,
			success:function(data){
				if(data.success==true){
					var str = "<ul id='ad-col'>" ;
					$.each(data.obj, function(i, item) {
						str+=
						"<li>"+
						"<a href='view/pre/jsp/detaial.jsp?"+item.id+"' target='_blank'>"+
						"<img src='"+item.imgPath+"' border='0' width='180' height='180'/>"+
						"</a>"+
						"<a href='view/pre/jsp/detaial.jsp?"+item.id+"' target='_blank'>"+item.wupinmc+item.num+"个</a>&nbsp;&nbsp;"+
						"<font color='#CF5926' size='3' face='黑体'><b>￥"+item.wupinPrice+"</b></font><br>"+
						"<a href='javascript:addGouwuche("+item.id+");'><img src='view/pre/images/index/gouwuche.jpg'/></a>"+
						"<a href='view/pre/jsp/dingdan.jsp?"+item.id+"' target='_blank'><img src='view/pre/images/index/goumai.jpg'/></a>"+
						"</li>";
					});
					str+="<ul>" ;
					$(str).appendTo("#wupinsZhong");
				}
			}
		}) ;
		
		//显示下半部分图片
		$.ajax({
			type:"get" ,
			url:"kehu/wupin/getWupins" ,
			dataType:"json" ,
			data:{location:"下",limit:12} ,
			success:function(data){
				if(data.success==true){
					var str = "<ul>" ;
					$.each(data.obj, function(i, item) {
						str+=
						"<li><div class='product' style='height:250px;width:190px;margin-top:0px;'>"+
						"<div class='p-img'><a href='view/pre/jsp/detaial.jsp?"+item.id+"' target='_blank'><img width='160' height='160' alt='' src='"+item.imgPath+"'/></a></div>"+
						"<div class='p-name'><a target='_blank' href='view/pre/jsp/detaial.jsp?"+item.id+"'>"+item.wupinmc+item.num+"个</a>&nbsp;&nbsp;"+
						"<font color='#CF5926' size='3' face='黑体'>￥<b>"+item.wupinPrice+"</b></font>"+
						"</div>"+
						"<div class='p-price'>"+
						"<a href='javascript:addGouwuche("+item.id+");'><img src='view/pre/images/index/gouwuche.jpg'/></a>"+
						"<a href='view/pre/jsp/dingdan.jsp?"+item.id+"' target='_blank'><img src='view/pre/images/index/goumai.jpg'/></a>"+
						"</div>"+
						"</div></li>" ;
					});
					str+="<ul>" ;
					$(str).appendTo("#wupinsXia");
				}
			}
		}) ;
		
		//检查是否存在登录用户
		$.ajax({
			type:"get" ,
			url:"kehu/kehuxx/getCurrentUser" ,
			dataType:"json" ,
			success:function(data){
				if(data.success==true){
					$("<a href='view/pre/jsp/user.jsp'>"+data.obj.username+"</a>&nbsp; | &nbsp;<a href='javascript:logout();'>退出</a>").appendTo("#currentUser") ;
					$("#login").hide();
					$("#register").hide();
				}else{
					$("#login").show();
					$("#register").show();
				}
			}
		}) ;
		
		//加载购物车
		getGouwuche() ;
		
	}) ;
	
	//用户退出
	function logout(){
		if(confirm("确定退出吗？")==true){
			$.ajax({
				type:"post" ,
				url:"kehu/kehuxx/logout" ,
				dataType:"json" ,
				success:function(data){
					if(data.success==true){
						window.location.reload() ; //刷新当前页面.  
					}
				}
			}) ;
		}
	}
	
	//加载购物车
	function getGouwuche(){
		$.ajax({
			type:"get" ,
			url:"kehu/kehuxx/getCurrentUser" ,
			dataType:"json" ,
			success:function(data){
				if(data.success==true){
					$.ajax({
						type:"get" ,
						url:"kehu/wupin/getGouwuche" ,
						dataType:"json" ,
						success:function(data){
							//计算总金额
							var money = 0 ;
							var wupin = "" ;
							var str = "" ;
							var num=0;
							if(data.obj!=null&&data.obj.length>0){
								$.each(data.obj,function(i,item){
												money+=item.gouwucheJiage;
												num+=item.gouwucheNum;
												wupin+="<li><span><img width='40px' height='40px' src='"+item.imgPath+"'/></span>&nbsp;&nbsp;&nbsp;"+
													   "<span>"+item.wupinmc+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
													   "<span>"+item.wupinDescribe+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
													   "<span>"+item.gouwucheNum+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
													   "<span><font color='#CF5926' size='3' face='黑体'><b>￥"+item.gouwucheJiage+"</b></font></span>&nbsp;&nbsp;"+
													   "<span style='float:right;margin-top:25px;'><a href='javascript:delateGouwuche("+item.twupin_id+")'><font color='red'>删除</font></a></span>"+
													   "</li>" ;
											}) ;
											
											str+="<p class='total'>目前选购商品共<b>"+data.obj.length+"</b>件,数量为"+num+"个</p>"+
												"<ul>"+wupin+"</ul>"+
					                            "<p class='price-total'>"+
					                            "<a href='view/pre/jsp/dingdan.jsp' target='_blank' class='settleup'><img src='view/pre/images/index/jiesuan.jpg'/></a> 共<b>"+data.obj.length+"</b>件商品"+
					                            "&nbsp;&nbsp;&nbsp;&nbsp;总计<b>"+money+"</b>元</p>" ;
							}else{
								/* str+="<p class='total'>目前选购商品共<b>0</b>件</p>"+
							        "<p class='price-total'>"+
							        "<a href='view/pre/jsp/dingdan.jsp' target='_blank' class='settleup'><img src='view/pre/images/index/jiesuan.jpg'/></a>"+
							        "共<b>0</b>件商品&nbsp;&nbsp;&nbsp;&nbsp;总计<b>0</b>元</p> " ; */
								str+="<p class='total' style='font-size:20px;color:red;text-align:center'>购物车为空</p>";
							}
							$("#gouwuche").html(str) ;
						}
				}) ;
			}
		/* 	else{
				alert("请先登录");
			} */
		}
		});
	}
	
	//添加购物车
	function addGouwuche(id){
		$.ajax({
			type:"get" ,
			url:"kehu/kehuxx/getCurrentUser" ,
			dataType:"json" ,
			success:function(data){
				if(data.success==true){
					$.ajax({
						type:"post" ,
						url:"kehu/wupin/addGouwuche" ,
						dataType:"json" ,
						data:{id:id} ,
						success:function(da){
							if(da.success==true){
								getGouwuche() ;
								alert("成功添加到购物车！") ;
								
								/* //计算总金额
								var money = 0 ;
								var num=0;
								var wupin = "" ;
								var str="";
								$.each(da.obj,function(i,item){
									money+=item.gouwucheJiage;
									num+=item.gouwucheNum();
									wupin+="<li><span><img width='40px' height='40px' src='"+item.imgPath+"'/></span>&nbsp;&nbsp;&nbsp;"+
										   "<span>"+item.wupinmc+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
										   "<span>"+item.wupinDescribe+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
										   "<span>"+item.gouwucheNum+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
										   "<span><font color='#CF5926' size='3' face='黑体'><b>￥"+item.gouwucheJiage+"</b></font></span>&nbsp;&nbsp;"+
										   "<span style='float:right;margin-top:25px;'><a href='javascript:delateGouwuche("+item.id+")'><font color='red'>删除</font></a></span>"+
										   "</li>" ;
								}) ;
								
								str+="<p class='total'>目前选购商品共<b>"+da.obj.length+"</b>件,数量为"+num+"个</p>"+
									"<ul>"+wupin+"</ul>"+
		                            "<p class='price-total'>"+
		                            "<a href='view/pre/jsp/dingdan.jsp' target='_blank' class='settleup'><img src='view/pre/images/index/jiesuan.jpg'/></a> 共<b>"+da.obj.length+"</b>件商品"+
		                            "&nbsp;&nbsp;&nbsp;&nbsp;总计<b>"+money+"</b>元</p>" ;
		                         	               
		                        $("#gouwuche").html(str) ; */
							}
						}
					}) ;
				}else{
					alert("请先登陆！") ;
				}
			}
		}) ;
	}
	
	//从购物车里面移除啊物品
	function delateGouwuche(id){
		$.ajax({
			type:"post" ,
			url:"kehu/wupin/deleteGouwuche" ,
			dataType:"json" ,
			data:{id:id} ,
			success:function(data){
				if(data.success==true){
				
					alert(data.msg) ;
					//重新加载购物车
					getGouwuche() ;
				}
			}
		}) ;
	}
	
</script>

</head>

<body>
	<div class="body2">
		<div class="nav" style="position: relative;">
			<div class="wrap">
				<div class="logo" id="currentUser" style="margin-top:10px;">
				</div>
			</div>
		
			<div class="wrap">
				<div class="nav-right">
					<dl class="loginbar" style="" id="login">
						<dt class="">
							<a href="view/pre/jsp/login.jsp"> 登录 <em class="arrow-up"></em>
							</a>
						</dt>
					</dl>

					<div class="reg-quit" style="" id="register">
						<a rel="nofollow" href="view/pre/jsp/register.jsp" target="_blank">注 册</a>
					</div>

					<dl class="cartbar" >
		                <dt><a href="view/pre/jsp/dingdan.jsp" target='_blank'><span style="width:50px;"><img height="40px" src="view/pre/images/index/gouwuchebg.jpg"/></span></a></dt>
		                <dd>
		                    <div class="cartbox" style="width:350px">
		                        <div class="cartbox-list" id="gouwuche" style="width:350px">
		                            
		                        </div>
		                    </div>
		                </dd>
		            </dl>
	
				</div>
			</div>
		</div>


		<div class="containter" id="main">
			<div class="wrap" id="wrapdiv">
				<div class="kv">
					<div class="picshow" id="wupinsShang"></div>
					<ul class="curtxt">
						<li class="on" id="imgDesc0"></li>
						<li class="" id="imgDesc1"></li>
						<li class="" id="imgDesc2"></li>
						<li class="" id="imgDesc3"></li>
						<li class="" id="imgDesc4"></li>
						<li class="" id="imgDesc5"></li>
					</ul>
				</div>

				<div class="list">
					<div id="wupinsZhong"></div>
					
					<div class="title">
						<h2>
							<a href="#">进口水果</a>
						</h2>
					</div>
					
					<div class="homefloor" style="height:500px;">
						<div class="right" id="wupinsXia" style="height:600px;"></div>
					</div>

				</div>
			</div>

		</div>

	</div>
</body>
</html>
