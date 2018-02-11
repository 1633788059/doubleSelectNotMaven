<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>商品详情</title>

<link type="text/css" rel="stylesheet" href="view/pre/css/index/public.css">
<link type="text/css" rel="Stylesheet" href="view/pre/css/index/yiguo.css">
<link type="text/css" rel="Stylesheet" href="view/pre/css/index/default.css">
<link type="text/css" rel="stylesheet" href="view/pre/css/detaial/public.css">
<link type="text/css" rel="stylesheet" href="view/pre/css/detaial/yiguo.css">
<link type="text/css" rel="stylesheet" href="view/pre/css/detaial/main_1280.css">
<link href="view/pre/css/detaial/favour.css" rel="stylesheet" type="text/css">
<link type="text/css" rel="stylesheet" href="view/pre/css/detaial/menu.css">
<script type="text/javascript" src="view/pre/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="view/pre/js/Common.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var url=location.search ;
		if(url.indexOf("?")!=-1){
			var str = url.substr(1).split("&")[0];           //物品id
			var dingdanid = url.substr(1).split("&")[1];     //订单id

			$.ajax({
				type:"get" ,
				url:"kehu/wupin/getWupin" ,
				dataType:"json" ,
				data:{id:str} ,
				success:function(data){
					if(data.success==true){
						$("#wupinid").html("<input type='text' id='id' value='"+data.obj.id+"'/>") ;
						$("<span>"+data.obj.wupinmc+"</span>").appendTo("#wupinmc") ;
						$("<img width='470' height='470' src='"+data.obj.imgPath+"' style='display: inline;'/> ").appendTo("#imgdiv2") ;
						$("<h1>"+data.obj.wupinmc+"</h1>").appendTo("#wupinmane") ;
						$("<span class='cxj'>￥"+data.obj.wupinPrice+"</span>").appendTo("#price") ;
						$("<span class='cxj'>"+data.obj.wupinweight+"&nbsp;&nbsp"+data.obj.wupinunit+"</span>").appendTo("#unit") ;
						$("#num").html(data.obj.num) ;
						$("#wupinbh").html(data.obj.wupinbh) ;
						
						$("#wupin_id").val(data.obj.id) ;
						$("#dingdan_id").val(dingdanid) ;
					
						if(dingdanid!=undefined){
							$("#allPingjia").css("display", "block");  
						}
					}
				}
			}) ;
		} 
		
		//根据物品id获取所有评价
		$.ajax({
			type:"get" ,
			url:"kehu/wupin/getPingjiaByWupinId" ,
			dataType:"json" ,
			data:{id:str} ,
			success:function(data){
				if(data.success){
					var str="<table border='0'>" ;
					$.each(data.obj,function(i,item){
						var star = "" ;
						for(var j=0;j<item.mycd+1;j++){
							star+="<img src='view/pre/images/detaial/star.jpg'>" ;
						}
						str+="<tr>"+
							 "<td width='100px;' style='background:#F7F7F7'>用户："+item.username+"</td>" +
							 "<td style='background:#FFFBF4;width:1000px;padding-left:30px;'>"+item.wppj+"</td>"+
							 "</tr>"+
							 "<tr>"+
							 "<td style='background:#F7F7F7'>"+star+"</td>"+
							 "<td style='font-size:12px;background:#FFFBF4;padding-left:30px;'>"+item.wppjdatetime+"</td>"+
							 "</tr>"+
							 "<tr style='height:15px'>"+
							 "<td colspan='3'></td>"+
							 "</tr>" ;
					}) ;
					str+="</table>" ;
					
					$(str).appendTo("#allUserPingjia") ;
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
					$("#currentUser").html("<a href='view/pre/jsp/user.jsp'>"+data.obj.username+"</a>&nbsp;|&nbsp;<a href='javascript:logout()'>退出</a>") ;
					$("#login").hide();
				}else{
					$("login").show();
				}
			}
		}) ;
		getGouwuche();
	}) ;
	
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
	
	//购买物品
	function bought(){
		//检查是否存在登录用户
		$.ajax({
			type:"get" ,
			url:"kehu/kehuxx/getCurrentUser" ,
			dataType:"json" ,
			success:function(data){
				if(data.success==true){
					var wupinid = $("#id").val() ;
					$.ajax({
						type:"post" ,
						url:"kehu/wupin/addGouwuche" ,
						dataType:"json" ,
						data:{id:wupinid} ,
						success:function(da){
							if(da.success==true){
								alert("成功添加到购物车！") ;
								getGouwuche();
							}
						}
					});
				}else{
					alert("您尚未登录！") ;
				}
			}
		}) ;	
	}
	
	//物品评价
	function pingjia(){
		var wupinid = $("#wupin_id").val() ;
		var dingdan_id = $("#dingdan_id").val() ;
		var mycd = $("input[name='mycd']:checked").val();
		var pingjia = $("#pingjia").val() ;
		
		$.ajax({
			type:"get" ,
			url:"kehu/wupin/checkUserDingdan" ,
			dataType:"json" ,
			data:{wupinid:wupinid} ,
			success:function(data){
				if(data.success==true){		
					$.ajax({
						type:"post" ,
						url:"kehu/wupin/pingjia" ,
						dataType:"json" ,
						data:{dingdan_id:dingdan_id,mycd:mycd,pingjia:pingjia,wupin_id:wupinid} ,
						success:function(da){
							alert(da.msg) ;
							if(da.success){
								window.location.reload() ;
								getGouwuche();
							}
						}
					}) ;
				}else{
					alert(data.msg) ;
				}
			}
		}) ;
	}
</script>

</head>
<body>
	<div style="margin:0 auto ;width:1130px;">
		<span id="currentUser"></span>	
		
		<span style="float:right;" id="login">
			<a rel="nofollow" href="view/pre/jsp/login.jsp" target="_blank">登录 </a> &nbsp;|&nbsp; 
			<a rel="nofollow" href="view/pre/jsp/register.jsp" target="_balnk">注 册</a>
		</span>
		<dl class="cartbar" style="float:right">
		   <span style="width:50px;"><a href="view/pre/jsp/dingdan.jsp" target='_blank'><img height="40px" src="view/pre/images/index/gouwuchebg.jpg"/></a></span>
		    <dd>
			    <div class="cartbox" style="width:350px">
				    <div class="cartbox-list" id="gouwuche" style="width:350px">
				                            
				    </div>
			    </div>
		    </dd>
		  </dl>
	</div>

	<div class="containter" id="main">
		<div class="wrap" id="wrapdiv">
			<div class="breadCrumb" id="wupinmc">
				<a href="view/pre/jsp/index.jsp">首页</a><span>></span>
			</div>
			<div class="main">
				<div class="main_left">
					<div class="cpic">
						<div class="prod_js" id="imgdiv2">
						</div>
					</div>
				</div>
				<div class="main_right">
					<div class="cxxx"></div>
					
					<div class="cpname" id="wupinmane">	
					</div>
					
					<div class="tabs">
						<ul>
							<li class="v">
								<a href="#">详情</a>
							</li>
						</ul>
					</div>
					
					<div class="tabs_info">
						<div id="price">
							<span>价格：</span>
						</div>
						
						<div class="tabs_t2">
							<table width="100%" cellspacing="0" cellpadding="0" border="0">
								<tbody>
									<tr>
										<td hidden id="wupinid"></td>
										
										<td><span>数量</span></td>
										
										<td>
											<input class="shuzik" name="product_amount" id="product_amount" type="text" style="height:20px;weight:40px" value="1">
										</td>
										
										<td align="right">
											<a class="addCart" href="javascript:bought();"></a>
										</td>
									</tr>			
								</tbody>
							</table>
						</div>
						<div id="unit">
							<span>单位:</span>
						</div>
					</div>

					<table width="100%" cellspacing="0" cellpadding="0" border="0"
						class="zx">
						<tbody>
							<tr>
								<td width="5%">库 存:</td>
								<td width="21%" id="num"></td>
							</tr>
							<tr>
								<td>商品编号:</td>
								<td id="wupinbh"></td>
							</tr>

							<tr>
								<td colspan="6" class="service_7day">
									7天无理由退换货
								</td>
							</tr>

						</tbody>
					</table>

				</div>
			</div>

			<div class="wrap-info">
				<div class="content1">
					<ul class="cont-tabs">
						<li class="on">用户评价</li>
					</ul>
					
					<div class="content-box">
						<div class="details" style="display:none;" id="allPingjia">
							<p>
								<b>星级评价：</b>
								<b>
									<input type="radio" value="0" name="mycd"/>1星
									<input type="radio" value="1" name="mycd"/>2星
									<input type="radio" value="2" name="mycd"/>3星
									<input type="radio" value="3" name="mycd"/>4星
									<input type="radio" value="4" name="mycd" checked/>5星
								</b> 
								<br>
							</p>
							<p>
								<b><textarea id="pingjia" cols="125" rows="2"></textarea></b> 
								<b>
									<input type="text" value="" id="wupin_id" hidden/>
									<input type="text" value="" id="dingdan_id" hidden/>
									<a href="javascript:pingjia()">
										<img src="view/pre/images/detaial/pingjia.jpg"/>
									</a>	
								</b>
								<br>
							</p>
						</div>
						
						<div class="details" id="allUserPingjia"></div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</body>