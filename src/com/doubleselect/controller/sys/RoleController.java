package com.doubleselect.controller.sys;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.doubleselect.controller.BaseController;
import com.doubleselect.model.sys.vo.Role;
import com.doubleselect.model.sys.vo.User;
import com.doubleselect.model.vo.Json;
import com.doubleselect.service.sys.IAuthService;
import com.doubleselect.service.sys.IRoleService;

/**
 * 
 * 类描述：角色管理控制器
 * 类名称：RoleController
 * @version 1.0
 */
@Controller
@RequestMapping("/sys/roleController")
public class RoleController extends BaseController {

	@Autowired
	private IAuthService authService;
	
	@Autowired
	private IRoleService roleService;

	/**
	 * 
	 * 方法描述:进入角色管理页面
	 * 方法名：open
	 * @return
	 * 返回值：String
	 */
	@RequestMapping("open")
	public String open(){

		return "sys/role/list";
	}
	
	/**
	 * 
	 * 方法描述：跳转经过服务器端生成表单校验值
	 * 方法名：preAdd
	 * @return
	 * 返回值：String
	 */
	@RequestMapping("preAdd")
	public String preAdd(){
		
		return "sys/role/add";
	}
	
	/**
	 * 
	 * 方法描述：添加角色
	 * 方法名：add
	 * @param role
	 * @param response
	 * 返回值：void
	 */
	@RequestMapping("add")
	@ResponseBody
	public void add(Role role,HttpServletResponse response){
		Json json = new Json();
		try {
			if(roleService.add(role)){
				json.setMsg("添加成攻");
				json.setSuccess(true);
			}
		} catch (Exception e) {
			json.setMsg("添加失败");
			json.setSuccess(false);
			e.printStackTrace();
		}
		String jsonString = JSON.toJSONString(json);
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null; 
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (out != null) {
			out.print(jsonString);
			out.flush();
			out.close();
		}	
	}
	
	/**
	 * 
	 * 方法描述：单个或者批量删除
	 * 方法名：delete
	 * @param id
	 * @param session
	 * @return
	 * 返回值：Json
	 */
	@RequestMapping("delete")
	@ResponseBody
	public Json delete(int[] ids,HttpSession session){
		Json json = new Json();
		User user = (User) session.getAttribute("user");
		if(roleService.delete(ids)){
			json.setMsg("删除成功");
			json.setSuccess(true);
		}else{
			json.setMsg("删除成功");
			json.setSuccess(true);
		}
		return json;
	}
	
	
	
	/**
	 * 
	 * 方法描述：根据角色名查询
	 * 方法名：search
	 * @param page
	 * @param rows
	 * @param name
	 * @return
	 * 返回值：Map<String,Object>
	 */
	@RequestMapping("search")
	@ResponseBody
	public Map<String, Object> search(int page, int rows, String name) {
		long total = 0;
		List<Role> roleList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			total = roleService.queryTotal(name);
			roleList = roleService.query(page, rows, name);
			map.put("total", total);
			map.put("rows", roleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 
	 * 方法描述：跳转到修改角色页面
	 * 方法名：preEdit
	 * @param model
	 * @param id
	 * @return
	 * 返回值：String
	 */
	@RequestMapping("preEdit")
	public String preEdit(Model model, int id){
		try {

			model.addAttribute("role", roleService.queryById(id));		
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		return "sys/role/edit";
	}
	
	@RequestMapping("update")
	public void update(Role role, HttpServletResponse response){
		Json json = new Json();
		roleService.update(role);
		json.setMsg("更新成功！");
		json.setSuccess(true);
		String jsonString = JSON.toJSONString(json);
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null; 
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (out != null) {
			out.print(jsonString);
			out.flush();
			out.close();
		}			
	}
	
	/**
	 * 
	 * 方法描述：打开权限菜单
	 * 方法名：openMenu
	 * @param model
	 * @param rid
	 * @return
	 * @throws Exception
	 * 返回值：String
	 */
	@RequestMapping("openMenu")
	public String openMenu(Model model,int rid) throws Exception {
		
		model.addAttribute("rid", rid);
		return "sys/role/addmenu";
	}
	
	@RequestMapping("queryTreeMenu")
	public void queryTreeMenu(int rid, HttpServletResponse response) throws Exception {
		
		String jsonString = authService.queryTreeMenu(rid);
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null; 
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (out != null) {
			out.print(jsonString);
			out.flush();
			out.close();
		}	
	}
	@RequestMapping("updateR_a")
	public void updateR_a(int rid, String oaids, String aids, HttpServletResponse response){
		
		Json object = new Json();	
	
		roleService.updateAuth(rid, oaids, aids);
		object.setSuccess(true);
		object.setMsg("添加成功！");
			
		
		String jsonString = JSON.toJSONString(object);
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null; 
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (out != null) {
			out.print(jsonString);
			out.flush();
			out.close();
		}	
	}
}
