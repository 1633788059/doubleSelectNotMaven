package com.doubleselect.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.doubleselect.model.sys.vo.User;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class UserCookieUtil {
	
	// 保存cookie的cookieName
	private final static String cookieDomainName = "doubleselect";   //自己随便定义
	
	// 加密cookie时的网站自定码
	private final static String webKey = "doubleselect";   //自己随便定义
	
	// 设置cookie有效期是两个星期，根据需要自定义
	private final static long cookieMaxAge = 60 * 60 * 24 * 7 * 2;

	/**
	 *  保存Cookie到客户端
	 *	传递进来的user对象中封装了在登陆时填写的用户名与密码
	 * @param user
	 * @param response
	 */
	public static void saveCookie(User user, HttpServletResponse response) {
		
		// cookie的有效期至（到哪一天）
		long validTime = System.currentTimeMillis() + (cookieMaxAge * 1000);
		/**
		 * 密码加密
		 */
/*		String Md5password=MD5.MD5(user.getPwd());
		user.setPwd(Md5password);*/
		// MD5加密用户详细信息（其实就是把当前用户加密一下，后面判断是否是同一个用户）
		//System.out.println("cookie传入password"+user.getPwd());
		String cookieValueWithMd5 = getMD5(user.getName() + ":"+ user.getPwd() + ":" + validTime + ":" + webKey);
		
		// 将要被保存的完整的Cookie值
		String cookieValue = user.getName() + ":" + validTime +":"+cookieValueWithMd5;
		
		// 再一次对Cookie的值进行BASE64编码
		String cookieValueBase64 = new String(Base64.encode(cookieValue.getBytes()));
		
		// 开始保存Cookie（cookie是网站名和值）
		Cookie cookie = new Cookie(cookieDomainName, cookieValueBase64);
		// 存两年(这个值应该大于或等于validTime)
		cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
		// cookie有效路径是网站根目录
		cookie.setPath("/");
		//System.out.println("传入cookie");
		// 向客户端写入
		response.addCookie(cookie);
	}

	
	/**
	 *简单的测试，无用
	 * @param cookiename
	 * @param request
	 * @return
	 */
	//取cookie
	public String getCookies(String cookiename,HttpServletRequest request){
			Cookie[] cookie = request.getCookies();
			String cookievalue=null;
			if(cookie!=null){
			for(int i=0;i<cookie.length;i++){
			if(cookie[i].getName().equals(cookiename))
			{
			try {
			cookievalue=URLDecoder.decode(cookie[i].getValue(), "utf-8");
			} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			}
			break;
			}
			}
			}
			return cookievalue;
	}
	
	// 用户注销时,清除Cookie
	public static void clearCookie(HttpServletResponse response) {
	               //创建一个空cookie添加，覆盖原来的达到清除目的
	Cookie cookie = new Cookie(cookieDomainName, null);
	cookie.setMaxAge(0);
	cookie.setPath("/");
	response.addCookie(cookie);
	}

	/**
	 *  获取Cookie组合字符串的MD5码的字符串
	 */
	public static String getMD5(String value) {
		String result = null;
		try {
		byte[] valueByte = value.getBytes();
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(valueByte);
		result = toHex(md.digest());
		} catch (NoSuchAlgorithmException e2) {
		e2.printStackTrace();
		}
		return result;
	}

	// 将传递进来的字节数组转换成十六进制的字符串形式并返回
	private static String toHex(byte[] buffer) {
		StringBuffer sb = new StringBuffer(buffer.length * 2);
		for (int i = 0; i < buffer.length; i++) {
		sb.append(Character.forDigit((buffer[i] & 0xf0) >> 4, 16));
		sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
		}
		return sb.toString();
		}
	}
