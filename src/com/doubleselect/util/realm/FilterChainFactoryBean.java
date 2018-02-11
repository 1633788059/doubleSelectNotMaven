package com.doubleselect.util.realm;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.poi.poifs.storage.ListManagedBlock;
import org.springframework.beans.factory.FactoryBean;
import com.doubleselect.service.interceptor.IAuthInterceptorService;
import com.doubleselect.service.sys.IUserService;


public class FilterChainFactoryBean implements FactoryBean<Map<String,String>> {
	

	@Resource
	private IAuthInterceptorService iAuthInterceptorService;
	
	@Resource
	private IUserService userService;
	
	/**
	 * 权限map
	 */
	public Map<String, String> getObject() throws Exception {
		Map<String,String>  map = this.iAuthInterceptorService.getMap();
		map.remove("/student/studentmessage/uploadImage*");
		map.remove("/teacher/teachermessage/uploadImage*");
	/*	map.put("/login", "anon");
		map.put("/doLogin", "anon");
		map.put("/defined", "anon");*/
		/*for(String permissions:iAuthInterceptorService.getPermissions("admin")){
			map.put(permissions, "authc,perms["+permissions+"]");
		}*/
		
		/*String hql ="from "+SysmanResource.class.getName() + "  where href is not null and deleteFlag = 0  "; 
		
		
		@SuppressWarnings("unchecked")
		List<SysmanResource> rs = (List<SysmanResource>) this.find(hql);
		
		for(SysmanResource r : rs){
			if(r.getHref().lastIndexOf("?") > 0 ){
				//map.put(r.getHref()+"&t_="+r.getPid(), "authc,perms["+r.getPid()+"]");
				map.put(r.getHref(), "authc,perms["+r.getHref()+"]");
			}else{
				//map.put(r.getHref()+"?t_="+r.getPid(), "authc,perms["+r.getPid()+"]");
				map.put(r.getHref(), "authc,perms["+r.getHref()+"]");
			}
		}*/
		//map.put("/admin/**", "authc");
		 for (Object obj : map.entrySet()) {
	            Entry entry = (Entry) obj;
	            String key = (String) entry.getKey();
	            String value = (String) entry.getValue();
	            //System.out.println(key + "=" + value);
	       }
		return map;
	}

	public Class<?> getObjectType() {
		return Map.class;
	}

	public boolean isSingleton() {
		return true;
	}

}
