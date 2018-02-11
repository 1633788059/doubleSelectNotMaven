package com.doubleselect.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.doubleselect.util.FormTool.Token;



/**
 * 
 * 类描述：用于产生校验码和验证拦截器，防止（网速慢时等情况）表单重复提交
 * 类名称：TokenInterceptor
 * @version 1.0
 */
public class TokenInterceptor  extends HandlerInterceptorAdapter{

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		//System.out.println("表单拦截器拦截了");
		if (handler instanceof HandlerMethod) {
			//System.out.println("表单handler : true");
		}
		if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class );
            if (annotation != null ) {
                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    request.getSession( false ).setAttribute( "token" , UUID.randomUUID().toString());
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        return false ;
                    }
                    request.getSession(false).removeAttribute( "token" );
                }
            }
            return true ;
		 } else {
	            return super .preHandle(request, response, handler);
	     }
   
    }

	/**
	 * 
	 * 方法描述：检查是否是重复提交情况
	 * 方法名：isRepeatSubmit
	 * 创建人：hy
	 * 创建时间：2014-7-27 上午9:58:35
	 * @param request
	 * @return
	 * 返回值：boolean
	 */
    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession( false ).getAttribute( "token" );
        if (serverToken == null ) {
            return true ;
        }
        String clinetToken = request.getParameter("token");
        if (clinetToken == null ) {
            return true ;
        }
        if (!serverToken.equals(clinetToken)) {
            return true ;
        }
        return false ;
    }
}
