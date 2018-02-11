package com.doubleselect.controller.match;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doubleselect.controller.BaseController;
import com.doubleselect.model.match.vo.Match;
import com.doubleselect.model.vo.Json;
import com.doubleselect.service.match.MatchService;
import com.doubleselect.service.teacher.TeacherService;

@Controller
@RequestMapping("/match/matchmessage/")
public class MatchController extends BaseController{
	
	@Autowired
	private MatchService matchService;
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("open")
	public String open(){
		return "match/matchmessage/list";
	}
	
	/**
	 * 添加匹配成功的学生和导师
	 * @param t
	 * @param request
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public Json add(Match t,HttpServletRequest request){
		Json json = new Json();
		try {
			this.matchService.save(t);
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
	public Json update(Match t,HttpServletRequest request){
		Json json = new Json();
		String row1=request.getParameter("row1");
		String matchold[]=row1.split(",");
		Match newmatch=new Match();
		newmatch.setStudentsno(matchold[0]);
		newmatch.setTeachersno(matchold[1]);
		try {
			this.matchService.save(t);
			//System.out.println("heheehhehe1");
			this.matchService.delete(newmatch);
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
	 * 删除填写匹配的信息
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
		for(int i=0;i<idNum/2;i++){
			Match Match =new Match();
			Match.setStudentsno(idArray[0+i*2]);
			Match.setTeachersno(idArray[1+i*2]);
			this.matchService.delete(Match);
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
	public Map<String,Object> search(int page,int rows,@RequestParam(value="teachersno",required=false) String teachersno,@RequestParam(value="studentsno",required=false) String studentsno) throws Exception{
	
			/**
			 * 当用tomcat7运行的时候需要转化编码格式,tomcat8不需要转化格式
			 */
		
		 if(teachersno!=null){
		 teachersno= new String(teachersno.getBytes("ISO8859-1"),"UTF-8");
		}	
		if(studentsno!=null){
			 studentsno= new String(studentsno.getBytes("ISO8859-1"),"UTF-8");
		}	
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<Match> list = null;
		int total = 0;
		try {
			total = this.matchService.queryTotal(studentsno,teachersno);
			list = this.matchService.getByPage(page, rows,studentsno,teachersno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("rows",list);
		map.put("total", total);
		return map;
	}

}
