
/**
 * 清除已经选中列表数据
 * @param datagrid 需要清空已选数据table表的id
 */
function clearSelections(datagrid) {
	var rows = $('#'+datagrid).datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '没有要取消的数据！', 'info');
		return;
	}
	$('#'+datagrid).datagrid('clearSelections');
}

/**增加、删除、修改的请求路径**/
var url_wl;

/**
 * 添加一条数据
 * @param formDivId 所要提交的form表单外层的div ID；
 * @param title 所要提交的表单标题；
 * @param formId 所要提交的表单ID；
 * @param url 请求路径；
 * */
function addCommon(formDivId,title,formId,url){
    $('#'+formDivId).dialog('open').dialog('setTitle',title);	
    $('#'+formId).form('clear');
    url_wl = url;
}

/**
 * 添加一条数据
 * @param tableId list页面中datagrid控件的id；
 * @param formDivId 所要提交的form表单外层的div ID；
 * @param title 所要提交的表单标题；
 * @param formId 所要提交的表单ID；
 * @param url 请求路径；
 * */
function updateCommon(tableId,formDivId,title,formId,url){
    var row = $('#'+tableId).datagrid('getSelected');
    if (row){
        $('#'+formDivId).dialog('open').dialog('setTitle',title);
        $('#'+formId).form('load',row);    //此句很重要
        url_wl = url+ "?id=" + row.id;
    }
}

/**
 * 格式转化
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function zhuanhua(value){
	var now=new Date(parseInt(value) * 1000);
	var year = now.getFullYear();    
     var   month=now.getMonth()+1;     
     var   date=now.getDate();     
     var   hour=now.getHours();     
     var   minute=now.getMinutes();     
     var   second=now.getSeconds();     
     return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;   
}

function updateManageCommon(tableId,formDivId,title,formId,url){
    var row = $('#'+tableId).datagrid('getSelected');
    if (row){
    	var starttime=zhuanhua(row.starttime);
    	var endtime=zhuanhua(row.endtime);
        $('#'+formDivId).dialog('open').dialog('setTitle',title);
        $('#'+formId).form('load',{
        	id:row.id,
        	starttime:starttime,
        	endtime:endtime,
        	value:row.value,
        	note:row.note,
        });    //此句很重要
        url_wl = url+ "?id=" + row.id;
    }
}
/**
 * 添加一条数据
 * @param tableId list页面中datagrid控件的id；
 * @param formDivId 所要提交的form表单外层的div ID；
 * @param title 所要提交的表单标题；
 * @param formId 所要提交的表单ID；
 * @param url 请求路径；
 * */
function updateVolunteer(tableId,formDivId,title,formId,url){
    var row = $('#'+tableId).datagrid('getSelected');
    if (row){
        $('#'+formDivId).dialog('open').dialog('setTitle',title);
        $('#'+formId).form('load',row);    //此句很重要
        var studentsno=row.studentsno;
        var teachersno=row.teachersno;
        var rank=row.rank;
        var row1=studentsno+","+teachersno+","+rank;
        //url_wl=url;
        url_wl = url+ "?row1="+row1;
    }
}



/**
 * 修改匹配的信息
 * @param tableId list页面中datagrid控件的id；
 * @param formDivId 所要提交的form表单外层的div ID；
 * @param title 所要提交的表单标题；
 * @param formId 所要提交的表单ID；
 * @param url 请求路径；
 * */
function updatematch(tableId,formDivId,title,formId,url){
    var row = $('#'+tableId).datagrid('getSelected');
    if (row){
        $('#'+formDivId).dialog('open').dialog('setTitle',title);
        $('#'+formId).form('load',row);    //此句很重要
        var studentsno=row.studentsno;
        var teachersno=row.teachersno;
        var row1=studentsno+","+teachersno;
        //url_wl=url;
        url_wl = url+ "?row1="+row1;
    }
}

/**
 * 保存数据
 * @param formId 所要提交的表单ID；
 * @param formDivId 所要提交的form表单外层的div ID；
 * @param tableId list页面中datagrid控件的id；
 * */
function saveCommon(formId,formDivId,tableId){
	
	$.ajax({
		url : "sys/authController/checkAuth",
		type : 'POST',
		data : "url="+url_wl,
		dataType : 'json',
		cache : false,//设置为 false 将不会从浏览器缓存中加载请求信息。
		beforeSend: function(){
			if($('#'+formId).form('validate')==false){
				return false;
			}
		},
		success : function(data) {
			if (data.success == false) {  
				alert("您没有操作权限！");		
			} else {
				    $('#'+formId).form('submit',{
				        url: url_wl,
				        onSubmit: function(){
				            return $(this).form('validate');
				        },
				        success: function(result){
				        	/**
				        	 * 将字符串转化位json类型
				        	 */
				            var result = eval('('+result+')');  
				            if (result.success){
				            	 $.messager.show({
				                    title: '提示',
				                    msg: result.msg
				                });
				                $('#'+formDivId).dialog('close');      		        	
				                $('#'+tableId).datagrid('reload');   
	
				            } else {
				                $.messager.show({
				                    title: '提示',
				                    msg: result.msg
				                });
				            }
				        }
				    });
			}
		},
		error : function() {
			$.messager.alert('提示', "失败，请重试或刷新后再试！", 'info');
		}
	});
}
/**
 * 保存数据
 * @param tableId list页面中datagrid控件的id；
 * @param url 所要请求的路径
 * *//*
function deleteCommon(tableId,url){
	var ids =new Array();
	var rows = $('#'+tableId).datagrid('getSelections');
	for ( var i = 0; i < rows.length; i++) {
		console.info("The %s jumped over %d tall buildings"+rows[i].id);
		ids.push(rows[i].id);
		alert(rows[i].id);
	}
	alert(ids.toString());
    if (rows){
        $.messager.confirm('警告','您确定要删除该信息吗?',function(r){
            if (r){
            	$.ajax({
            		url : "sys/authController/checkAuth",
            		type : 'POST',
            		data : "url="+ url,
            		dataType : 'json',
            		cache : false,
            		success : function(data) {
            			if (data.success == false) {  
            				alert("您没有操作权限！");		
            			} else {
	                          $.post(url,{ids:ids.toString()},function(result){
		                          if (result.success){
		                              $.messager.show({    
		                                  title: '提示',
		                                  msg: result.msg
		                              });
		                              $('#'+tableId).datagrid('reload');  
		                          } else {
		                              $.messager.show({    
		                                  title: '提示',
		                                  msg: result.msg
		                              });
		                          }
	                         },'json');            				
            			}
            		},
            		error : function() {
            			$.messager.alert('提示', "失败，请重试或刷新后再试！", 'info');
            		}
            	});
            }
        });
    }
}
*/
/**
 * 删除志愿的信息
 */
function deleteVolunteer(tableId,url){
	var ids =new Array();
	var rows = $('#'+tableId).datagrid('getSelections');
	$.each(rows,function(index,item){
		var item1=item.studentsno+","+item.teachersno+","+item.rank;
		ids.push(item1);
	});
    if (rows){
        $.messager.confirm('警告','您确定要删除该信息吗?',function(r){
            if (r){
            	$.ajax({
            		url : "sys/authController/checkAuth",
            		type : 'POST',
            		data : "url="+ url,
            		dataType : 'json',
            		cache : false,
            		success : function(data) {
            			if (data.success == false) {  
            				alert("您没有操作权限！");		
            			} else {
	                          $.post(url,{ids:ids.toString()},function(result){
		                          if (result.success){
		                              $.messager.show({    
		                                  title: '提示',
		                                  msg: result.msg
		                              });
		                              $('#'+tableId).datagrid('reload');  
		                          } else {
		                              $.messager.show({    
		                                  title: '提示',
		                                  msg: result.msg
		                              });
		                          }
	                         },'json');            				
            			}
            		},
            		error : function() {
            			$.messager.alert('提示', "失败，请重试或刷新后再试！", 'info');
            		}
            	});
            }
        });
    }
}

/**
 * 删除匹配的信息
 * @param tableId
 * @param url
 */
function deletematch(tableId,url){
	var ids =new Array();
	var rows = $('#'+tableId).datagrid('getSelections');
	$.each(rows,function(index,item){
		var item1=item.studentsno+","+item.teachersno;
		ids.push(item1);
	});
    if (rows){
        $.messager.confirm('警告','您确定要删除该信息吗?',function(r){
            if (r){
            	$.ajax({
            		url : "sys/authController/checkAuth",
            		type : 'POST',
            		data : "url="+ url,
            		dataType : 'json',
            		cache : false,
            		success : function(data) {
            			if (data.success == false) {  
            				alert("您没有操作权限！");		
            			} else {
	                          $.post(url,{ids:ids.toString()},function(result){
		                          if (result.success){
		                              $.messager.show({    
		                                  title: '提示',
		                                  msg: result.msg
		                              });
		                              $('#'+tableId).datagrid('reload');  
		                          } else {
		                              $.messager.show({    
		                                  title: '提示',
		                                  msg: result.msg
		                              });
		                          }
	                         },'json');            				
            			}
            		},
            		error : function() {
            			$.messager.alert('提示', "失败，请重试或刷新后再试！", 'info');
            		}
            	});
            }
        });
    }
}

function teacheraddvolunteer(tableId,url){
	var ids =new Array();
	var rows = $('#'+tableId).datagrid('getSelections');
	$.each(rows,function(index,item){
		var item1=item.studentsno;
		ids.push(item1);
	});
    if (rows){
        $.messager.confirm('警告','您确定要添加到志愿信息吗?',function(r){
            if (r){
            	$.ajax({
            		url : "sys/authController/checkAuth",
            		type : 'POST',
            		data : "url="+ url,
            		dataType : 'json',
            		cache : false,
            		success : function(data) {
            			if (data.success == false) {  
            				alert("您没有操作权限！");		
            			} else {
	                          $.post(url,{ids:ids.toString()},function(result){
		                          if (result.success){
		                              $.messager.show({    
		                                  title: '提示',
		                                  msg: result.msg
		                              });
		                              $('#'+tableId).datagrid('reload');  
		                          } else {
		                              $.messager.show({    
		                                  title: '提示',
		                                  msg: result.msg
		                              });
		                          }
	                         },'json');            				
            			}
            		},
            		error : function() {
            			$.messager.alert('提示', "失败，请重试或刷新后再试！", 'info');
            		}
            	});
            }
        });
    }
}

/**
 * 保存数据
 * @param tableId list页面中datagrid控件的id；
 * @param url 所要请求的路径
 * */
function deleteCommon(tableId,url){
	var row = $('#'+tableId).datagrid('getSelected');
    if (row){
        $.messager.confirm('警告','您确定要删除该信息吗?',function(r){
            if (r){
            	$.ajax({
            		url : "sys/authController/checkAuth",
            		type : 'POST',
            		data : "url="+ url,
            		dataType : 'json',
            		cache : false,
            		success : function(data) {
            			if (data.success == false) {  
            				alert("您没有操作权限！");		
            			} else {
	                          $.post(url,{id:row.id},function(result){
		                          if (result.success){
		                              $.messager.show({    
		                                  title: '提示',
		                                  msg: result.msg
		                              });
		                              $('#'+tableId).datagrid('reload');  
		                          } else {
		                              $.messager.show({    
		                                  title: '提示',
		                                  msg: result.msg
		                              });
		                          }
	                         },'json');            				
            			}
            		},
            		error : function() {
            			$.messager.alert('提示', "失败，请重试或刷新后再试！", 'info');
            		}
            	});
            }
        });
    }
}

/**
 * 批量或者单个删除列表数据
 * @param id table表中每行数据所对应的的id，这个字段是区分单个删除还是批量删除的依据
 * @param tableId table表单的id，当提交成功后，需要刷新展示数据的table
 * @param url 提交请求的路径
 */

function deleteSelections(id,tableId, url){
	var ids = [];
	if(id == undefined){
		var rows = $('#'+tableId).datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示', '请选择要删除的数据！', 'info');
			return;
		}
		for ( var i = 0; i < rows.length; i++) {
			console.info("The %s jumped over %d tall buildings"+rows[i].id);
			ids.push(rows[i].id);
		}
	}else{
		ids.push(id);
	}
	$.messager.confirm('确认','确定要删除？',function(r){
	    if (r){   
	    	$.ajax({
				url : url,
				type : 'POST',
				data : {"ids":ids},
				//data : "ids="+ids, 当时一个数组时候汇报异常，
				traditional: true,  //必须加上 不然data : {"ids":ids}传到后台为空
				dataType : 'json',
				cache : false,
				success : function(data) {
					console.info("common.js:"+data);
					if (data.success == true) {
						$.messager.alert('提示', data.msg, 'info');
						$('#'+tableId).datagrid('reload');
						$('#'+tableId).datagrid('clearSelections');  
					} else {
						$.messager.alert('提示', data.msg, 'info');
					}
				},
				error : function() {
					$.messager.alert('提示', "删除失败，请重试或刷新后再试！", 'info');
				}
			});  
	    }   
	});
}

//提交表单是只要是服务器段跳转，仍然会有拦截器提示没有权限
function noAuthopenWindow(divId,title,width,url){
	$('#'+divId).window({
		title : title,
	    width:width,  
	    height:$(document.body).height()*0.8,
	    top:20,
	    draggable:true,
	    minimizable:false
	}); 
	$('#'+divId).window('refresh', url);	//通过ajax加载窗口内容
}


function openWindow(divId,title,width,url){

	$.ajax({
		url : "sys/authController/checkAuth",
		type : 'POST',
		data : "url="+url,
		dataType : 'json',
		cache : false,
		success : function(data) {
			if (data.success == false) {  
				alert("您没有操作权限！");		
			} else {
				$('#'+divId).window({
					title : title,
				    width:width,  
				    height:$(document.body).height()*0.8,
				    top:20,
				    draggable:true,
				    minimizable:false
				}); 
				$('#'+divId).window('refresh', url);	//通过ajax加载窗口内容
			}
		},
		error : function() {
			$.messager.alert('提示', "失败，请重试或刷新后再试！", 'info');
		}
	});  
}

/**
 * 
 * @param formId 根据它提交表单
 * @param tableId 提交表单后刷新后重新显示提交后完整的数据
 * @param divId 关闭已经提交后的表单窗口
 * @param url 提交表单的路劲
 */
function submitForm(formId,tableId,divId,url) {
	
	$('#'+formId).form('submit', {
		url : url,
		onSubmit : function() {		
			return $('#'+formId).form('validate');
		},
		success : function(data) {
			/**
			 * form表单返回的为字符串，需要将字符串转化为json对象
			 */
			var arr= $.parseJSON(data);		//将提交返回后的Json的值转化为json对象
			//alert(data.success);
			if (arr.success == true) {
				$.messager.alert('提示', arr.msg, 'info');
				$('#'+divId).window('close');
	        	$('#'+tableId).datagrid('reload');
				$('#'+tableId).datagrid('clearSelections');  
			} else {
				$.messager.alert('提示', arr.msg, 'info');
			}
		}
	});
}

/**
 * 
 * @param tableId
 * @param divId
 * @param url
 * @param data
 */
function submitFormByAjax(tableId,divId,url,data) {
	$.ajax({
		url : url,
		type : 'POST',
		data : data,
		dataType : 'json',
		cache : false,
		success : function(data) {
			if (data.success == true) {
				$.messager.alert('提示', data.msg, 'info');
				$('#'+divId).window('close');
				$('#'+tableId).datagrid('reload');
				$('#'+tableId).datagrid('clearSelections');  
			} else {
				$.messager.alert('提示', data.msg, 'info');
			}
		},
		error : function() {
			$.messager.alert('提示', "添加失败，请重试或刷新后再试！", 'info');
		}
	});
}

/**
 * 提交表格编辑行数据
 * @param tableId table表单的id，当提交成功后，需要刷新展示数据的table
 * @param url 提交请求的路径
 * @param data 提交请求的参数
 */
function submitDatagridData(tableId,url,data) {
	$.ajax({
		url : url,
		type : 'POST',
		data : data,
		dataType : 'json',
		cache : false,
		success : function(data) {
			if (data.success == true) {
				$.messager.alert('提示', data.msg, 'info');
				$('#'+tableId).datagrid('reload');
				$('#'+tableId).datagrid('clearSelections');  
			} else {
				$.messager.alert('提示', data.msg, 'info');
			}
		},
		error : function() {
			$.messager.alert('提示', "操作失败，请重试或刷新后再试！", 'info');
		}
	});
}

function clearForm(formId){
	
	$('#'+formId).form('clear');
}



/**
 * 格式化时间样式
 */
//$.fn.datebox.defaults.formatter = function(date){
//	var y = date.getFullYear();
//	var m = date.getMonth()+1;
//	var d = date.getDate();
//	return y+'-'+m+'-'+d;
//};

/**
 * 格式化时间样式
 */
Date.prototype.format = function(format){
    /*
     * eg:format="yyyy-MM-dd hh:mm:ss";
     */
    if(!format){
        format = "yyyy-MM-dd hh:mm:ss";
    }

    var o = {
            "M+": this.getMonth() + 1, // month
            "d+": this.getDate(), // day
            "h+": this.getHours(), // hour
            "m+": this.getMinutes(), // minute
            "s+": this.getSeconds(), // second
            "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
            "S": this.getMilliseconds()
            // millisecond
   };

   if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) { 
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" +o[k]).length));
       }
    }
    return format;
};

/**
 * json对象为json字符串(此方法使用于所有浏览器)
 * @param json
 * @returns {String}
 */
function jsonArryToString(json) {
    var a = '', tpl = '"{0}":"{1}"';
    for (var i=0;i<json.length;i++) {
        for (var name in json[i]) {
            var b = tpl.replace('{0}', name).replace('{1}', json[i][name]);
            a += a.length == 0 ? b : ',' + b;
        }
    }
    return '[{' + a + '}]';
}

/**
 * 初始化前端显示的日期格式
 * **/
//$.fn.datebox.defaults.formatter = function(date){
//	var y = date.getFullYear();
//	var m = date.getMonth()+1;
//	var d = date.getDate();
//	return y+'--'+m+'--'+d;
//};

