package com.doubleselect.controller.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.doubleselect.controller.BaseController;
import com.doubleselect.model.sys.vo.User;
import com.doubleselect.model.vo.Json;
import com.doubleselect.service.sys.IAuthService;
import com.doubleselect.service.sys.IRoleService;
import com.doubleselect.service.sys.IUserService;
import com.doubleselect.util.MD5;
import com.doubleselect.util.RandomValidateCode;
import com.doubleselect.util.UserCookieUtil;

/**
 * 
 * 类描述：用户控制器
 * 类名称：UserController
 * @version 1.0
 */
@Controller
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IAuthService authService;

	/**
	 * 
	 * 方法描述：打开用户管理
	 * 方法名：open
	 * @return
	 * 返回值：String
	 */
	@RequestMapping("/sys/userController/open")
	public String open(){

		return "sys/user/list";
	}
	
	/**
	 * 
	 * 方法描述：用于防止表单重复提交
	 * 方法名：preAdd
	 * @return
	 * 返回值：String
	 */
	@RequestMapping("/sys/userController/preAdd")
	public String preAdd(){
		
		return "sys/user/add";
	}
	
	/**
	 * 
	 * 方法描述：添加用户
	 * 方法名：add
	 * @param user
	 * @param response
	 * 返回值：void
	 */
	@RequestMapping("/sys/userController/add")
	public void add(User user,HttpServletResponse response){
		Json json = new Json();
		String Md5password=MD5.MD5(user.getPwd());
		user.setPwd(Md5password);
		try {
			userService.add(user);
			json.setMsg("添加成功");
			json.setSuccess(true);
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
	 * 方法描述：单个或者是删除
	 * 方法名：delete
	 * @param id
	 * @param session
	 * @return
	 * 返回值：Json
	 */
	@RequestMapping("/sys/userController/delete")
	@ResponseBody
	public Json delete(int[] ids,HttpSession session){
		Json json = new Json();
		User user = (User) session.getAttribute("user");
		try {
			userService.delete(ids,user);
			json.setMsg("删除成功");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除失败");
			json.setSuccess(true);
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 
	 * 方法描述:点击设置角色后跳转到相应的页面
	 * 方法名：setRole
	 * @param model
	 * @param uid
	 * @return
	 * 返回值：String
	 */
	@RequestMapping("/sys/userController/setRole")
	public String setRole(Model model, int uid){
		
		String roleText = roleService.curRole(uid);
		model.addAttribute("roleText", roleText);
		
		return "sys/user/setUser";
	}
	
	/**
	 * 
	 * 方法描述：设置角色后提交  根据用户id删除权限，然后在添加权限
	 * 方法名：updateRole
	 * @param id
	 * @param rid
	 * 返回值：void
	 */
	@RequestMapping("/sys/userController/updateRole")
	public void updateRole(int id,int[] rid,HttpServletResponse response){
		Json json = new Json();
		userService.updateRole(id,rid);
		
		json.setMsg("设置角色成功！");
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
	 * 方法描述：根据用户名查询用户 可以为空
	 * 方法名：search
	 * @param page
	 * @param rows
	 * @param name
	 * @return
	 * 返回值：Map<String,Object>
	 */
	@RequestMapping("/sys/userController/search")
	@ResponseBody
	public Map<String, Object> search(int page, int rows, String name) {
		long total = 0;
		List<User> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			total = userService.queryTotal(name);
			//逻辑放在dao层分页查询，如果出现异常，首先检查这个地方是否有问题
			list = userService.query(page, rows,name);
			map.put("total", total);
			map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 
	 * @param user
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/updatepass")
	@ResponseBody
	public Json updatepass(HttpSession session, HttpServletRequest request,String newpass) {
		Json json=new Json();
		User user = (User) session.getAttribute("user");
		String Md5password=MD5.MD5(newpass);
		user.setPwd(Md5password);
		try{ 
			this.userService.updatepass(user);
			json.setMsg("修改密码成功");
			json.setSuccess(true);
			json.setObj(user);
		}
		catch (Exception e) {
			json.setMsg("修改密码失败");
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 用户获取验证码
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/code")
	public void getImage(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	
	/**
	 * 
	 * 方法描述：登陆系统
	 * 方法名：login
	 * @param user
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 * 返回值：boolean
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Json login(User user, HttpSession session, HttpServletRequest request,Model model,String code, HttpServletResponse response) {
		Json json=new Json();
		
		String mima=user.getPwd();
		String remember=request.getParameter("remember");
		//System.out.println("remember:"+remember);
		String randomCode = session.getAttribute("RANDOMVALIDATECODEKEY").toString() ; //获取原验证码
		////System.out.println(code+"=======");
		////System.out.println(randomCode+"=======");
		if(randomCode.equals(code)){
			User us=userService.getPassword(user);
			
			//User u = userService.login(user);
			if (us != null) {
				String pwd=us.getPwd();
				////System.out.println(pwd);
				String rwpwd=MD5.MD5(pwd+MD5.MD5(randomCode));
				////System.out.println(rwpwd);
				if(mima.equals(rwpwd)){
					
					//shiro验证
					try{
						Subject subject=SecurityUtils.getSubject();
						UsernamePasswordToken token=new UsernamePasswordToken(user.getName(), us.getPwd());
						subject.login(token);
						token.clear();
						session.setAttribute("user", us);
						
						
						String url ="student/studentmessage/uploadImage";
						
						//权限校验。判断是否包含权限。
						//Subject subject1 = SecurityUtils.getSubject();
						//具体响应ShiroDbRealm。doGetAuthorizationInfo，判断是否包含此url的响应权限
						//基于权限的访问控制
					/*	boolean isPermitted = subject.isPermitted(url);
						if(isPermitted) {
							System.out.println("包含权限");
						}else{
							System.out.println("不包含权限");
						}*/
						
						
						if(remember.equals("true")){
							UserCookieUtil.saveCookie(us, response);
						}
						json.setSuccess(true) ;
						json.setMsg("正在登陆.....") ;
					}catch(Exception e){
						json.setSuccess(false) ;
						json.setMsg("账户或密码错误！") ;
					}
				}else{
					json.setSuccess(false) ;
					json.setMsg("密码错误！") ;
				}
			} else {
				json.setSuccess(false) ;
				json.setMsg("账户不存在！") ;
			}
		}else{
			json.setSuccess(false) ;
			json.setMsg("验证码不正确！") ;
		}
		return json;
	}
	
	@RequestMapping("/login.do")
	public String indexVali(Model model,HttpSession session){
		User user = (User)session.getAttribute("user");
		if(checkLogin(session)){
			String menu = "";
			try{
				menu = authService.buildMenuTree(user);
			}catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("menu",menu);
			return "index";
		}
		return "login";
	}	
	
	@RequestMapping("/404")
	public String error(Model model,HttpSession session){
		return "404";
	}
	
	
	@RequestMapping("/")
	public String indexVali12(Model model,HttpSession session){
		User user = (User)session.getAttribute("user");
		if(checkLogin(session)){
			//System.out.println("进入系统"+user.getName());
			String menu = "";
			try{
				menu = authService.buildMenuTree(user);
			}catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("menu",menu);
			return "index";
		}
		return "login";
	}
	
	
	
	
	@RequestMapping("/logout")
	@ResponseBody
	public boolean logout(HttpSession session,HttpServletResponse response){
		/*session.removeAttribute("user");
		UserCookieUtil.clearCookie(response);
		session.invalidate();*/
		UserCookieUtil.clearCookie(response);
		
		Subject subject = SecurityUtils.getSubject();
		//可以使用 subject.isAuthenticated() 以判断当前用户已经登录过了 此时可以直接通过subject.getSession()去获取我们放入session的信息了。
		if (subject.isAuthenticated()) {
			//"登出用户：" + ((SysmanUser) subject.getPrincipal()).getUserName()
			// session 会销毁，在SessionListener监听session销毁，清理权限缓存
			subject.logout(); 
		}
		return true;
	}
	
	public boolean checkLogin(HttpSession session){
		User user= (User)session.getAttribute("user");
		if(user == null){
			return false;
		}
		return true;
	}
}
