
function addCol(value, row, index){
	return "<span class='xiugai'><a href='javascript:void(0);' onclick='setRole("+value+")' >设置角色</a></span>" +
		   "&nbsp;&nbsp;<span class='shanchu'><a href='javascript:void(0);' onclick='deleteData("+value+")'> 删除</a></span>"; 
}

//查询
function doSearch() {

	var name = $('#name').val(); 
	
	$('#listUser').datagrid({
		pageNumber : 1,
		queryParams : {
			name : name
		}
	});
}

//添加用户
function formAdd(){
	
	noAuthopenWindow("win","添加用户",650,"sys/userController/preAdd");
}


//取消已选
function cancleChoice(){
	
	 clearSelections("listUser");
}

//成批 或者是单个删除
function deleteData(id){ 
	deleteSelections(id,"listUser","sys/userController/delete");
} 



//设置角色按钮窗口
function setRole(id){
	
	openWindow("win","设置角色",600,"sys/userController/setRole?uid="+id);
}

//设置角色提交表单
function submitUpdateRole(){
	
	submitForm("ff","listUser","win","sys/userController/updateRole");
}


