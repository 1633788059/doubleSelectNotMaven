//提交编辑角色表单
function submitRoleEdit(){
	
	submitForm("ff","listRole","win","sys/roleController/update");
}

//清空编辑角色表单
function clearForm() {

	$('#ff').form('clear');
}
