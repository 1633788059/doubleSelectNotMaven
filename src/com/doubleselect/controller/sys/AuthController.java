package com.doubleselect.controller.sys;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doubleselect.controller.BaseController;
import com.doubleselect.model.sys.vo.User;
import com.doubleselect.model.vo.Json;
import com.doubleselect.service.interceptor.IAuthInterceptorService;

/**
 * 
 * 类描述：权限管理控制器
 * 类名称：AuthController
 * 创建时间：2014-8-3 上午10:18:42
 * @version 1.0
 */
@Controller
@RequestMapping("sys/authController")
public class AuthController extends BaseController {

	@Autowired
	private IAuthInterceptorService authInterceptorService;

	@RequestMapping("checkAuth")
	@ResponseBody
	public Json checkAuth(HttpServletResponse response, HttpServletRequest request, String url){
		Json json = new Json();
		HttpSession session =  request.getSession();
		User user = (User) session.getAttribute("user");
		boolean flag = false;
		try {
			flag = authInterceptorService.checkAuth(user, url);
		} catch (Exception e) {	
			e.printStackTrace();
			flag = false;
		}
		json.setSuccess(flag);
		return json;		
	}	
}
