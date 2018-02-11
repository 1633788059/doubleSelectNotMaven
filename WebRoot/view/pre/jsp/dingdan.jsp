<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>结算</title>
    <link href="view/pre/css/dingdan/dingdan.css" rel="stylesheet"/>
    <script type="text/javascript" src="view/pre/js/jquery-1.7.1.min.js"></script>
     <script type="text/javascript" src="view/pre/js/jquery.js"></script>
   	<script type="text/javascript" src="js/util/all_easyui.js"></script>
    <script type="text/javascript">
    	var k = 0 ;          //获取商品总数
    
    	$(document).ready(function(){
    		getgouwuche();
    		//获取用户所有地址
    		$.ajax({
    			type:"get" ,
    			url:"kehu/kehuxx/getKehuDiZhi" ,
    			dataType:"json" ,
    			success:function(data){
    				if(data.success==true){
    					var str = "<table>" ;
    					$.each(data.obj,function(i,item){
    						str+="<tr>"+
								 "<td><input type='radio' name='address' id='num'"+i+" value='"+item.id+"'/></td>"+
								 "<td>"+(i+1)+"</td>"+
								 "<td>"+item.kehudz+"</td>"+
								 "</tr>" ;
							
    					}) ;
    					
    					str+="</table>" ;
    					$("#addresses").html(str) ;
    				}
    			}
    		}) ;
    		//查找所有的省份
    		$.ajax({
    			type:"get" ,
    			url:"kehu/kehuxx/getProvince" ,
    			dataType:"json" ,
    			success:function(data){
    				if(data.success==true){
    					var str = "<option value=''>请选择...</option>" ;
    					$.each(data.obj,function(i,item){
    						str+="<option value="+item.id+">"+item.provinceName+"</option>";
    					}) ;
    					$("#provinceName").html(str) ; 
    				}
    			}
    		}) ;
    		
    		//当省份改变时,改变市区的值
    		$("#provinceName").change(function(){
    			$("#cityName option:not(:first)").remove();
    			var provinceId=$(this).val();
    			var provinceName=$("#provinceName option:selected").text();
    			if(provinceId!=null){
					var url="kehu/kehuxx/getCity" ;    			
    				var args={provinceId:provinceId,provinceName:provinceName,time:new Date()};
    				$.getJSON(url,args,function(data){
    					if(data.success==true){
	    					var str = "<option value=''>请选择...</option>" ;
	    					$.each(data.obj,function(i,item){
	    						str+="<option value="+item.id+">"+item.cityName+"</option>";
	    					}) ;
	    					$("#cityName").html(str); 
    				}
    				else{
    					alert("当前省没有市区");
    				}
    				});
    			}
    			
    		});
    		
    		//当市区改变时,改变县区的值
    		$("#cityName").change(function(){
    			$("#countyName option:not(:first)").remove();
    			var cityId=$(this).val();
    			var cityName=$("#cityName option:selected").text();
    			if(cityId!=null){
					var url="kehu/kehuxx/getCounty" ;    			
    				var args={"cityId":cityId,"cityName":cityName,"time":new Date()};
    				$.get(url,args,function(data){
    					if(data.success==true){
	    					var str = "<option value=''>请选择...</option>" ;
	    					$.each(data.obj,function(i,item){
	    						str+="<option value="+item.id+">"+item.countyName+"</option>";
	    					}) ;
	    					$("#countyName").html(str) ; 
    				}
    				else{
    					alert("当前市区没有县或没有区");
    				}
    				},"json");
    			}
    			
    		});
    		
    		//当县区改变时,改变街道的值
    		$("#countyName").change(function(){
    			$("#streetName option:not(:first)").remove();
    			var countyId=$(this).val();
    			var countyName=$("#countyName option:selected").text();
    			if(countyId!=null){
					var url="kehu/kehuxx/getStreet";    			
    				var args={"countyId":countyId,"countyName":countyName,"time":new Date()};
    				$.get(url,args,function(data){
    					if(data.success==true){
	    					var str = "<option value=''>请选择...</option>" ;
	    					$.each(data.obj,function(i,item){
	    						str+="<option value="+item.id+">"+item.streetName+"</option>";
	    					}) ;
	    					$("#streetName").html(str) ; 
    				}
    				else{
    					alert("当前县区没有街道");
    				}
    				},"json");
    			}
    			
    		});
	    
	    		
    		//检查是否存在登录用户
			$.ajax({
				type:"get" ,
				url:"kehu/kehuxx/getCurrentUser" ,
				dataType:"json" ,
				success:function(data){
					if(data.success==true){
						$("#user").html("当前用户：<a href='view/pre/jsp/user.jsp'>"+data.obj.username+"</a>&nbsp;|&nbsp;<a href='javascript:logout();'>退出</a>") ;
					}
				}
			}) ;
			change();
    	}) ;
    	
    	function change(){
    		$.ajax({
		    			type:"get" ,
		    			url:"kehu/wupin/getGouwuche" ,
		    			dataType:"json" ,
		    			success:function(data){
		    				if(data.success==true&&data.obj!=0){
		    					$.each(data.obj,function(i,item){
		    						$("#num"+i).change(function(){
			    						var numChange=$("#num"+i).val();
			    						if(item.num<numChange){
			    							alert("库存不足");
			    						}else{
				    						$.ajax({
												type:"post" ,
												url:"kehu/wupin/updateGouwuche" ,
												dataType:"json" ,
												data:{id:item.twupin_id,numChange:numChange} ,
												success:function(data){
													if(data.success==true){
														alert("修改成功") ;
														//重新加载
														//window.location.reload() ;
														getgouwuche();
														change();
													}
												}
											}) ;
										}
			    					});
		    					});
		    					}
		    				}
		    		});	
    	
    	}
    	//减少购物车的数量
    	function reduce(id,wupinNum){
	    	if(wupinNum>1){
	    		$.ajax({
						type:"post" ,
						url:"kehu/wupin/reduceGouwucheNum" ,
						dataType:"json" ,
						data:{id:id},
						success:function(data){
							if(data.success==true){
								//window.location.reload() ; //刷新当前页面.  
									getgouwuche();
									change();
							}
						}
					}) ;
			}
			else{
				alert("数量已经达到最低值");
			}
    	}
    	
    	//添加购物车数量
    	function add(id,num,gouwucheNum){
    		if(num<=gouwucheNum){
    			alert("你所选择的货物大于总的数量");
    		}
    		else{
    		
	    		$.ajax({
						type:"post" ,
						url:"kehu/wupin/addGouwucheNum" ,
						dataType:"json" ,
						data:{id:id},
						success:function(data1){
							if(data1.success==true){
							
									getgouwuche();
									change();
							}
						}
					}) ;
				}
    	}
    	function getgouwuche(){
	    	$.ajax({
	    			type:"get" ,
	    			url:"kehu/wupin/getGouwuche" ,
	    			dataType:"json" ,
	    			success:function(data){
	    				if(data.success==true&&data.obj!=0){
	    					var str = "" ;
	    					var money = 0 ;
							var num=0;
	    					$.each(data.obj,function(i,item){
	    						str+=
	    						"<tr>"+
	    							 "<td><a href='view/pre/jsp/detaial.jsp?"+item.twupin_id+"' target='_blank'><img width='42' height='42' src='"+item.imgPath+"'/></a></td>"+
									 "<td><span><a href='view/pre/jsp/detaial.jsp?"+item.twupin_id+"' target='_blank'>"+item.wupinmc+"</a></span></td>"+	
									 "<td><b>￥"+item.wupinPrice+"</b></td>"+
									 "<td><a href='javascript:void(0)' onclick='reduce("+item.twupin_id+","+item.gouwucheNum+");' style='heigth:20px;width:10px;margin:10px;font-size:20px;'>-</a>"+
									 "<span><input type='text' id='num"+i+"' value='"+item.gouwucheNum+"' style='width:50px;'/></span>"+
									 "<a href='javascript:void(0)' onclick='add("+item.twupin_id+","+item.num+","+item.gouwucheNum+");' style='heigth:20px;width:50px;margin:10px;font-size:20px;'>+</a></td>"+
									 "<td><em>￥"+item.gouwucheJiage+"</em></td>"+
									 "<td><a href='javascript:delateGouwuche("+item.twupin_id+")' style='cursor: pointer;'>删除</a></td>"+
								"</tr>" +
								"<tr>"+
									 "<td colspan='6'><HR style='border:1px dashed #BBBBBB' width='100%'/></td>"+
								"</tr>" ;
								
								//计算总金额
								money+=item.gouwucheJiage;
								//计算物品总数
								k=i+1;
								num+=item.gouwucheNum;
	    					}) ;
	    					$("#gouwuche").html(str) ;
	    					$("#allMoneyWupin").html("共计<font color='#CF5926' size='5'>"+k+"</font>件商品,数量为<font color='#CF5926' size='5'>"+num+"</font>个,总价格<font color='#CF5926' size='5'>￥"+money+"</font>元") ;
	    				}else{
	    					$("#gouwuche").html("<font color='#CF5926' size='15'>你目前还没有在购物车添加信息</font>") ;
	    					$("#allMoneyWupin").html("共计<font color='#CF5926' size='5'>0</font>件商品，总价格<font color='#CF5926' size='5'>￥0</font>元") ;
	    				}
	    			}
	    		}) ;
    		
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
		
		//从购物车里面移除啊物品
		function delateGouwuche(id){
			if(confirm("确定要删除吗？")==true){
				$.ajax({
					type:"post" ,
					url:"kehu/wupin/deleteGouwuche" ,
					dataType:"json" ,
					data:{id:id} ,
					success:function(data){
						if(data.success==true){
							$.messager.show({
								title : '提示',
								msg : data.msg,
								timeout : 3000,
							});
							//alert(data.msg) ;
							//重新加载
							//window.location.reload() ;
							getgouwuche();
							
						}
					}
				}) ;
			}
		}
		//添加新地址
    	function addAddress(){
    		var address = $("#newAddress").val().trim() ;
    		var provinceId=$("#provinceName option:selected").val();
    		var cityId=$("#cityName option:selected").val();
    		var countyId=$("#cityName option:selected").val();
    		var streetId=$("#streetName option:selected").val();
    		var provinceName=$("#provinceName option:selected").text();
    		var cityName=$("#cityName option:selected").text();
    		var countyName=$("#countyName option:selected").text();
    		var streetName=$("#streetName option:selected").text();
    		if(address==null||address==""||provinceId==null||provinceId==""||cityId==null||cityId==""||countyId==null||countyId==""||streetId==""){
    			alert("用户地址和城区的信息均不能为空！") ;
    		}else{
    			$.ajax({
    				type:"post" ,
    				url:"kehu/kehuxx/addAddress" ,
    				dataType:"json" ,
    				data:{address:address,provinceName:provinceName,cityName:cityName,countyName:countyName,streetName:streetName},
    				success:function(data){
    					if(data.success==true){
    						$.messager.show({
								title : '提示',
								msg : data.msg,
								timeout : 3000,
							});
    						window.location.reload() ;
    					}
    				}
    			}) ;
    		}
    	}
		//提交订单
		function submitDingdan(){
			$.ajax({
				type:"get" ,
				url:"kehu/kehuxx/getCurrentUser" ,
				dataType:"json" ,
				success:function(data){
					if(data.success==true){
						if(k>0){
							var num = new Array(k); //创建一个数量数组
							for(var i=0;i<k;i++){
								num[i] = $("#num"+i).val() ;    //根据id获取没见物品购买数量
							}
							
							var address = $("input[name='address']:checked").val();
							var time = $("input[name='time']:checked").val();   
						
							if(address==null||address==""){
								alert("客户送货地址未选择！") ;
							}else if(time==null||time==""){
								alert("客户期望送达时间未选择！") ;
							}else{
								$.ajax({
									type:"post" ,
									url:"kehu/wupin/submitDingdan" ,
									dataType:"json" ,
									traditional: true ,
									data:{num:num,address:address,time:time} ,
									success:function(data){
											$.messager.show({
												title : '提示',
												msg : data.msg,
												timeout : 3000,
											});
										//getgouwuche();
										window.location.reload() ;
									}
								}) ;	
							}
						}else{
							alert("您的购物车为空！") ;
						}
					}else{
						alert("您尚未登录！") ;
					}
				}
			}) ;
		}
    </script>
    
  </head>
  
  <body>
		<div id="header">
			<div id="user"></div>
		</div>
		
		<div id="all">
			<div id="pic">
				<img src="view/pre/images/dingdan/header.jpg"/>
			</div>
			
			<div id="data">
				<div id="tab">
					<table>
						<thead style="background-color:#F4DECF;">
							<tr>
								<td>图片</td>
								<td>商品</td>
								<td>单价</td>
								<td>数量</td>
								<td>金额小计</td>
								<td>操作</td>
							</tr>
						</thead>
						
						<tbody id="gouwuche"></tbody>
						
					</table>
				</div>
			</div>
			
			<div id="address">
				<div id="addressTitle">选择用户地址</div>
				<div id="addresses">
				</div>
				<div id="addressTitle">
					添加新用户地址
				</div>
				<div id="addresses">
					<p>所在区域</p>
					<select id="provinceName">
					</select>
					<select id="cityName">
						<option value="">请选择...</option>
					</select>
					<select id="countyName">
						<option value="">请选择...</option>
					</select>
					<select id="streetName">
						<option value="">请选择...</option>
					</select>
				</div>
				<div id="addressTitle">详细地址</div>
				<div id="addresses">
					<div id="tab">
						<div>
							<input type="text" id="newAddress" size="50"/>
							<input type="button" value="添加用户地址" onclick="addAddress()"/>
						</div>
					</div>
				</div>
			</div>
			
			<div id="address">
				<div id="addressTitle">期望送达时间</div>
				<div id="addresses">
					<label><input name="time" type="radio" value="0" />30分钟内 </label> &nbsp;&nbsp;
					<label><input name="time" type="radio" value="1" />1小时内 </label> &nbsp;&nbsp;
					<label><input name="time" type="radio" value="2" />3小时内 </label> &nbsp;&nbsp;
					<label><input name="time" type="radio" value="3" />1天内 </label> &nbsp;&nbsp;
					<label><input name="time" type="radio" value="4" />2天内 </label> &nbsp;&nbsp;
					<label><input name="time" type="radio" value="5" />不限</label>
				</div>
			</div>
			
			<div id="submit">
				<div id="left"><span id="allMoneyWupin"></span></div>
				<div id="right"><a href="javascript:submitDingdan();"><img src="view/pre/images/dingdan/jiesuan.jpg"/></a></div>
			</div>
		</div>
	</body>
</html>
