/*// 计算数据库服务器与客户端的时间差
var dbtime = parseInt("1460977114000");
var clienttime = new Date().getTime();
var difftime = dbtime-clienttime ; 
function showTimes(){
	//获取当前日期
	var now = new Date(new Date().getTime() + difftime);
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
*/


/**
 * 提交填写的志愿的信息
 */
function addvolunteer(){
	 var teachersno1=$('input[name="teachersno1"]').val();
	 var teachersno2=$('input[name="teachersno2"]').val();
	 var teachersno3=$('input[name="teachersno3"]').val();
	 $.ajax({
			url:"volunteer/volunteermessage/studentadd" ,
			type:"get" ,
			dataType:"json",
			data:{teachersno1:teachersno1,teachersno2:teachersno2,teachersno3:teachersno3},
			success:function(data){
				if(data.success==true){
					 $.messager.show({
		                    title: '提示',
		                    msg: data.msg
		                });
					  setTimeout(function () {
						  window.location.reload();
					    }, 3000);
				}else{
					 $.messager.show({
		                    title: '提示',
		                    msg: data.msg
		                });
					  setTimeout(function () {
						  window.location.reload();
					    }, 3000);
				}
			}
	}) ;
	}
	




function closeteacher1(){
	$('#chaxunteacher1').hide();
}
//教师信息得查询
function dosearchteacher1(value){
	  $('#chaxunteacher1').show();
	  $("#chaxunteacher1").css("z-index","9999");
	  $('#teacherTab1').datagrid
	  (
		 {
			 singleSelect:true,
			 /**
			  * idField的属性意思为当你选择两个页面的不同行是，设定idField的话，不会被刷新
			  */
			// idField:'id',
		     url:'teacher/teachermessage/search',
		     pagination:true,
		     queryParams :
		     {
		    	 teachersno: value
		     },
		      columns:
		      [[
		        /*{title:'id',field:'id',width:100,align:'center'},*/
		        {title:'学号',field:'teachersno',width:100,align:'center'},	
		        {title:'姓名',field:'teachername',width:100,align:'center'},	
		        {title:'性别',field:'teachersex',width:100,align:'center'},	
		        {title:'专业',field:'teachermajor',width:100,align:'center'},	
		        {title:'学历',field:'degree',width:100,align:'center'},	
		        {title:'指导最少个数',field:'guidemin',width:100,align:'center'},	
		        {title:'指导最多个数',field:'guidemax',width:100,align:'center'},
		      ]],
		      onDblClickRow: function(index,field,value){
		    	  var row = $('#teacherTab1').datagrid('getSelected');
		    	  console.info(row);
		    	  if (row){
		    		  console.info(row);
		    		 $('input[name="teachersno1"]').val(row.teachersno);
					 $('#chaxunteacher1').hide();
		    	  }
		  	  }
		  }
		);
	
}
function closeteacher2(){
	$('#chaxunteacher2').hide();
}
//教师信息得查询
function dosearchteacher2(value){
	  $('#chaxunteacher2').show();
	  $("#chaxunteacher2").css("z-index","9999");
	  $('#teacherTab2').datagrid
	  (
		 {
			 singleSelect:true,
			 /**
			  * idField的属性意思为当你选择两个页面的不同行是，设定idField的话，不会被刷新
			  */
			// idField:'id',
		     url:'teacher/teachermessage/search',
		     pagination:true,
		     queryParams :
		     {
		    	 teachersno: value
		     },
		      columns:
		      [[
		        /*{title:'id',field:'id',width:100,align:'center'},*/
		        {title:'学号',field:'teachersno',width:100,align:'center'},	
		        {title:'姓名',field:'teachername',width:100,align:'center'},	
		        {title:'性别',field:'teachersex',width:100,align:'center'},	
		        {title:'专业',field:'teachermajor',width:100,align:'center'},	
		        {title:'学历',field:'degree',width:100,align:'center'},	
		        {title:'指导最少个数',field:'guidemin',width:100,align:'center'},	
		        {title:'指导最多个数',field:'guidemax',width:100,align:'center'},
		      ]],
		      onDblClickRow: function(index,field,value){
		    	  var row = $('#teacherTab2').datagrid('getSelected');
		    	  console.info(row);
		    	  if (row){
		    		  console.info(row);
		    		 $('input[name="teachersno2"]').val(row.teachersno);
					 $('#chaxunteacher2').hide();
		    	  }
		  	  }
		  }
		);
	
}
function closeteacher3(){
	$('#chaxunteacher3').hide();
}
//教师信息得查询
function dosearchteacher3(value){
	  $('#chaxunteacher3').show();
	  $("#chaxunteacher3").css("z-index","9999");
	  $('#teacherTab3').datagrid
	  (
		 {
			 singleSelect:true,
			 /**
			  * idField的属性意思为当你选择两个页面的不同行是，设定idField的话，不会被刷新
			  */
			// idField:'id',
		     url:'teacher/teachermessage/search',
		     pagination:true,
		     queryParams :
		     {
		    	 teachersno: value
		     },
		      columns:
		      [[
		        /*{title:'id',field:'id',width:100,align:'center'},*/
		        {title:'学号',field:'teachersno',width:100,align:'center'},	
		        {title:'姓名',field:'teachername',width:100,align:'center'},	
		        {title:'性别',field:'teachersex',width:100,align:'center'},	
		        {title:'专业',field:'teachermajor',width:100,align:'center'},	
		        {title:'学历',field:'degree',width:100,align:'center'},	
		        {title:'指导最少个数',field:'guidemin',width:100,align:'center'},	
		        {title:'指导最多个数',field:'guidemax',width:100,align:'center'},
		      ]],
		      onDblClickRow: function(index,field,value){
		    	  var row = $('#teacherTab3').datagrid('getSelected');
		    	  console.info(row);
		    	  if (row){
		    		  console.info(row);
		    		 $('input[name="teachersno3"]').val(row.teachersno);
					 $('#chaxunteacher3').hide();
		    	  }
		  	  }
		  }
		);
	
}

