package com.doubleselect.interceptor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.doubleselect.model.sys.vo.User;
import com.doubleselect.service.interceptor.IAuthInterceptorService;
/**
 * 
 * 类描述：权限拦截器
 * 类名称：AuthInterceptor
 * @version 1.0
 */
public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	private static List<String> exceptant = new ArrayList<String>();
	
	@Autowired
	private IAuthInterceptorService authInterceptorService;
	
	public AuthInterceptor(){	
		//在此处添加不需要拦截哦权限 sys/userController/setRole已经设置过了所以不需要
		exceptant.add("sys/userController/updateRole");
		exceptant.add("sys/roleController/update");
		exceptant.add("sys/userController/queryTreeMenu");
		exceptant.add("student/studentmessage/studentmessage");
		exceptant.add("teacher/teachermessage/teachermessage");
		exceptant.add("student/studentmessage/searchStudentBysno");
		exceptant.add("teacher/teachermessage/searchTeacherBysno");
		exceptant.add("manage/managemessage/searchManage");
		exceptant.add("manage/managemessage/searchManageByValue");
		exceptant.add("teacher/teachermessage/updateinformation");
		exceptant.add("tudent/studentmessage/updateinformation");
	}
		
	/**
	 * 在拦截指定操作之后，对其权限进行校验，如果没有权限，则返回false，
	 * 否则，如果有下一个拦截器，就继续执行拦截器，如果没有则返回true
	 * 返回值为true ，则继续调用后续的拦截器和目标方法
	 * 返回值为false，则不会调用后续的拦截器和目标方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
			
		
		//System.out.println("权限检查拦截器preHandle执行了....");
		
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
		HttpSession  session = request.getSession();
		String url = request.getRequestURL().toString();
		
		//System.out.println("路径"+url);
		
		User user = (User) session.getAttribute("user");
		if (isExceptant(url) || url.contains("search") || url.contains("preAdd") || url.contains("preEdit")) {
			//System.out.println("扣1");
			return super.preHandle(request, response, handler);
		}else if (authInterceptorService.checkAuth(user, url)) {
			//System.out.println("扣"+ "2");
             return super.preHandle(request, response, handler);
		}else {
			PrintWriter out = response.getWriter();
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
			builder.append("alert(\"您没有该操作权限！.........\");");
			builder.append("</script>");
			out.print(builder.toString());
			out.flush();
			out.close();
			return false;
		}
	
	}
	
	private boolean isExceptant(String url){
		if(url==null ){
			return false;
		}
	
		int i=exceptant.size()-1;
		String tempUrl = null;
		for(;i>=0;i--){
			tempUrl = exceptant.get(i).trim();
			int index = url.trim().indexOf(tempUrl);
			if (index>0) {
				return true;
			}
		}
		return false;
	}	
}
