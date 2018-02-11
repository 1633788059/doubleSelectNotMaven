function teachermessage(value, row, index){
	return "<span class='xiugai'><a href='teacher/teachermessage/teachermessage?teacherId="+value+"'>详细信息</a></span>"; 
}

function teachermessageBysno(value, row, index){
	return "<span class='xiugai'><a href='teacher/teachermessage/teachermessage?teacherSno="+value+"'>"+value+"</a></span>"; 
}
function dosearch(){
	var teachersno= $("#teachersno").val(); 
	var teachername = $("#teachername").val(); 
	var teachersex = $("#teachersex").combobox("getText"); 
	//结果一样都可以
	/*
	 * var teachersex1 = $('#teachersex').combobox("getValue"); 
	var teachersex2 = $('#teachersex').val(); 
	alert(teachersex1);
	alert(teachersex2);*/
	$("#teachermessage").datagrid({
		queryParams : {
			teachersno : teachersno,
			teachername : teachername,
			teachersex : teachersex
		}
	});
}
function addteachermessage(){
	addCommon('teachermessageform','添加教师信息','teachermessage_form','teacher/teachermessage/add');
}

//修改函数
function editteachermessage(){
		updateCommon('teachermessage','teachermessageform','修改教师信息','teachermessage_form','teacher/teachermessage/update');
}
//提交函数
function save(){
//	if($('#fzrid').val()=='' || $('#fzrname').val()==''){
//		$.messager.alert('警告','请选择仓库负责人信息！');   
//		return false;
//	} 
	saveCommon('teachermessage_form','teachermessageform','teachermessage');
}

//删除货架信息
function deleteteachermessage(id){
	deleteSelections(id,'teachermessage','teacher/teachermessage/delete');        
}