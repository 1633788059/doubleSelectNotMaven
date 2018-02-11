package com.doubleselect.service.sys.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import com.doubleselect.model.sys.vo.User;
import com.doubleselect.service.sys.IUserService;


@Service
public class IUserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Autowired
	private IRoleDao roleDao;

	@Autowired
	private IAuthDao authDao;
	
	
	public List<String> authList(String id) {
		List<String> authList = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Tuser t = userDao.get("from Tuser t join fetch t.troles role join fetch role.tauth tauth where t.id = :id", params);
		if (t != null) {
			Set<Trole> roles = t.getTroles();
			if (roles != null && !roles.isEmpty()) {
				for (Trole role : roles) {
					Set<Tauth> auths = role.getTauths();
					if (auths != null && !auths.isEmpty()) {
						for (Tauth auth : auths) {
							if (auth != null && auth.getUrl() != null) {
								authList.add(auth.getUrl());
							}
						}
					}
				}
			}
		}
		return authList;
	}
	

	public User getPassword(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", user.getName());
		Tuser t = userDao.get("from Tuser t where t.name = :name", params);
		if (t != null) {
			BeanUtils.copyProperties(t, user);
			return user;
		}
		return null;
	}
	
	public User login(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", user.getName());
		params.put("pwd",user.getPwd());
		Tuser t = userDao.get("from Tuser t where t.name = :name and t.pwd = :pwd", params);
		if (t != null) {
			BeanUtils.copyProperties(t, user);
			return user;
		}
		return null;
	}


	
	public long queryTotal(String name) {
		Map<String,Object> params = new HashMap<String, Object>();
		Long total ;
		String hql ;
		if(name == null || "".equals(name)){
			hql = "select count(*) from Tuser t where 1 = 1";
		}else{
			hql = "select count(*) from Tuser t where name = :name";
			params.put("name", name);
		}
		total = userDao.count(hql, params);
		return total;
	}


	
	public List<User> query(int page, int rows, String name) {
		
		List<User> userList = new ArrayList<User>();
		Map<String, Object> params = new HashMap<String,Object>();
		String hql ;
		if(name == null || "".equals(name)){
			hql = " from Tuser t order by id desc";
		}else{
			hql = "from Tuser t where t.name = :name order by id desc";
			params.put("name", name);
		}
		
		List<Tuser> tuserList = userDao.find(hql,params,page,rows);
	
		if(tuserList != null && tuserList.size() > 0){
			for(Tuser t : tuserList){
				User u = new User();
				BeanUtils.copyProperties(t, u);
				Set<Trole> roles = t.getTroles();
				if(roles != null && !roles.isEmpty()){
					String roleIds = "";
					String roleNames = "";
					boolean flag = false;
					for(Trole tr : roles){
						if(flag){
							roleIds += ",";
							roleNames += ",";
						}else{
							flag = true;
						}
						roleIds += tr.getId();
						roleNames +=tr.getName();
					}
					u.setRoleIds(roleIds);
					u.setRoleNames(roleNames);
				}
				userList.add(u);
			}
		}
		return userList;
	}
	
	synchronized public void add(User user) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("name", user.getName());
		if(userDao.count("select count(*) from Tuser t where t.name = :name ", params) > 0){
			throw new Exception("登陆名已存在");
		} else {
			Tuser tuser = new Tuser();
			BeanUtils.copyProperties(user, tuser);
			tuser.setCreatedatetime(new Date());
			userDao.save(tuser);
		}
	}

	
	public void delete(int[] ids, User user) {
		for(int id:ids){
			if(id != user.getId()){ //防止出现在即删除自己情况
				userDao.delete(userDao.get(Tuser.class, id));
			}
		}
	}


	
	public void updateRole(int id, int[] rid) {

		Set<Trole> troleSet = new HashSet<Trole>();
		for(int troleId:rid){
			Trole trole = roleDao.get(Trole.class,troleId);
			troleSet.add(trole);
		}
		
		Tuser tuser = userDao.get(Tuser.class, id);
		tuser.setTroles(null);
		tuser.setTroles(troleSet);

		Serializable i = userDao.save(tuser);
		/**
		 * 这个也可以
		 */
		//userDao.update(tuser);
	}



	public void updatepass(User user){
		// TODO Auto-generated method stub
		String hql="update tuser set pwd=:pwd where id=:id";
		Map<String ,Object> params = new HashMap<String,Object>();
		params.put("pwd",user.getPwd());
		params.put("id",user.getId());
		this.userDao.update(hql,params);
	}


	public User findByusername(String username) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name",username);
		Tuser t = userDao.get("from Tuser t where t.name = :name", params);
		if (t != null) {
			User user=new User();
			BeanUtils.copyProperties(t, user);
			return user;
		}
		return null;
	}
	
}
