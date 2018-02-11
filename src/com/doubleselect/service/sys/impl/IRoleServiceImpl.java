package com.doubleselect.service.sys.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doubleselect.dao.sys.IAuthDao;
import com.doubleselect.dao.sys.IRoleDao;
import com.doubleselect.dao.sys.IUserDao;
import com.doubleselect.model.sys.Tauth;
import com.doubleselect.model.sys.Trole;
import com.doubleselect.model.sys.Tuser;
import com.doubleselect.model.sys.vo.Role;
import com.doubleselect.service.sys.IRoleService;


@Service
public class IRoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IAuthDao authDao;

	
	public String curRole(int uid) {
		
		Tuser tuser = userDao.get(Tuser.class, uid);
		
		//当前用户所拥有的角色
		Set<Trole> tuser_role = tuser.getTroles();
		
		List<Integer> urid = new ArrayList<Integer>();
		for(Trole trole:tuser_role){
			urid.add(trole.getId());
		}
		
		//当前所有的trole角色
		List<Trole> troleList = roleDao.find("from Trole");
		
		// 构造前台展示代码
		StringBuilder sb = new StringBuilder();
		sb.append("<input type='hidden' name='id' value='" + uid + "'>");
		sb.append("<table style='margin:0 auto;'>");
		sb.append("<tr><td></td><td></td></tr>");
		int index = 0;
		for(Trole trole : troleList){
			if(index++%2==0){
				sb.append("<tr>");
				sb.append("<td>");
				if (urid.contains(trole.getId())) { //当前用户tuser中对应拥有的权限
					sb.append("<input type=\"checkbox\" checked=\"checked\" name=\"rid\" value=\""
							+ trole.getId() + "\">" + trole.getName() + "</input>");
				} else {							    //当前用户tuser没有对应拥有的权限
					sb.append("<input type=\"checkbox\" name=\"rid\" value=\""
							+  trole.getId() + "\">" + trole.getName() + "</input>");
				}
				sb.append("</td>");
			}else{
				sb.append("<td style='padding-left:40;'>");
				if (urid.contains(trole.getId())) {
					sb.append("<input type=\"checkbox\" checked=\"checked\" name=\"rid\" value=\""
							+ trole.getId() + "\">" + trole.getName() + "</input>");
				} else {
					sb.append("<input type=\"checkbox\" name=\"rid\" value=\""
							+  trole.getId() + "\">" + trole.getName() + "</input>");
				}
				sb.append("</td>");
				sb.append("</tr>");
				index = 0;
			}
		}
		if(!sb.toString().endsWith("</tr>")){
			sb.append("</tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	
	public long queryTotal(String name) {
		Map<String,Object> params = new HashMap<String, Object>();
		Long total ;
		String hql ;
		if(name == null || "".equals(name)){
			hql = "select count(*) from Trole t where 1 = 1";
		}else{
			hql = "select count(*) from Trole t where name = :name";
			params.put("name", name);
		}
		total = roleDao.count(hql, params);
		return total;
	}

	
	public List<Role> query(int page, int rows, String name) {
		//System.out.println("从数据库中读取");
		Map<String, Object> params = new HashMap<String,Object>();
		String hql ;
		if(name == null || "".equals(name)){
			hql = " from Trole t order by id desc";
		}else{
			hql = "from Trole t where t.name = :name order by id desc";
			params.put("name", name);
		}
		
		List<Trole> troleList = roleDao.find(hql,params,page,rows);
		
		List<Role> roleList = new ArrayList<Role>();
		
		for(Trole trole : troleList){
			Role role = new Role();
			BeanUtils.copyProperties(trole, role);
			roleList.add(role);
		}
		
		return roleList;
	}

	
	public boolean delete(int[] ids) {
		//暂时先这种删除方法  以后会改为批量删除
		String hql = "delete Trole t where t.id = :id";
		for(int id:ids){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id", id);
			int i = roleDao.executeHql(hql, params);
			if(i <= 0){
				return false;
			}
		}
		return true;
	}

	 
	 public boolean add(Role role) throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name", role.getName());
		if(roleDao.count("select count(*) from Trole t where t.name = :name ", params) > 0){
			throw new Exception("角色名已存在");
		}else{
			Trole trole = new Trole();
			BeanUtils.copyProperties(role, trole);
	
			Serializable ser = roleDao.save(trole);
			return ser != null;
		}
	}

	
	public Role queryById(int id) {
		Trole trole = roleDao.get(Trole.class, id);
		Role role = new Role();
		BeanUtils.copyProperties(trole, role);
		return role;
	}
	
	
	public void update(Role role){
		Trole trole = new Trole();
		BeanUtils.copyProperties(role, trole);
		roleDao.update(trole);
	}
	
	
	public void updateAuth(int rid ,String oaids, String aids) {
		String[] str = null;
		Trole trole = roleDao.get(Trole.class, rid);
		
		Set<Tauth> authSet = new HashSet<Tauth>() ;
		
		if(aids != null && !"".equals(aids)){
			str = aids.split(",");
			if(str != null && str.length > 0){
				for(String aid : str){
					int iaid = Integer.parseInt(aid);
					authSet.add(authDao.get(Tauth.class, iaid));
				}
			}
		}
		trole.setTauths(authSet);
		roleDao.update(trole);
	}	
}
