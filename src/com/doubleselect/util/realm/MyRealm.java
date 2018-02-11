package com.doubleselect.util.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.doubleselect.model.sys.vo.User;
import com.doubleselect.service.interceptor.IAuthInterceptorService;
import com.doubleselect.service.sys.IUserService;
/**
 * 自定义Realm
 * @author java1234_小锋
 *
 */
public class MyRealm extends AuthorizingRealm{

	@Resource
	private IAuthInterceptorService iAuthInterceptorService;
	
	@Resource
	private IUserService userService;
	
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName=(String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		Set<String> exceptant=iAuthInterceptorService.getPermissions(userName);
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
		//System.out.println(principals.getPrimaryPrincipal());
		//System.out.println(principals.getRealmNames());
		
		for(String role:iAuthInterceptorService.getRoles(userName)){
			//System.out.println("角色"+role);
		}
		for(String permissions:iAuthInterceptorService.getPermissions(userName)){
			//System.out.println("权限"+permissions);
		}
		//System.out.println(iAuthInterceptorService.getPermissions(userName));
		authorizationInfo.setRoles(iAuthInterceptorService.getRoles(userName));
		authorizationInfo.setStringPermissions(exceptant);
		return authorizationInfo;
	}
	

	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String userName=(String)token.getPrincipal();
		User user  = userService.findByusername(userName);
	
		if(user!=null){
			AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user.getName(),user.getPwd(),getName());
			return authcInfo;
		}else{
			return null;				
		}

	}


}
