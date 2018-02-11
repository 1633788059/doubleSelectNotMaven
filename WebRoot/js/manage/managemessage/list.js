/**格式化日期:转换成（yyyy-mm-dd）
		 ****/
/*		$.fn.datebox.defaults.formatter = function(v){
		
		if (v instanceof Date) {
		
		var y = v.getFullYear();
		var m = v.getMonth() + 1;if(m<10){m="0"+m;}
		var d = v.getDate();if(d<10){d="0"+d;}
		var h = v.getHours();if(h<10){h="0"+h;}
		var i = v.getMinutes();if(i<10){i="0"+i;}
		var s = v.getSeconds();
		var ms = v.getMilliseconds();
		if (ms > 0)
		 return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s
		   + '.' + ms;
		if (h > 0 || i > 0 || s > 0)
		 return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s;
		return y + '-' + m + '-' + d;
		
		
		}
		return '';
		} 	
		*/
		
		
		
		/*
		Date.prototype.format = function(format) {
			var o = {
				"M+" : this.getMonth() + 1, // month
				"d+" : this.getDate(), // day
				"h+" : this.getHours(), // hour
				"m+" : this.getMinutes(), // minute
				"s+" : this.getSeconds(), // second
				"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
				"S" : this.getMilliseconds()
			// millisecond
			};
			if (/(y+)/.test(format))
				format = format.replace(RegExp.$1, (this.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			for ( var k in o)
				if (new RegExp("(" + k + ")").test(format))
					format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
							: ("00" + o[k]).substr(("" + o[k]).length));
			return format;
		};*/
		



function zhuanhua(value, row, index){
	var now=new Date(parseInt(value) * 1000);
	var year = now.getFullYear();    
     var   month=now.getMonth()+1;     
     var   date=now.getDate();     
     var   hour=now.getHours();     
     var   minute=now.getMinutes();     
     var   second=now.getSeconds();     
     return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
	/*var dt;
	if (value instanceof Date) {
		dt = value;
	} else {
		dt = new Date(parseInt(value) * 1000);
		if (isNaN(dt)) {
			value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); // 标红的这段是关键代码，将那个长字符串的日期值转换成正常的JS日期格式
			dt = new Date();
			dt.setTime(value);
		}
	}
	//alert("yyyy-MM-dd hh:mm");
	return dt.format("yyyy-MM-dd hh:mm:ss"); // 这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义
	*/
}


function dosearch(){
	var starttime= $("#starttime").combobox('getText'); 
	var endtime = $("#endtime").combobox('getText'); 
	var value = $("#value").val(); 
	//结果一样都可以
	/*
	 * var managesex1 = $('#managesex').combobox("getValue"); 
	var managesex2 = $('#managesex').val(); 
	alert(managesex1);
	alert(managesex2);*/
	$("#managemessage").datagrid({
		queryParams : {
			starttime : starttime,
			endtime : endtime,
			value : value
		}
	});
}
function addmanagemessage(){
	addCommon('managemessageform','添加管理信息','managemessage_form','manage/managemessage/add');
}

//修改函数
function editmanagemessage(){
		updateManageCommon('managemessage','managemessageform','修改管理信息','managemessage_form','manage/managemessage/update');
}
//提交函数
function save(){
//	if($('#fzrid').val()=='' || $('#fzrname').val()==''){
//		$.messager.alert('警告','请选择仓库负责人信息！');   
//		return false;
//	} 
	saveCommon('managemessage_form','managemessageform','managemessage');
}

//删除货架信息
function deletemanagemessage(id){
	deleteSelections(id,'managemessage','manage/managemessage/delete');        
}