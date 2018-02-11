package com.doubleselect.controller.teacher;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.doubleselect.controller.BaseController;
import com.doubleselect.model.image.vo.Image;
import com.doubleselect.model.student.Tstudent;
import com.doubleselect.model.student.vo.Student;
import com.doubleselect.model.sys.vo.User;
import com.doubleselect.model.teacher.Tteacher;
import com.doubleselect.model.teacher.vo.Teacher;
import com.doubleselect.model.vo.Json;
import com.doubleselect.service.image.ImageService;
import com.doubleselect.service.teacher.TeacherService;
import com.doubleselect.util.Commer;
import com.doubleselect.util.OperateImage;
import com.doubleselect.util.excel.ExcelUtil;
import com.doubleselect.util.excel.ResponseUtil;

@Controller
@RequestMapping("/teacher/teachermessage/")
public class teacherController extends BaseController{
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping("open")
	public String open(){
		return "teacher/teachermessage/list";
	}

	/**
	 * 获取teacher的详细信息
	 * @return
	 */
	@RequestMapping("teachermessage")
	public String teachermessage(HttpServletRequest request,String teacherId,String teacherSno,String role){
		//System.out.println(role);
		if(null!=role){
			request.setAttribute("role", role);
		}
		if(null!=teacherSno){
			request.setAttribute("teacherSno", teacherSno);
		}
		if(null!=teacherId){
			request.setAttribute("teacherId", teacherId);
		}
		return "teacher/teachermessage/teachermessage";
	}
	
	
	@RequestMapping("updateinformation")
	public String updateinfomation(HttpServletRequest request,String teacherSno,Model model){
		List<Teacher> teachers=this.teacherService.getByTeacherSno(teacherSno);
		Teacher teacher=(Teacher)teachers.get(0);
		
		if(null!=teacherSno){
			request.setAttribute("teacherSno", teacherSno);
		}
		model.addAttribute("teacher",teacher);
		return "teacher/teachermessage/updateinformation";
	}
	
	
	
	 @RequestMapping(value = "/uploadImage")
	    public @ResponseBody
	    Map uploadImage(@RequestParam MultipartFile avatar_file, HttpServletRequest request,HttpSession session) {

		 	/**
		 	 * 定义图片名称
		 	 */
		 	String temp = null ;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS") ;
			Date date = new Date() ;
			temp = sdf.format(date) ;
		 
			String fileName = temp + "." + avatar_file.getOriginalFilename().substring(avatar_file.getOriginalFilename().lastIndexOf(".")+1);         //文件重命名                 
			
	        java.util.Random random = new java.util.Random();// 定义随机类
	        int result = random.nextInt(1);
	        //不写/的话路径将会报错
	        //String path = request.getSession().getServletContext().getRealPath("upload");
	        String path = request.getSession().getServletContext().getRealPath("/upload");
	       
	        User user = (User) session.getAttribute("user");
	        
	        File targetFile = new File(path, fileName);
	        File pathImage = new File(Commer.nginxFileName+Commer.nginxTeacherName+user.getName()+"\\"+fileName);
	        
	        if(!targetFile.exists()){
	            targetFile.mkdirs();
	        }
	        if(!pathImage.exists()){
	        	pathImage.mkdirs();
	        }
	        try {
	        	
	            //保存文件
	            avatar_file.transferTo(targetFile);
	            
	            //裁剪图片
	            JSONObject object = JSON.parseObject(request.getParameter("avatar_data"));
	            OperateImage o = new OperateImage((int) Float.parseFloat(object.get("x").toString()), (int) Float.parseFloat(object.get("y").toString()), (int) Float.parseFloat(object.get("width").toString()), (int) Float.parseFloat(object.get("height").toString()));
	            o.setSrcpath(path+"/"+fileName);//输入图片地址
	            //System.out.println(path+"/"+fileName);
	            o.setSubpath(Commer.nginxFileName+Commer.nginxTeacherName+user.getName()+"\\"+fileName);//输出图片地址
	            //System.out.println("result"+result);
	            o.cut();
	            List<Image> list=this.imageService.getBysno(user.getName());
	        	if(list.size()>0){
	        		Image image=list.get(0);
	        		File file=new File(Commer.nginxFileName+image.getImagepath());
	        	
	        		if(file.exists() && file.isFile()){
	        		    file.delete();
	        		}
	        		image.setImagename(fileName);
		        	image.setImagepath(Commer.nginxTeacherName+user.getName()+"\\"+fileName);
		        	image.setSno(user.getName());
		        	this.imageService.update(image);
	        	}else{
	        		Image image=new Image();
		        	image.setImagename(fileName);
		        	image.setImagepath(Commer.nginxTeacherName+user.getName()+"\\"+fileName);
		        	image.setSno(user.getName());
		            this.imageService.save(image);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        Map map = new HashMap();
	        map.put("result", true);
	        /*nginx地址*/
	        map.put("message", "http://localhost:90/"+Commer.nginxTeacherName+user.getName()+"\\"+fileName);
	        return map;
	    }
	/**
	 * 根据教师学号的详细信息
	 * @return
	 */
	@RequestMapping("xinxi")
	public String teacherxinxi(HttpServletRequest request,HttpSession session){
		User user = (User) session.getAttribute("user");
		request.setAttribute("teacherSno", user.getName());
		return "teacher/teachermessage/information";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Json add(Teacher t,HttpServletRequest request){
		Json json = new Json();
		try {
			this.teacherService.save(t);
			json.setSuccess(true);
			json.setMsg("添加成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("添加失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}
		
		return json;
	}
	
	
	@RequestMapping("update")
	@ResponseBody
	public Json update(Teacher t,int id){
		Json json = new Json();
		t.setId(id);
		try {
			this.teacherService.update(t);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("修改失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}
		
		return json;
	}
	
	
	@RequestMapping("delete")
	@ResponseBody
	public Json delete(int[] ids){
		Json json = new Json();
		try {
			for(int id:ids){
				this.teacherService.delete(id);
			}
			json.setSuccess(true);
			json.setMsg("删除成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("删除失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}
		
		return json;
	}
	
	@RequestMapping("export")
	@ResponseBody
	public String export(HttpServletResponse response) throws Exception{
		Workbook wb = new HSSFWorkbook(); // 定义一个工作簿
		String headers[] = { "学号", "姓名", "性别", "专业","最后学历","指导最少个数","指导最多个数"}; // 表的字段
		List<Tteacher>  list = null;
		list = this.teacherService.findAll();
		ExcelUtil.fillExcelDataByteacher(list, wb, headers);
		ResponseUtil.export(response, wb, "导出excel.xls"); // 将excel导出
		return null;
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public Json upload(@RequestParam(value = "file", required = true) MultipartFile file,HttpServletRequest request) throws Exception{
		Json json=new Json();
		//POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
		POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet hssfSheet = wb.getSheetAt(0); // 获取第一个Sheet页
		//System.out.println("1");
		if (hssfSheet != null) {
			//System.out.println("2");
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					json.setSuccess(false);
					json.setMsg("此文件不存在数据");
				}else{
					////System.out.println(ExcelUtil.formatCell(hssfRow.getCell(4)));
					//System.out.println("学号"+(ExcelUtil.formatCell(hssfRow.getCell(0)))+"");
				
					List<Teacher> list=this.teacherService.getByTeacherSno((ExcelUtil.formatCell(hssfRow.getCell(0)))+"");
					if(list.size()>0){
						//System.out.println("3");
					}else {
						////System.out.println(list.get(0).getId());
						////System.out.println(list2.get(0).getId());
						//Tkaihuhjbxx tkaihuhjbxx=list.get(0);
						//String headers[] = { "学号", "姓名", "性别", "专业","最后学历","毕业学校","专业特长","毕业时间"}; 
						try{
								Teacher teacher=new Teacher();
								teacher.setTeachersno(ExcelUtil.formatCell(hssfRow.getCell(0)));
								teacher.setTeachername(ExcelUtil.formatCell(hssfRow.getCell(1)));
								teacher.setTeachersex(ExcelUtil.formatCell(hssfRow.getCell(2)));
								teacher.setTeachermajor(ExcelUtil.formatCell(hssfRow.getCell(3)));
								teacher.setDegree(ExcelUtil.formatCell(hssfRow.getCell(4)));
								teacher.setGuidemin(Integer.parseInt(ExcelUtil.formatCell(hssfRow.getCell(5))));
								teacher.setGuidemax(Integer.parseInt(ExcelUtil.formatCell(hssfRow.getCell(6))));
								this.teacherService.save(teacher);
								json.setSuccess(true);
								json.setMsg("添加成功");
							}catch(Exception e){
								json.setSuccess(false);
								json.setMsg("请检查导入数据格式是否正确");
							}
						}
					}
				}
			
			}
		////System.out.println(json);
		return json;
	}
	
	@RequestMapping("search")
	@ResponseBody
	public Map<String,Object> search(int page,int rows,@RequestParam(value="teachersno",required=false) String teachersno,@RequestParam(value="teachername",required=false) String teachername,@RequestParam(value="teachersex",required=false) String teachersex,String teacherId) throws Exception{
	
		/**
		 * tomcat7时候注释去掉
		 */
			if(teachersno!=null){
		 teachersno= new String(teachersno.getBytes("ISO8859-1"),"UTF-8");
		}	
		if(teachername!=null){
			 teachername= new String(teachername.getBytes("ISO8859-1"),"UTF-8");
		}	
		if(teachersex!=null){
			 teachersex = new String(teachersex.getBytes("ISO8859-1"),"UTF-8");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		List<Teacher> list = null;
		int total = 0;
		try {
			total = this.teacherService.queryTotal(teachersno,teachername,teachersex,teacherId);
			list = this.teacherService.getByPage(page, rows, teachersno,teachername,teachersex,teacherId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("rows",list);
		map.put("total", total);
		return map;
	}
	
	@RequestMapping("searchteacher")
	@ResponseBody
	public Json searchteacher(String teacherId) throws Exception{
		Json json=new Json();
		List<Teacher> list = null;
		
		try {
			list = this.teacherService.getByteacherId(teacherId);
			json.setObj(list);
			json.setSuccess(true);
			json.setMsg("获取信息成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("获取信息失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 根据教师sno获取教师信息
	 * @param teachcerSno
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("searchTeacherBysno")
	@ResponseBody
	public Json searchTeacherBysno(String teacherSno) throws Exception{
		Json json=new Json();
		List<Teacher> list = null;
		try {
			list = this.teacherService.getByTeacherSno(teacherSno);
			json.setObj(list);
			json.setSuccess(true);
			json.setMsg("获取信息成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("获取信息失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}
		return json;
	}
}
