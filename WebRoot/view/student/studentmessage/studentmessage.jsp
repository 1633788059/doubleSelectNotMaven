<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String studentId=(String)request.getAttribute("studentId");
String studentSno=(String)request.getAttribute("studentSno");
String role=(String)request.getAttribute("role");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
	<link rel="stylesheet" href="css/Form.css" type="text/css" />
	<link rel="stylesheet" href="css/Form.css" type="text/css" />
	  <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/util/all_easyui.js"></script></head>
	<link href="cropper/cropper.min.css" rel="stylesheet">
    <link href="sitelogo/sitelogo.css" rel="stylesheet">
    <script src="cropper/cropper.min.js"></script>
	<style>
		.textbox{
			height:20px;
			margin:0;
			padding:0 2px;
			box-sizing:content-box;
		}
		table {
				margin: 0 auto;
				border: 1px solid #89BFA7;
				border-collapse: collapse;
				border-spacing: 0;
				empty-cells: show;
				width:760px;
			}
			
			td {
				text-align: left;
				border: 1px solid #89BFA7;
				font-size: 9pt;
				padding-left: 2px;
				padding-right: 2px;
				line-height:5mm;
			}
			
			thead td, tfoot td {
				text-align: center;
				background-color:#BFE5D5;
				line-height:3mm;
			}
	</style>
 	</head>
		<script type="text/javascript">
		    var url_student;
			function showMessage(){
				var studentId=$("#studentId").val();
				var sno=$("#sno").val();
				var role=$("#role").val();
				if(role=='null'){
					$("#crop-avatar").hide();
				}
				if("null"==sno&&null!=studentId){
					url_student="student/studentmessage/searchStudent?studentId="+studentId;
				}
				if(null!=sno&&"null"==studentId){
					url_student="student/studentmessage/searchStudentBysno?studentSno="+sno;
				}
				$.ajax({
					url:url_student,
					type:"get" ,
					dataType:"json",
					success:function(data){
						if(data.success==true){
							$.each(data.obj, function(i, item) {
								$("#studentsno").html(item.studentsno);
								$("#studentname").html(item.studentname);
								$("#studentmajor").html(item.studentmajor);
								$("#finaldegree").html(item.finaldegree);
								$("#graduation").html(item.graduation);
								$("#time").html(item.time);
								$("#studentsex").html(item.studentsex);
								$("#expertise").html(item.expertise);
							});
						}else{
							alert(data.msg) ;
						}
					}
				}) ;
				$.ajax({
						url:"image/imagemessage/searchImage",
						type:"post",
						dataType:"json",
						data:{sno:sno},
						success:function(data){
							if(data.success==true){
								$.each(data.obj, function(i, item) {
									var imagePhoto="http://localhost:90/"+item.imagepath;
									$("#photo").attr("src",imagePhoto);
								});
							}else{
								$("#photo").attr("src","images/man.jpg.png");
							}
						}
				}) ;
			
			}
			
		</script>
	</head>
	<input type="hidden" id="studentId" value="<%=studentId %>"/>
	<input type="hidden" id="role" value="<%=role %>"/>
	<input type="hidden" id="sno" value="<%=studentSno %>"/>
	<body onload="showMessage()">
		<div style="width: 100%;height: 100%;overflow-y:scroll;">
			<table border="1" cellpadding="0" cellspacing="0" align="center" style="border:none;">
				<tr>
					<td style="border:none;padding:0px;margin:0px;">
						<div id="divTitle">基本信息</div>
						<div id="divTip"></div>
						<div id="divButton">
							<input type="hidden" id="menucode_current" name="menucode_current" />
							<input type="hidden" id="btnAdd" value="呵呵" class="button" onclick="return DoSubmit(this);" />
							<!-- <input type="button" id="btnUpload" value="上传照片" class="button" onclick="doUploadPhoto();" /> -->
							<div id="crop-avatar">
            						<div class="avatar-view" style="width:60px;height:25px">
            							图片上传
            						</div>
            				</div>
						</div>						
						<div id="divLine"></div>
					</td>
				</tr>
			</table>
			<table border="1" cellpadding="0" cellspacing="0" align="center">
				<thead>
					<tr>
						<td colspan="6">
							&nbsp;
						</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width:17%;">
							<div align="center">
								学&nbsp;&nbsp;&nbsp;&nbsp;号
							</div>
						</td>
						<td id="studentsno" style="width:16%;"></td>
						<td style="width:17%;">
							<div align="center">
								姓&nbsp;&nbsp;&nbsp;&nbsp;名
							</div>
						</td>
						<td id="studentname" colspan="2">
							&nbsp;
						</td>
						<td rowspan="6" style="text-align:center;overflow:hidden;vertical-align: middle;">
							<div style="width:90px; height:105px; border:1px solid #B3B5B4;">
								<img id="photo" name="photo" width="90px" height="105px" src="images/man.jpg.png" />
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								曾用名
							</div>
						</td>
						<td id="cym">
							&nbsp;
						</td>
						<td>
							<div align="center">
								姓名拼音(英文名)
							</div>
						</td>
						<td id="ywm" colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								性&nbsp;&nbsp;&nbsp;&nbsp;别
							</div>
						</td>
						<td id="studentsex">
							&nbsp;
						</td>
						<td>
							<div align="center">
								身份证号
							</div>
						</td>
						<td id="sfzh" colspan="2"></td>
					</tr>
					<tr>
						<td>
							<div align="center">
								出生日期
							</div>
						</td>
						<td id="csrq">
							&nbsp;
						</td>
						<td>
							<div align="center">
								民&nbsp;&nbsp;&nbsp;&nbsp;族
							</div>
						</td>
						<td id="mz" colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								籍&nbsp;&nbsp;&nbsp;&nbsp;贯
							</div>
						</td>
						<td id="jg">
							&nbsp;
						</td>
						<td>
							<div align="center">
								政治面貌
							</div>
						</td>
						<td id="zzmm" colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								入团时间
							</div>
						</td>
						<td id="rtsj">
							&nbsp;
						</td>
						<td>
							<div align="center">
								出生地
							</div>
						</td>
						<td id="csd" colspan="2">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								专&nbsp;&nbsp;&nbsp;&nbsp;业
							</div>
						</td>
						<td id="studentmajor">
							&nbsp;
						</td>
						<td>
							<div align="center">
								最后毕业学校
							</div>
						</td>
						<td id="graduation" style="width:17%;">
							&nbsp;
						</td>
						<td style="width:16%;">
							<div align="center">
								最后学历
							</div>
						</td>
						<td id="finaldegree" style="width:17%;">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								户籍类别
							</div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="center">
								宿舍号
							</div>
						</td>
						<td id="ss_mc" style="width:17%;">
							&nbsp;
						</td>
						<td style="width:16%;">
							<div align="center">
								宿舍电话
							</div>
						</td>
						<td id="ssdh" style="width:17%;">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								港澳台代码
							</div>
						</td>
						<td id="gat"></td>
						<td>
							<div align="center">
								联系人
							</div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="center">
								邮政编码
							</div>
						</td>
						<td id="yzbm">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								档案行号
							</div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="center">
								联系地址
							</div>
						</td>
						<td id="txdz" colspan="3">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								档案页号
							</div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="center">
								联系电话
							</div>
						</td>
						<td id="dh">
							&nbsp;
						</td>
						<td>
							<div align="center">
								专业特长
							</div>
						</td>
						<td id="expertise">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								招生季节
							</div>
						</td>
						<td id="zsjj">
							&nbsp;
						</td>
						<td>
							<div align="center">
								电子邮箱
							</div>
						</td>
						<td id="dzyx">
							&nbsp;
						</td>
						<td>
							<div align="center">
								毕业时间
							</div>
						</td>
						<td id="time">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								准考证号
							</div>
						</td>
						<td id="gkzkzh">
							&nbsp;
						</td>
						<td>
							<div align="center">
								考生号
							</div>
						</td>
						<td id="gkksh">
							&nbsp;
						</td>
						<td>
							<div align="center">
								考生类别
							</div>
						</td>
						<td id="kslb">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								健康状况
							</div>
						</td>
						<td id="jkzk">
							&nbsp;
						</td>
						<td>
							<div align="center">
								考生特征
							</div>
						</td>
						<td colspan="3">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								入学文化程度
							</div>
						</td>
						<td id="whcd">
							&nbsp;
						</td>
						<td>
							<div align="center">
							生源省份
							</div>
						</td>
						<td colspan="3">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								毕业类别
							</div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="center">
								生源地区
							</div>
						</td>
						<td id="syd" colspan="3">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								科类代码
							</div>
						</td>
						<td>
							&nbsp;
						</td>
	
						<td>
							<div align="center">
								毕业中学
							</div>
						</td>
						<td id="sydw" colspan="3">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								入学成绩
							</div>
						</td>
						<td id="whcj">
							&nbsp;
						</td>
						<td>
							<div align="center">
								获知学校信息途径
							</div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="center">
								特&nbsp;&nbsp;&nbsp;&nbsp;长
							</div>
						</td>
						<td id="kstc">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								入学时间
							</div>
						</td>
						<td id="bdtime">
							&nbsp;
						</td>
						<td>
							<div align="center">
								录取专业
							</div>
						</td>
						<td id="lqzy">
							&nbsp;
						</td>
						<td>
							<div align="center">
								学&nbsp;&nbsp;&nbsp;&nbsp;制
							</div>
						</td>
						<td id="xz">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								入学年份
							</div>
						</td>
						<td id="rxnj">
							&nbsp;
						</td>
						<td>
							<div align="center">
								入学方式
							</div>
						</td>
						<td id="rxfs">
							&nbsp;
						</td>
						<td>
							<div align="center">
								学习形式
							</div>
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								培养类别
							</div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="center">
								培养对象
							</div>
						</td>
						<td id="pydx">
							&nbsp;
						</td>
						<td>
							<div align="center">
								培养层次
							</div>
						</td>
						<td id="pycc">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								办学类别
							</div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="center">
								办学形式
							</div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="center">
								学习年限
							</div>
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								其他办学形式
							</div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="center">
								主修外语语种
							</div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="center">
								主修外语级别
							</div>
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								院(系)/部
							</div>
						</td>
						<td id="yxb">
							&nbsp;
						</td>
						<td>
							<div align="center">
								行政班级
							</div>
						</td>
						<td id="bjmc">
							&nbsp;
						</td>
						<td>
							<div align="center">
								辅导员
							</div>
						</td>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								主修计算机能力等级
							</div>
						</td>
						<td>
							&nbsp;
						</td>
						<td>
							<div align="center">
								辅修专业
							</div>
						</td>
						<td id="fxzy" colspan="3">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								辅修年纪
							</div>
						</td>
						<td id="fxnj">
							&nbsp;
						</td>
						<td>
							<div align="center">
								备&nbsp;&nbsp;&nbsp;&nbsp;注
							</div>
						</td>
						<td id="p" colspan="3">
							&nbsp;
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6">
							&nbsp;
						</td>
					</tr>
				</tfoot>
			</table>
			<iframe style="display:none;" id="hidFrm" name="hidFrm"></iframe>
			<div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
			    <div class="modal-dialog modal-lg">
			        <div class="modal-content">
			            <form class="avatar-form" action="student/studentmessage/uploadImage" enctype="multipart/form-data" method="post">
			                <div class="modal-header">
			                    <button class="close" data-dismiss="modal" type="button">&times;</button>
			                    <h4 class="modal-title" id="avatar-modal-label">上传图片</h4>
			                </div>
			                <div class="modal-body">
			                    <div class="avatar-body">
			                        <div class="avatar-upload">
			                            <input class="avatar-src" name="avatar_src" type="hidden">
			                            <input class="avatar-data" name="avatar_data" type="hidden">
			                            <label for="avatarInput">图片上传</label>
			                            <input class="avatar-input" id="avatarInput" name="avatar_file" type="file"></div>
			                        <div class="row">
			                            <div class="col-md-9">
			                                <div class="avatar-wrapper"></div>
			                            </div>
			                            <div class="col-md-3">
			                                <div class="avatar-preview preview-lg"></div>
			                                <div class="avatar-preview preview-md"></div>
			                                <div class="avatar-preview preview-sm"></div>
			                            </div>
			                        </div>
			                        <div class="row avatar-btns">
			                            <div class="col-md-9"></div>
			                            <div class="col-md-3">
			                                <button class="btn btn-success btn-block avatar-save" type="submit"><i class="fa fa-save"></i> 保存修改</button>
			                            </div>
			                        </div>
			                    </div>
			                </div>
			            </form>
			        </div>
			    </div>
			</div>
			<div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
					</div>
		</div>
		  <script src="sitelogo/sitelogo.js"></script>
	</body>
</html>