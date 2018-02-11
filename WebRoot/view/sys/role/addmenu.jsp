<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>设置权限</title>
  </head>
  <body>
    <div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>填写下面的内容并按提交保存！</div>
	</div>
	<form id="ff" method="post">	
		<!-- 角色id 对应数据库中trole  id -->		
		<input name="rid"  id="rid" type="hidden" value="${rid}"/>
		
		<!-- 原有的权限  对应数据库中tauthority 中的id-->
		<input name="oaids"  id="oaids" type="hidden"/>	
				
		<!--权限id 更新后的权限 对应数据库中tauthority 中的id  -->
		<input name="aids"  id="aids" type="hidden"/>
	</form>
	
	
	<script type="text/javascript">
	
		$("#tt").tree({   
			checkbox:true,
		    url:"sys/roleController/queryTreeMenu?rid=" + $("#rid").val(),
		    onLoadSuccess: function(node,data){
		    	defaultChecked("oaids");
				$("#submit").attr("style","margin-left:30%;display:block;");
			}
	
		});  
	
		function defaultChecked(value){
			var nodes = $('#tt').tree('getChecked'); 
			var nodeParents = [];
			var ids = ''; 
			if (nodes.length > 0) { 
				for (var i = 0; i < nodes.length; i++) { 
					if (ids != '') { 
						ids += ','; 
					} 
					ids += nodes[i].id;
				}
			}
			$("#tt").find('.tree-checkbox2').each(function(){		
			        var node = $(this).parent();
			
			        nodeParents.push($.extend({}, $.data(node[0], 'tree-node'), {	
			                target: node[0],			
			                checked: node.find('.tree-checkbox').hasClass('tree-checkbox2')			
			        }));
			
			});
			for (var i = 0; i < nodeParents.length; i++){
				if (nodeParents.length > 0) { 
					for (var i = 0; i < nodeParents.length; i++) { 
						if (ids != '') { 
							ids += ','; 
						} 
						ids += nodeParents[i].id;
					}
				}
			}
			$("#" + value).val(ids);
		}
		
		function submit(){
			defaultChecked("aids");	
			submitForm("ff","listRole","win","sys/roleController/updateR_a");
		}

	</script>
	<div style="display:none;" id="submit" >
	
		<a href="javascript:void(0)" data-options="iconCls:'icon-ok',plain:true"
			class="easyui-linkbutton" onclick="submit()" style="margin:0 auto;">提交</a>
		
		<ul id="tt"></ul>
	</div>
  </body>
</html>
