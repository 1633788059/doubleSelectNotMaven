package com.doubleselect.controller.volunteer;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doubleselect.controller.BaseController;
import com.doubleselect.model.manage.vo.Manage;
import com.doubleselect.model.match.vo.Match;
import com.doubleselect.model.sys.vo.User;
import com.doubleselect.model.teacher.vo.Teacher;
import com.doubleselect.model.vo.Json;
import com.doubleselect.model.vo.TeachersnoNum;
import com.doubleselect.model.volunteer.vo.Volunteer;
import com.doubleselect.service.manage.ManageService;
import com.doubleselect.service.match.MatchService;
import com.doubleselect.service.teacher.TeacherService;
import com.doubleselect.service.volunteer.VolunteerService;

@Controller
@RequestMapping("/volunteer/volunteermessage/")
public class volunteerController extends BaseController{
	private static String studentSelect="studentSelect";
	private static String studentUpdate="studentUpdate";
	private static String teacherSelect="teacherSelect";
	private static String teacherUpdate="teacherUpdate";
	
	@Autowired
	private VolunteerService volunteerService;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private MatchService matchService;
	
	@Autowired 
	private ManageService manageService;
	
	@RequestMapping("open")
	public String open(){
		return "volunteer/volunteermessage/list";
	}
	
	/**
	 * 学生选报志愿
	 * @return
	 */
	@RequestMapping("studentopen")
	public String studentopen(HttpServletRequest request,HttpSession session,Model model){
		User user = (User) session.getAttribute("user");
		Manage manage=new Manage();
		List<Manage> manageList=this.manageService.getByValue(studentSelect);
		if(manageList.size()==0){
			//System.out.println("该数据不存在");
		}else{
			manage=(Manage) manageList.get(0);
		}
		model.addAttribute("manage",manage);
		request.setAttribute("studentSno", user.getName());
		return "volunteer/volunteermessage/studentlist";
	}

	/**
	 * 老师选择学生为志愿
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("teacheraddvolunteer")
	@ResponseBody
	public Json teacheraddvolunteer(HttpServletRequest request,HttpSession session){
		Json json=new Json();
		User user = (User) session.getAttribute("user");
		String studentsnos=request.getParameter("ids");
		int oldnum=this.volunteerService.queryTotal(null, user.getName(), "4");
		//System.out.println(oldnum+"====");
		List<Teacher> list =this.teacherService.getByTeacherSno(user.getName());
		String []nums=studentsnos.split(",");
		if(list.get(0).getGuidemax()>=nums.length+oldnum){
			try {
					for(int i=0;i<nums.length;i++){
						Volunteer volunteer=new Volunteer();
						volunteer.setRank(4);
						volunteer.setTeachersno(user.getName());
						volunteer.setStudentsno(nums[i]);
						this.volunteerService.save(volunteer);
					}
				
					json.setSuccess(true);
					json.setMsg("添加成功！");
				} catch (Exception e) {
					json.setSuccess(false);
					json.setMsg("添加失败，请尝试刷新之后再操作！");
					e.printStackTrace();
				}
		}else{
			json.setSuccess(false);
			json.setMsg("你选择的学生人数大于你指导的人数");
		}
		return json;
	}
	
	/**
	 * 老师志愿修改
	 * @return
	 */
	@RequestMapping("teachermessageupdate")
	public String teachermessageupdate(HttpServletRequest request,HttpSession session,Model model){
		User user = (User) session.getAttribute("user");
		Manage manage=new Manage();
		List<Manage> manageList=this.manageService.getByValue(teacherUpdate);
		if(manageList.size()==0){
			//System.out.println("该数据不存在");
		}else{
			manage=(Manage) manageList.get(0);
		}
		model.addAttribute("manage",manage);
		request.setAttribute("teacherSno", user.getName());
		return "volunteer/volunteermessage/teachermessageupdate";
	}
	
	
	/**
	 * 学生志愿修改
	 * @return
	 */
	@RequestMapping("studentmessageupdate")
	public String studentmessageupdate(HttpServletRequest request,HttpSession session,Model model){
		User user = (User) session.getAttribute("user");
		request.setAttribute("studentSno", user.getName());
		Manage manage=new Manage();
		List<Manage> manageList=this.manageService.getByValue(studentUpdate);
		if(manageList.size()==0){
			//System.out.println("该数据不存在");
		}else{
			manage=(Manage) manageList.get(0);
		}
		model.addAttribute("manage",manage);
		return "volunteer/volunteermessage/studentmessageupdate";
	}
	
	
	/**
	 * 教师选报志愿
	 * @return
	 */
	@RequestMapping("teacheropen")
	public String teacheropen(HttpServletRequest request,HttpSession session,Model model){
		User user = (User) session.getAttribute("user");
		Manage manage=new Manage();
		List<Manage> manageList=this.manageService.getByValue(teacherSelect);
		if(manageList.size()==0){
			//System.out.println("该数据不存在");
		}else{
			manage=(Manage) manageList.get(0);
		}
		model.addAttribute("manage",manage);
		request.setAttribute("teacherSno", user.getName());
		return "volunteer/volunteermessage/teacherlist";
	}
	
	/**
	 * 教师查看自己被选择的学生的信息
	 * @return
	 */
	@RequestMapping("teacherselect")
	public String teacherselect(HttpServletRequest request,HttpSession session){
		User user = (User) session.getAttribute("user");
		request.setAttribute("teacherSno", user.getName());
		return "volunteer/volunteermessage/teacherselect";
	}
	
	/**
	 * 根据 teachersno获取teacher的详细信息
	 * @return
	 */
	@RequestMapping("teachermessage")
	public String teachermessage(HttpServletRequest request,String teacherId,String teacherSno){
		if(null!=teacherSno){
			request.setAttribute("teacherSno", teacherSno);
		}
		if(null!=teacherId){
			request.setAttribute("teacherId", teacherId);
		}
		return "teacher/teachermessage/teachermessage";
	}
	
	
	/**
	 * 根据studentSno获取student的详细信息
	 * @return
	 */
	@RequestMapping("studentmessage")
	public String studentmessage(HttpServletRequest request,String studentId,String studentSno){
		if(null!=studentSno){
			request.setAttribute("studentSno", studentSno);
		}
		if(null!=studentId){
			request.setAttribute("studentId",studentId);
		}
		return "student/studentmessage/studentmessage";
	}
	
	/**
	 * 查看个人选择的志愿
	 * @return
	 */
/*	@RequestMapping("xinxi")
	public String teacherxinxi(HttpServletRequest request,HttpSession session){
		User user = (User) session.getAttribute("user");
		request.setAttribute("teacherSno", user.getName());
		return "teacher/teachermessage/information";
	}*/
	
	/**
	 * 志愿信息得添加(管理员的权限)
	 * @param t
	 * @param request
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Json add(Volunteer t,HttpServletRequest request){
		Json json = new Json();
		try {
			this.volunteerService.save(t);
			json.setSuccess(true);
			json.setMsg("添加成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("添加失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}	
		return json;
	}
	
	/**
	 * 导师与研究生进行匹配
	 * @param t
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("marry")
	@ResponseBody
	public Json marry(){
		Json json = new Json();
		int teacherMatchNum=0;
		List<Volunteer> list=new ArrayList<Volunteer>();
		List<TeachersnoNum> listTeachersno=new ArrayList<TeachersnoNum>();
		
		/**
		 * 获取满足条件的教师的名单的集合
		 */
		try{
			listTeachersno=this.volunteerService.marryTeachersno();
			////System.out.println(list.size());
			for(int j=1;j<4;j++){
				//System.out.println("长度为"+listTeachersno.size());
				for(TeachersnoNum t2:listTeachersno){
					Teacher teacher=this.teacherService.getByTeacherSno(t2.getTeachersnoMarry()).get(0);
					/**
					 * 获取教师匹配成功的学生的名单
					 */
						list=this.volunteerService.marryByteachersnoAndrank(t2.getTeachersnoMarry(),j+"");
						//System.out.println(t2.getTeachersnoMarry()+","+j);
						/*for(Volunteer vo:list){
							//System.out.println(vo.getTeachersno()+","+vo.getStudentsno()+","+vo.getRank());
						}*/
						if(list.size()==0){
							continue;
						}
						teacherMatchNum=this.matchService.queryTotal(null, t2.getTeachersnoMarry());
						//System.out.println("teacherMatchNum:"+teacherMatchNum);
						if(teacher.getGuidemax()<=list.size()+teacherMatchNum){
							/*if((teacher.getGuidemax()-teacherMatchNum)<=0){
								break;
							}*/
							//System.out.println("总数小于等于满足条件的数");
							for(int i=0;i<(teacher.getGuidemax()-teacherMatchNum);i++){
								//System.out.println("名额超载");
								Volunteer vAdd=new Volunteer();
								vAdd=list.get(i);
								Match match=new Match();
								match.setStudentsno(vAdd.getStudentsno());
								match.setTeachersno(vAdd.getTeachersno());
								this.matchService.save(match);
								this.volunteerService.deleteBystudentsnoAndteachersno(vAdd.getStudentsno(),null);
								this.volunteerService.deleteBystudentsnoAndteachersno(null, vAdd.getTeachersno());
							}
						}else{
							for(Volunteer tAdd:list){
								Match match=new Match();
								match.setStudentsno(tAdd.getStudentsno());
								match.setTeachersno(tAdd.getTeachersno());
								this.matchService.save(match);
								this.volunteerService.deleteBystudentsnoAndteachersno(tAdd.getStudentsno(),null);
							}
						}
					}
				
			}
			json.setSuccess(true);
			json.setMsg("匹配成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("匹配失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}	
		return json;
	}
	
	/**
	 * 学生志愿选择老师
	 * @param request
	 * @param session
	 * @param teachersno1
	 * @param teachersno2
	 * @param teachersno3
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("studentadd")
	@ResponseBody
	public Json studentadd(HttpServletRequest request,HttpSession session,String teachersno1,String teachersno2,String teachersno3) throws UnsupportedEncodingException{
		Json json = new Json();
		User user = (User) session.getAttribute("user");
		
		/**
		 * tomocat7和tomocat8编码方式不同
		 */
		if(teachersno1!=null){
			 teachersno1= new String(teachersno1.getBytes("ISO8859-1"),"UTF-8");
		}	
		if(teachersno2!=null){
				 teachersno2= new String(teachersno2.getBytes("ISO8859-1"),"UTF-8");
		}	
		if(teachersno3!=null){
				 teachersno3 = new String(teachersno3.getBytes("ISO8859-1"),"UTF-8");
		}
		List<Volunteer> volunteer=new ArrayList<Volunteer>();
		if(!("请输入老师工号".equals(teachersno1)))
		{
			Volunteer t=new Volunteer();
			t.setRank(1);
			t.setStudentsno(user.getName());
			t.setTeachersno(teachersno1);
			volunteer.add(t);
		}
		if(!("请输入老师工号".equals(teachersno2)))
		{
			Volunteer t=new Volunteer();
			t.setRank(2);
			t.setStudentsno(user.getName());
			t.setTeachersno(teachersno2);
			volunteer.add(t);
		}
		if(!("请输入老师工号".equals(teachersno3)))
		{
			Volunteer t=new Volunteer();
			t.setRank(3);
			t.setStudentsno(user.getName());
			t.setTeachersno(teachersno3);
			volunteer.add(t);
		}
		try {
			for(Volunteer t1:volunteer){
			this.volunteerService.save(t1);
			}
			json.setSuccess(true);
			json.setMsg("添加成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("添加失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}	
		return json;
	}
	
	
	/**
	 * 此更新方法可以先删除再添加
	 * @param t
	 * @param request
	 * @return
	 */
	@RequestMapping(value="update")
	@ResponseBody
	public Json update(Volunteer t,HttpServletRequest request){
		Json json = new Json();
		String row1=request.getParameter("row1");
		String volunteerold[]=row1.split(",");
		Volunteer newvolunteer=new Volunteer();
		newvolunteer.setStudentsno(volunteerold[0]);
		newvolunteer.setTeachersno(volunteerold[1]);
		newvolunteer.setRank(Integer.parseInt(volunteerold[2]));
		try {
			this.volunteerService.save(t);
			//System.out.println("heheehhehe1");
			this.volunteerService.delete(newvolunteer);
			json.setSuccess(true);
			json.setMsg("修改成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("修改失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}
		return json;
	}
	
	
	/**
	 * 删除填写的志愿着的信息
	 * @param request
	 * @return
	 */
	@RequestMapping("test")
	@ResponseBody
	public Json test(HttpServletRequest request){

		Json json = new Json();
		try {
	
			this.volunteerService.deleteBystudentsnoAndteachersno("3",null);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("删除失败，请尝试刷新之后再操作！");
			e.printStackTrace();
		}
		
		return json;
	}
	
	/**
	 * 删除填写的志愿着的信息
	 * @param request
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Json delete(HttpServletRequest request){
		String ids=request.getParameter("ids");
		String []idArray=ids.split(",");
		Json json = new Json();
		try {
		int idNum=idArray.length;
		for(int i=0;i<idNum/3;i++){
			Volunteer volunteer =new Volunteer();
			volunteer.setStudentsno(idArray[0+i*3]);
			volunteer.setTeachersno(idArray[1+i*3]);
			volunteer.setRank(Integer.parseInt(idArray[2+i*3]));
			this.volunteerService.delete(volunteer);
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
	
	
	
	@RequestMapping("search")
	@ResponseBody
	public Map<String,Object> search(int page,int rows,@RequestParam(value="teachersno",required=false) String teachersno,@RequestParam(value="studentsno",required=false) String studentsno,@RequestParam(value="rank",required=false) String rank) throws Exception{
	
			/**
			 * 当用tomcat7运行的时候需要转化编码格式,tomcat8不需要转化格式
			 */
		
		 if(teachersno!=null){
		 teachersno= new String(teachersno.getBytes("ISO8859-1"),"UTF-8");
		}	
		if(studentsno!=null){
			 studentsno= new String(studentsno.getBytes("ISO8859-1"),"UTF-8");
		}	
		if(rank!=null){
			 rank = new String(rank.getBytes("ISO8859-1"),"UTF-8");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		List<Volunteer> list = null;
		int total = 0;
		try {
			total = this.volunteerService.queryTotal(studentsno,teachersno,rank);
			list = this.volunteerService.getByPage(page, rows,studentsno,teachersno,rank);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("rows",list);
		map.put("total", total);
		return map;
	}

}
