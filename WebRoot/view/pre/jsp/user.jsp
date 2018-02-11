<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户信息管理</title>
    <link href="view/pre/css/dingdan/dingdan.css" rel="stylesheet"/>
    <script type="text/javascript" src="view/pre/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		//获取用户所有地址
    		$.ajax({
    			type:"get" ,
    			url:"kehu/kehuxx/getKehuDiZhi" ,
    			dataType:"json" ,
    			success:function(data){
    				if(data.success==true){
    					var str = "<table><thead style='background-color:#F4DECF;'>" ;
    					$.each(data.obj,function(i,item){
    						str+="<tr>"+
								 "<td>"+(i+1)+"</td>"+
								 "<td>"+item.kehudz+"</td>"+
								 "</tr>" ;
    					}) ;
    					str+="</thread></table>" ;
    					$("#allAddresses").html(str) ;
    				}
    			}
    		}) ;
    		
    		//获取用户所有订单
    		$.ajax({
    			type:"get" ,
    			url:"kehu/wupin/getAllDingdan" ,
    			dataType:"json" ,
    			success:function(data){
    				if(data.success==true){
    					var str = "<table><thead style='background-color:#F4DECF;'>" ;
    						str+="<tr style='font-size:12px;text-align:center;'>"+
    							 "<td></td>"+
    							 "<td>订单编号</td>"+
    							 "<td>订单状态</td>"+
    							 "<td>物品图片</td>"+
    							 "<td>物品名称</td>"+
    							 "<td>物品数量</td>"+
    							 "<td>物品单价</td>"+
    							 "<td>总价格</td>"+
    							 "<td>期望送达时间</td>"+
    							 "<td>下单时间</td>"+
    							 "<td>收货人姓名</td>"+
    							 "<td>收货人地址</td>"+
    							 "<td>联系电话</td>"+
    							 "<td>操作</td>"+
    							 "</tr>" ;
    							 
						$.each(data.obj,function(i,item){
							var zhuangtai = "" ;                 //订单状态
							var operate = "" ;                   //操作
							
	    					if(item.ddzhuangtai==0){
	    						zhuangtai = "未发送" ;
	    						operate = "<input type='button' value='等待发货'/>" ;
	    					}else if(item.ddzhuangtai==1){
	    						zhuangtai = "已发送" ;
	    						operate = "<input type='button' onclick='souhuo("+item.id+")' value='确认收货'/>" ;
	    					}else if(item.ddzhuangtai==2){
	    						zhuangtai = "已接受" ;
	    						operate = "<a href='view/pre/jsp/detaial.jsp?"+item.wupin_id+"&"+item.id+"' target='_blank'><button>商品评价</button></a>" ;
	    					}
							
							str+="<tr style='font-size:12px;'>"+
								 "<td hidden>"+item.id+"</td>"+
								 "<td>"+(i+1)+"</td>"+
								 "<td>"+item.ddname+"</td>"+
								 "<td>"+zhuangtai+"</td>"+
								 "<td><a href='view/pre/jsp/detaial.jsp?"+item.wupin_id+"' target='_blank'><img width='50px' src='"+item.wupinImagePath+"'/></a></td>"+
								 "<td><a href='view/pre/jsp/detaial.jsp?"+item.wupin_id+"' target='_blank'>"+item.wupinmc+"</a></td>"+
								 "<td>"+item.wupinnum+"</td>"+
								 "<td>"+item.wupinprice+"</td>"+
								 "<td>"+item.totalprice+"</td>"+
								 "<td>"+item.gdtime+"</td>"+
								 "<td>"+item.datetime+"</td>"+
								 "<td>"+item.xingming+"</td>"+
								 "<td>"+item.kehudz+"</td>"+
								 "<td>"+item.tel+"</td>"+
								 "<td>"+operate+"</td>"+
								 "</tr>" ;
						}) ;	
						str+="</thead></table>" ;
						
						$("#allDingdan").html(str) ;
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
						$("#user").html("当前用户：<a href='view/pre/jsp/user.jsp'>"+data.obj.username+"</a>") ;
						$("#username").val(data.obj.username) ;
						$("#password").val(data.obj.password) ;
						$("#shenfenzheng").val(data.obj.shenfenz) ;
						$("#tel").val(data.obj.tel) ;
						$("#xingming").val(data.obj.xingming) ;
					}
				}
			}) ;
    	}) ;
    	
    	//确认收货
    	function souhuo(dingdanid){
    		$.ajax({
				type:"post" ,
				url:"kehu/wupin/souhuo" ,
				dataType:"json" ,
				data:{dingdanid:dingdanid} ,
				success:function(data){
					if(data.success==true){
						window.location.reload() ;
					}
				}
			}) ;
    	}
    	
    	//添加新地址
    	function addAddress(){
    		var address = $("#newAddress").val().trim() ;
    		if(address==null||address==""){
    			alert("用户地址不能为空！") ;
    		}else{
    			$.ajax({
    				type:"post" ,
    				url:"kehu/kehuxx/addAddress" ,
    				dataType:"json" ,
    				data:{address:address},
    				success:function(data){
    					if(data.success==true){
    						alert(data.msg) ;
    						window.location.reload() ;
    					}
    				}
    			}) ;
    		}
    	}
    </script>

  </head>
  
  <body>
    <div id="header">
			<div id="user"></div>
		</div>
		
		<div id="all">
			<div id="pic" style="background:#F8CE72;height:70px;">	
				<font size="5px;">个人信息管理</font>	
			</div>
			
			<div id="address">
				<div id="addressTitle">用户信息管理</div>
				<div id="addresses">
					<div id="tab">
					<table>
						<thead style="background-color:#F4DECF;">
							<tr>
								<td>登陆账号:<input type="text" id="username" readOnly/></td>
								<td>用户密码:<input type="text" id="password" readOnly/></td>
							</tr>
							<tr>
								<td>身份证号:<input type="text" id="shenfenzheng" readOnly/></td>
								<td>联系电话:<input type="text" id="tel" readOnly/></td>
							</tr>
							<tr>
								<td>用户姓名:<input type="text" id="xingming" readOnly/></td>
							</tr>
						</thead>
					</table>
				</div>
				</div>
			</div>
			
			<div id="address">
				<div id="addressTitle">用户地址管理</div>
				<div id="addresses">
					<div id="tab">
						<!-- <div>
							<input type="text" id="newAddress" size="50"/>
							<input type="button" value="添加用户地址" onclick="addAddress()"/>
						</div> -->
						<div id="allAddresses"></div>
					</div>
				</div>
			</div>
			
			<div id="address">
				<div id="addressTitle">用户订单管理</div>
				<div id="addresses">
					<div id="tab">
						<div id="allDingdan"></div>
					</div>
				</div>
			</div>
		</div>
  </body>
</html>
