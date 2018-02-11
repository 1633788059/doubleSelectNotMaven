package com.doubleselect.service.interceptor;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.doubleselect.model.sys.vo.User;

public interface IAuthInterceptorService {

	boolean checkAuth(User user, String url) throws Exception;
	
	/**
	 * 得到用户的角色
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	Set<String> getRoles(String userName);
	
	/**
	 * 得到用户的权限
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	Set<String> getPermissions(String userName);
	
	/**
	 * 得到map
	 * 
	 * @return
	 */
	Map<String, String> getMap();
	
	/**
	 * 得到list
	 * @return
	 */
	List<String> getList();
}
