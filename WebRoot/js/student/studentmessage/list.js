function studentmessage(value, row, index){
	return "<span class='xiugai'><a href='student/studentmessage/studentmessage?studentId="+value+"'>详细信息</a></span>"; 
}

function studentmessageBysno(value, row, index){
	return "<span class='xiugai'><a href='student/studentmessage/studentmessage?studentSno="+value+"'>"+value+"</a></span>"; 
}
function dosearch(){
	var studentsno= $("#studentsno").val(); 
	var studentname = $("#studentname").val(); 
	var studentsex = $("#studentsex").combobox("getText"); 
	//结果一样都可以
	/*
	 * var studentsex1 = $('#studentsex').combobox("getValue"); 
	var studentsex2 = $('#studentsex').val(); 
	alert(studentsex1);
	alert(studentsex2);*/
	$("#studentmessage").datagrid({
		queryParams : {
			studentsno : studentsno,
			studentname : studentname,
			studentsex : studentsex
		}
	});
}
function addstudentmessage(){
	addCommon('studentmessageform','添加学生信息','studentmessage_form','student/studentmessage/add');
}

//修改函数
function editstudentmessage(){
		updateCommon('studentmessage','studentmessageform','修改学生信息','studentmessage_form','student/studentmessage/update');
}
//提交函数
function save(){
//	if($('#fzrid').val()=='' || $('#fzrname').val()==''){
//		$.messager.alert('警告','请选择仓库负责人信息！');   
//		return false;
//	} 
	 var time = $('.easyui-datetimebox').datetimebox('getValue');
	 alert(time);
	saveCommon('studentmessage_form','studentmessageform','studentmessage');
}

//删除货架信息
function deletestudentmessage(id){
	//deleteCommon('studentmessage','student/studentmessage/delete'); 
	deleteSelections(id,'studentmessage','student/studentmessage/delete');
}