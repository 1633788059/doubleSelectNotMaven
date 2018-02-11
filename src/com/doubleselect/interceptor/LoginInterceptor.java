package com.doubleselect.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.doubleselect.model.sys.vo.User;

/**
 * 
 * 类描述：登陆拦截器
 * 类名称：LoginInterceptor
 * 创建人： hy
 * 创建时间：2014-8-3 下午7:12:31
 * @version 1.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//System.out.println("登陆拦截器执行了");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {
			String context = session.getServletContext().getContextPath();
			
			PrintWriter out = response.getWriter();
			StringBuilder builder = new StringBuilder();
			builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
			builder.append("alert(\"还没有登录或者登录时间过期，请重新登录！\");");
			builder.append("window.parent.location='"+context+"';");
			builder.append("</script>");
			out.print(builder.toString());
			out.flush();
			out.close();

			return false;
		} else {
			return super.preHandle(request, response, handler);
		}
	}
}
