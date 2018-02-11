/**
 * 显示学生信息
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function studentmessageBysno(value, row, index){
	return "<span class='xiugai'><a href='student/studentmessage/studentmessage?studentSno="+value+"'>"+value+"</a></span>"; 
}

/**
 * 显示教师信息
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function teachermessageBysno(value, row, index){
	return "<span class='xiugai'><a href='teacher/teachermessage/teachermessage?teacherSno="+value+"'>"+value+"</a></span>"; 
}

/**
 * 显示所填报的为第几志愿
 * @param value
 * @param row
 * @param index
 */
function volunteer(value, row, index){
	if(value==1){
		return "学生第一志愿";
	}
	if(value==2){
		return "学生第二志愿";
	}
	if(value==3){
		return "学生第三志愿";
	}
	if(value==4){
		return "导师志愿";
	}
}

function dosearch(){
	var teachersno= $("#teachersno").val(); 
	var studentsno = $("#studentsno").val(); 
	var rank = $("#rank").combobox("getValue"); 
	$("#volunteermessage").datagrid({
		queryParams : {
			teachersno : teachersno,
			studentsno : studentsno,
			rank : rank
		}
	});
}

function closestudent(){
	$('#chaxunstudent').hide();
}
//物品信息查询
function dosearchstudent(value){
//	  if(value=="")
//	  {
//		  $.messager.alert('提示', "负责人姓名不能为空！", 'info');
//		  return ;
//	  }
	  $('#chaxunstudent').show();
	  $("#chaxunstudent").css("z-index","9999");
	  $('#studentTab').datagrid
	  (
		 {
			 singleSelect:true,
			 /**
			  * idField的属性意思为当你选择两个页面的不同行是，设定idField的话，不会被刷新
			  */
			// idField:'id',
		     url:'student/studentmessage/search',
		     pagination:true,
		     queryParams :
		     {
		    	 studentsno: value
		     },
		      columns:
		      [[
		        /*{title:'id',field:'id',width:100,align:'center'},*/
		        {title:'学号',field:'studentsno',width:100,align:'center'},	
		        {title:'姓名',field:'studentname',width:100,align:'center'},	
		        {title:'性别',field:'studentsex',width:100,align:'center'},	
		        {title:'专业',field:'studentmajor',width:100,align:'center'},	
		        {title:'最后学历',field:'finaldegree',width:100,align:'center'},	
		        {title:'毕业学校',field:'graduation',width:100,align:'center'},	
		        {title:'专业特长',field:'expertise',width:100,align:'center'},
		        {title:'毕业时间',field:'time',width:100,align:'center'},	
		      ]],
		      onDblClickRow: function(index,field,value){
		    	  var row = $('#studentTab').datagrid('getSelected');
		    	  console.info(row);
		    	  if (row){
		    		  console.info(row);
		    		 $('input[name="studentsno"]').val(row.studentsno);
					 $('#chaxunstudent').hide();
		    	  }
		  	  }
		  }
		);
	
}


function closeteacher(){
	$('#chaxunteacher').hide();
}
//教师信息得查询
function dosearchteacher(value){
//	  if(value=="")
//	  {
//		  $.messager.alert('提示', "负责人姓名不能为空！", 'info');
//		  return ;
//	  }
	  $('#chaxunteacher').show();
	  $("#chaxunteacher").css("z-index","9999");
	  $('#teacherTab').datagrid
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
		    	  var row = $('#teacherTab').datagrid('getSelected');
		    	  console.info(row);
		    	  if (row){
		    		  console.info(row);
		    		 $('input[name="teachersno"]').val(row.teachersno);
					 $('#chaxunteacher').hide();
		    	  }
		  	  }
		  }
		);
	
}



function addvolunteermessage(){
	addCommon('volunteermessageform','添加志愿信息','volunteermessage_form','volunteer/volunteermessage/add');
}

//修改函数
function editvolunteermessage(){
		updateVolunteer('volunteermessage','volunteermessageform','修改志愿信息','volunteermessage_form','volunteer/volunteermessage/update');
}
//提交函数
function save(){
//	if($('#fzrid').val()=='' || $('#fzrname').val()==''){
//		$.messager.alert('警告','请选择仓库负责人信息！');   
//		return false;
//	} 
	saveCommon('volunteermessage_form','volunteermessageform','volunteermessage');
}

//删除货架信息
function deletevolunteermessage(){
	deleteVolunteer('volunteermessage','volunteer/volunteermessage/delete');        
}