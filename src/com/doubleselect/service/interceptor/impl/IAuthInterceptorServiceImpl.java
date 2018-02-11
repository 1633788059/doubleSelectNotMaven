package com.doubleselect.service.interceptor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.doubleselect.dao.interceptor.IAuthInterceptorDao;
import com.doubleselect.dao.sys.IUserDao;
import com.doubleselect.model.sys.Tauth;
import com.doubleselect.model.sys.Trole;
import com.doubleselect.model.sys.Tuser;
import com.doubleselect.model.sys.vo.User;
import com.doubleselect.service.interceptor.IAuthInterceptorService;
import com.sun.org.apache.bcel.internal.generic.RETURN;



@Service
public class IAuthInterceptorServiceImpl implements IAuthInterceptorService {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IAuthInterceptorDao authDao;
	
	
	public boolean checkAuth(User user, String url) throws Exception {
		
		//System.out.println("com.doubleselect.service.interceptor.impl");
		//根据用户id查出权限用户对应的权限中的url
		List<String> urlList = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", user.getId());
		Tuser t = userDao.get("from Tuser t join fetch t.troles role join fetch role.tauths tauth where t.id = :id", params);
		if (t != null) {
			Set<Trole> roles = t.getTroles();
			if (roles != null && !roles.isEmpty()) {
				for (Trole role : roles) {
					Set<Tauth> tauths = role.getTauths();
					if (tauths != null && !tauths.isEmpty()) {
						for (Tauth auth : tauths) {
							if (auth != null && auth.getUrl() != null) {
								urlList.add(auth.getUrl());
							}
						}
					}
				}
			}
		}
		
		for(String tempUrl : urlList){
			if(tempUrl != null && !"".equals(tempUrl.trim()) && url.indexOf(tempUrl)>=0)	
				
				////System.out.println("1");
				
				return true;			
		}
		////System.out.println("2");
		return false;
	}


	public Set<String> getRoles(String userName) {
		//System.out.println("com.doubleselect.service.interceptor.impl");
		//根据用户id查出权限用户对应的权限中的url
		List<String> urlList = new ArrayList<String>();
		Set<String> roleSet=new HashSet<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", userName);
		Tuser t = userDao.get("from Tuser t join fetch t.troles role join fetch role.tauths tauth where t.name = :name", params);
		if (t != null) {
			Set<Trole> roles = t.getTroles();
			if (roles != null && !roles.isEmpty()) {
				for (Trole role : roles) {
						roleSet.add(role.getName());
					}
				}
			}
		
		return roleSet;
	
	}


	public Set<String> getPermissions(String userName) {
		//System.out.println("com.doubleselect.service.interceptor.impl");
				//根据用户id查出权限用户对应的权限中的url
				List<String> urlList = new ArrayList<String>();
				Set<String> permissions=new HashSet<String>();
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("name", userName);
				Tuser t = userDao.get("from Tuser t join fetch t.troles role join fetch role.tauths tauth where t.name = :name", params);
				if (t != null) {
					Set<Trole> roles = t.getTroles();
					if (roles != null && !roles.isEmpty()) {
						for (Trole role : roles) {
							Set<Tauth> tauths = role.getTauths();
							if (tauths != null && !tauths.isEmpty()) {
								for (Tauth auth : tauths) {
									if (auth != null && auth.getUrl() != null) {
										permissions.add(auth.getUrl());
									}
								}
							}
						}
					}
				}
				return permissions;
	}


	public Map<String, String> getMap() {
		Map<String,String>  map = new LinkedHashMap<String,String>();	
		
/*		List<Tuser> user= userDao.find("from Tuser t join fetch t.troles role join fetch role.tauths tauth");
		
		for(Tuser t:user){
			Set<Trole> roles = t.getTroles();
			if (roles != null && !roles.isEmpty()) {
				for (Trole role : roles) {
					System.out.println(role.getName());
					Set<Tauth> tauths = role.getTauths();
					if (tauths != null && !tauths.isEmpty()) {
						for (Tauth auth : tauths) {
							if (auth != null && auth.getUrl() != null) {
								map.put("/"+auth.getUrl(),"authc,roles["+role.getName()+"],perms["+auth.getUrl()+"]");
							}
						}
					}
				}
			}
		}*/
		
		List<Tauth> authList = new ArrayList<Tauth>();
		authList = authDao.find("from Tauth t");
		
		for (Tauth auth : authList) {
			if (auth != null && auth.getUrl() != null) {
				map.put("/"+auth.getUrl()+"*","authc,perms["+auth.getUrl()+"]");
			}
		}
		//System.out.println("map的长度"+map.size());
		return map;
	}

	
	public List<String> getList() {
		 List<String>  map = new ArrayList<String>();	
		
/*		List<Tuser> user= userDao.find("from Tuser t join fetch t.troles role join fetch role.tauths tauth");
		
		for(Tuser t:user){
			Set<Trole> roles = t.getTroles();
			if (roles != null && !roles.isEmpty()) {
				for (Trole role : roles) {
					System.out.println(role.getName());
					Set<Tauth> tauths = role.getTauths();
					if (tauths != null && !tauths.isEmpty()) {
						for (Tauth auth : tauths) {
							if (auth != null && auth.getUrl() != null) {
								map.put("/"+auth.getUrl(),"authc,roles["+role.getName()+"],perms["+auth.getUrl()+"]");
							}
						}
					}
				}
			}
		}*/
		
		List<Tauth> authList = new ArrayList<Tauth>();
		authList = authDao.find("from Tauth t");
		
		for (Tauth auth : authList) {
			if (auth != null && auth.getUrl() != null) {
				map.add(auth.getUrl());
			}
		}
		//System.out.println("map的长度"+map.size());
		return map;
	}

}
