package com.doubleselect.interceptor;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.doubleselect.model.sys.vo.User;
import com.doubleselect.service.sys.IUserService;
import com.doubleselect.util.UserCookieUtil;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class PowerInterceptor extends HandlerInterceptorAdapter{
private final static String cookieDomainName = "doubleselect";   //自己随便定义，跟上面一致
	
	@Autowired
	private IUserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws Exception {
		//System.out.println("到达cookie拦截器了");
		HttpSession session=request.getSession(true);
		User user=(User) session.getAttribute("user");
		//System.out.println("user:"+user);
		String url=request.getRequestURI().toString();
		//System.out.println("url"+url);
		//判断用户是否登录，一般是都没登录的
		if(user==null){
			  Cookie[] cookies = request.getCookies();//取cookie值，这里还有其他网站的
			  if(cookies!=null){
				  String cookieValue = null;
				  //System.out.println("cookies:"+cookies.length);
				  //下面是找到本项目的cookie
				  for (int i = 0; i < cookies.length; i++) {
					  if(cookieDomainName.equals(cookies[i].getName())){
						  cookieValue = cookies[i].getValue();
						  break;
					  }
				  }
			  //如果cookieValue为空 说明用户上次没有选择“记住下次登录”执行其他
				  if(cookieValue==null){
					  if(url.contains("doubleselect")){  
						  //System.out.println("1");
						  response.sendRedirect(request.getContextPath()+"/");
						  return false;
					  }
					  //System.out.println("2");
				  }else{
				  // 先得到的CookieValue进行Base64解码
					  String cookieValueAfterDecode = new String(Base64.decode(cookieValue),"utf-8");
					  // 对解码后的值进行分拆,得到一个数组,如果数组长度不为3,就是非法登陆
					  String cookieValues[] = cookieValueAfterDecode.split(":");
					  if(cookieValues.length!=3){ 
						  response.sendRedirect(request.getContextPath()+"/");
						  return false;
				  }
			  // 判断是否在有效期内,过期就删除Cookie
			  long validTimeInCookie = new Long(cookieValues[1]);
			  if (validTimeInCookie < System.currentTimeMillis()) {
					  // 删除Cookie
					  UserCookieUtil.clearCookie(response); 
					  response.sendRedirect(request.getContextPath()+"/");
					  return false;
				  }
				  // 取出cookie中的用户名,并到数据库中检查这个用户名,
				  String username = cookieValues[0];
				  //System.out.println("username:"+username);
				  User temp = userService.findByusername(username);
				  //System.out.println("temp"+temp);
				  // 如果user返回不为空,就取出密码,使用用户名+密码+有效时间+ webSiteKey进行MD5加密。与前面设置的进行比较，看是否是同一个用户
				  if(temp!=null){
					  String md5ValueInCookie = cookieValues[2];
					  String md5ValueFromUser = UserCookieUtil.getMD5(temp.getName() + ":" + temp.getPwd() + ":" + validTimeInCookie + ":" + cookieDomainName);
						  // 将结果与Cookie中的MD5码相比较,如果相同,写入Session,自动登陆成功,并继续用户请求
						  if (md5ValueFromUser.equals(md5ValueInCookie)) {
							  session.setAttribute("user", temp);
							  response.sendRedirect(request.getContextPath()+"/");
							  return false;
						  }
				  }
			  }
			}
		}else{
			if(url.contains("doubleselect")){
				response.sendRedirect(request.getContextPath()+"/");
				return false;
				}
			}
		return true;
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
	HttpServletResponse response, Object handler, Exception ex)
	throws Exception {
	super.afterCompletion(request, response, handler, ex);
	}
}
