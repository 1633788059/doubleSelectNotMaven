package com.doubleselect.controller.student;

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
import com.doubleselect.model.vo.Json;
import com.doubleselect.service.image.ImageService;
import com.doubleselect.service.manage.ManageService;
import com.doubleselect.service.student.StudentService;
import com.doubleselect.util.Commer;
import com.doubleselect.util.OperateImage;
import com.doubleselect.util.excel.ExcelUtil;
import com.doubleselect.util.excel.ResponseUtil;

@Controller
@RequestMapping("/student/studentmessage/")
public class studentController extends BaseController{
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ManageService manageService;
	
	@Autowired
	private ImageService imageService;
	@RequestMapping("open")
	public String open(){
		return "student/studentmessage/list";
	}

	/**
	 * 根据studentId获取student的详细信息
	 * @return
	 */
	@RequestMapping("studentmessage")
	public String studentmessage(HttpServletRequest request,String studentId,String studentSno,String role){
		//System.out.println(studentSno+".......///");
		if(null!=role){
			request.setAttribute("role", role);
		}
		if(null!=studentSno){
			request.setAttribute("studentSno", studentSno);
		}
		if(null!=studentId){
			request.setAttribute("studentId",studentId);
		}
		return "student/studentmessage/studentmessage";
	}
	
	@RequestMapping("updateinformation")
	public String updateinfomation(HttpServletRequest request,String studentSno,Model model){
		List<Student> students=this.studentService.getByStudentSno(studentSno);
		Student student=(Student)students.get(0);
		
		if(null!=studentSno){
			request.setAttribute("studentSno", studentSno);
		}
		model.addAttribute("student",student);
		return "student/studentmessage/updateinformation";
	}
	
	@RequestMapping("xinxi")
	public String information(HttpServletRequest request,HttpSession session){
		User user = (User) session.getAttribute("user");
		request.setAttribute("studentSno", user.getName());
		return "student/studentmessage/information";
	}
	@RequestMapping("add")
	@ResponseBody
	public Json add(Student t,HttpServletRequest request){
		String time=request.getParameter("time");
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		Json json = new Json();
		try {
			Date date =formatter.parse(time);
			t.setTime(date);
			this.studentService.save(t);
			json.setSuccess(true);
			json.setMsg("添加成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("添加失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}
		
		return json;
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
	       
	        System.out.println("path:"+path);
	        
	        User user = (User) session.getAttribute("user");
	        
	        File targetFile = new File(path, fileName);
	        File pathImage = new File(Commer.nginxFileName+Commer.nginxStudentName+user.getName()+"\\"+fileName);
	        
	        System.out.println("pathImage"+pathImage.getAbsolutePath());
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
	            
	            o.setSubpath(Commer.nginxFileName+Commer.nginxStudentName+user.getName()+"\\"+fileName);//输出图片地址
	            
	            o.cut();
	            List<Image> list=this.imageService.getBysno(user.getName());
	        	if(list.size()>0){
	        		//System.out.println("到达1");
	        		Image image=list.get(0);
	        		File file=new File(Commer.nginxFileName+image.getImagepath());
	        		
	        		if(file.exists() && file.isFile()){
	        		    file.delete();
	        		}
	        		image.setImagename(fileName);
		        	image.setImagepath(Commer.nginxStudentName+user.getName()+"\\"+fileName);
		        	image.setSno(user.getName());
		        	this.imageService.update(image);
	        	}else{
	        		//System.out.println("到达2");
	        		Image image=new Image();
		        	image.setImagename(fileName);
		        	image.setImagepath(Commer.nginxStudentName+user.getName()+"\\"+fileName);
		        	image.setSno(user.getName());
		            this.imageService.save(image);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        Map map = new HashMap();
	        map.put("result", true);
	        /*nginx地址*/
	        map.put("message", "http://localhost:90/"+Commer.nginxStudentName+user.getName()+"\\"+fileName);
	        return map;
	    }
	
	
	@RequestMapping("update")
	@ResponseBody
	public Json update(Student t,int id,HttpServletRequest request){
		Json json = new Json();
		t.setId(id);
		String time=request.getParameter("time");
		try {
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			Date date =formatter.parse(time);
			t.setTime(date);
			this.studentService.update(t);
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
	public Json delete(int []ids){
		Json json = new Json();
		try {
			for(int id:ids){
				
				this.studentService.delete(id);
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
		String headers[] = { "学号", "姓名", "性别", "专业","最后学历","毕业学校","专业特长","毕业时间"}; // 表的字段
		List<Tstudent>  list = null;
		list = this.studentService.findAll();
		ExcelUtil.fillExcelData(list, wb, headers);
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
				
					List<Student> list=this.studentService.getByStudentSno((ExcelUtil.formatCell(hssfRow.getCell(0)))+"");
					if(list.size()>0){
						//System.out.println("3");
					}else {
						////System.out.println(list.get(0).getId());
						////System.out.println(list2.get(0).getId());
						//Tkaihuhjbxx tkaihuhjbxx=list.get(0);
						//String headers[] = { "学号", "姓名", "性别", "专业","最后学历","毕业学校","专业特长","毕业时间"}; 
						try{
								Student student=new Student();
								student.setStudentsno(ExcelUtil.formatCell(hssfRow.getCell(0)));
								student.setStudentname(ExcelUtil.formatCell(hssfRow.getCell(1)));
								student.setStudentsex(ExcelUtil.formatCell(hssfRow.getCell(2)));
								student.setStudentmajor(ExcelUtil.formatCell(hssfRow.getCell(3)));
								student.setFinaldegree(ExcelUtil.formatCell(hssfRow.getCell(4)));
								student.setGraduation(ExcelUtil.formatCell(hssfRow.getCell(5)));
								student.setExpertise(ExcelUtil.formatCell(hssfRow.getCell(6)));
								//System.out.println("date:"+ExcelUtil.formatCell(hssfRow.getCell(7)));
								SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
								Date date =formatter.parse(ExcelUtil.formatCell(hssfRow.getCell(7)));
								//System.out.println(date+"8888");
								student.setTime(date);
								this.studentService.save(student);
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
	//,produces="text/html;charset=UTF-8"解决返回中文乱码的问题
	@RequestMapping(value="search",produces="text/html;charset=UTF-8")
	@ResponseBody
	public Map<String,Object> search(int page,int rows,@RequestParam(value="studentsno",required=false) String studentsno,@RequestParam(value="studentname",required=false) String studentname,@RequestParam(value="studentsex",required=false) String studentsex,String studentId) throws Exception{

		/**
		 * tomcat7使用时注释去掉
		 */
		if(studentsno!=null){
		 studentsno= new String(studentsno.getBytes("ISO8859-1"),"UTF-8");
		}	
		if(studentname!=null){
			 studentname= new String(studentname.getBytes("ISO8859-1"),"UTF-8");
		}	
		if(studentsex!=null){
			 studentsex = new String(studentsex.getBytes("ISO8859-1"),"UTF-8");
		}
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<Student> list = null;
		int total = 0;
		try {
			total = this.studentService.queryTotal(studentsno,studentname,studentsex,studentId);
			list = this.studentService.getByPage(page, rows, studentsno,studentname,studentsex,studentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("rows",list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 根据学生学号查找学生所有信息
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("searchStudent")
	@ResponseBody
	public Json searchStudent(String studentId) throws Exception{
		Json json=new Json();
		List<Student> list = null;
		
		try {
			list = this.studentService.getByStudentId(studentId);
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
	
	@RequestMapping("searchStudentBysno")
	@ResponseBody
	public Json searchStudentBysno(String studentSno) throws Exception{
		Json json=new Json();
		List<Student> list = null;
		//System.out.println(studentSno);
		try {
			list = this.studentService.getByStudentSno(studentSno);
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
