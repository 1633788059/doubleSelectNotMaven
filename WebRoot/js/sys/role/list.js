
/* 设置操作格式 */
function addCol(value, row, index){
  	
	return "<span class='xiugai'><a href='javascript:void(0);' onclick='openMenu("+value+")' >设置权限</a></span>" +
			"&nbsp;&nbsp;<span class='xiugai'><a href='javascript:void(0);' onclick='editRole("+value+")' >修改</a></span>" +
			"&nbsp;&nbsp;<span class='shanchu'><a href='javascript:void(0);' onclick='deleteData("+value+")'> 删除</a></span>"; 
}

//查询
function doSearch() {

	var name = $('#name').val(); 
	
	$('#listRole').datagrid({
		pageNumber : 1,
		queryParams : {
			name : name
		}
	});
}

//取消已选数据  
function clearRoleSelections(){
	
	 clearSelections("listRole");
}

//弹出添加窗口
function add(){
	noAuthopenWindow("win","添加角色",600,"sys/roleController/preAdd");
}

//成批删除所选记录
function deleteData(id){ 
	
	deleteSelections(id,"listRole","sys/roleController/delete");
} 

function editRole(id){
	
	noAuthopenWindow("win","修改角色",600,"sys/roleController/preEdit?id="+id);
}
//设置权限
function openMenu(id){
	
	noAuthopenWindow("win","权限菜单",600,"sys/roleController/openMenu?rid="+id);
}







