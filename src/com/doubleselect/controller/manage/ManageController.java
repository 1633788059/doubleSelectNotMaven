package com.doubleselect.controller.manage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doubleselect.controller.BaseController;
import com.doubleselect.model.manage.vo.Manage;
import com.doubleselect.model.vo.Json;
import com.doubleselect.service.manage.ManageService;

@Controller
@RequestMapping("/manage/managemessage/")
public class ManageController extends BaseController{
	
	@Autowired
	private ManageService manageService;
	
	@RequestMapping("open")
	public String open(){
		return "manage/managemessage/list";
	}


	@RequestMapping("add")
	@ResponseBody
	public Json add(HttpServletRequest request){
		Manage t=new Manage();
		String starttime=request.getParameter("starttime");
		String value=request.getParameter("value");
		String note=request.getParameter("note");
		String endtime=request.getParameter("endtime");
		//System.out.println("starttime"+starttime);
		//System.out.println("endtime"+endtime);
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		Json json = new Json();
		try {
			Date date =formatter.parse(starttime);
			Date date1 =formatter.parse(endtime);
			long st=date.getTime()/1000;
			long et=date1.getTime()/1000;
			//System.out.println("st"+st);
			//System.out.println("et"+et);
			t.setValue(value);
			t.setNote(note);
			t.setStarttime(st);
			t.setEndtime(et);
			this.manageService.save(t);
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
	public Json update(HttpServletRequest request){
		Json json = new Json();
		Manage t=new Manage();
		int id=Integer.parseInt(request.getParameter("id"));
		t.setId(id);
		String starttime=request.getParameter("starttime");
		String value=request.getParameter("value");
		String note=request.getParameter("note");
		String endtime=request.getParameter("endtime");
		try {
			SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			Date date =formatter.parse(starttime);
			Date date1 =formatter.parse(endtime);
			long st=date.getTime()/1000;
			long et=date1.getTime()/1000;
			t.setValue(value);
			t.setNote(note);
			t.setStarttime(st);
			t.setEndtime(et);
			this.manageService.update(t);
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
				
				this.manageService.delete(id);
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
	public Map<String,Object> search(int page,int rows,@RequestParam(value="starttime",required=false) String starttime,@RequestParam(value="endtime",required=false) String endtime,@RequestParam(value="value",required=false) String value) throws Exception{

		/**
		 * tomcat7使用时注释去掉
		 */
		if(starttime!=null){
		starttime= new String(starttime.getBytes("ISO8859-1"),"UTF-8");
		}	
		if(endtime!=null){
			 endtime= new String(endtime.getBytes("ISO8859-1"),"UTF-8");
		}	
		if(value!=null){
			 value = new String(value.getBytes("ISO8859-1"),"UTF-8");
		}
		//System.out.println("endtime"+endtime);
		SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		if(starttime!=null&&starttime!=""){
			Date date =formatter.parse(starttime);
			 starttime=date.getTime()/1000+"";
		}
		if(endtime!=null&&endtime!=""){
			Date date1 =formatter.parse(endtime);
			 endtime=date1.getTime()/1000+"";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		List<Manage> list = null;
		int total = 0;
		try {
			total = this.manageService.queryTotal(starttime,endtime,value);
			list = this.manageService.getByPage(page, rows,starttime,endtime,value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("rows",list);
		map.put("total", total);
		return map;
	}
	
	/**
	 * 根据学生学号查找学生所有信息
	 * @param ManageId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("searchManage")
	@ResponseBody
	public Json searchManage(String Id) throws Exception{
		Json json=new Json();
		List<Manage> list = null;
		
		try {
			list = this.manageService.getById(Id);
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
	
	@RequestMapping("searchManageByValue")
	@ResponseBody
	public Json searchManageBysno(String value) throws Exception{
		Json json=new Json();
		List<Manage> list = null;
		try {
			list = this.manageService.getByValue(value);
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
