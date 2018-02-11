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

function showMessage(){
	var teachersno = $("#sno").val(); 
	$("#volunteermessage").datagrid({
		url:'volunteer/volunteermessage/search?teachersno='+teachersno+"&rank=5", 
	});
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
	var teachersno = $("#sno").val();
	var studentsno = $("#studentsno").val(); 
	var rank = $("#rank").combobox("getValue"); 
	$("#volunteermessage").datagrid({
		url:'volunteer/volunteermessage/search',
		queryParams : {
			teachersno : teachersno,
			studentsno : studentsno,
			rank : rank
		}
	});
}