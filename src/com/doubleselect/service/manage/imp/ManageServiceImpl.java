package com.doubleselect.service.manage.imp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doubleselect.dao.manage.ManageDao;
import com.doubleselect.model.manage.Tmanage;
import com.doubleselect.model.manage.vo.Manage;
import com.doubleselect.service.manage.ManageService;

@Service
public class ManageServiceImpl implements ManageService{
	
	@Autowired
	private ManageDao manageDao;

	
	public void save(Manage t) throws Exception {
		Tmanage tdd = new Tmanage();
		BeanUtils.copyProperties(t, tdd);
		this.manageDao.save(tdd);	
	}

	
	public void delete(Manage t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		this.manageDao.delete(id, Tmanage.class);
	}

	
	public void delete(int[] ids) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Manage t) throws Exception {
		// TODO Auto-generated method stub
		Tmanage tdd = new Tmanage();
		BeanUtils.copyProperties(t, tdd);
		this.manageDao.update(tdd);	
	}

	
	public List<Manage> getByPage(int page, int row, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Manage getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int queryTotal(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public List<Manage> getByPage(int page, int row, String starttime,String endtime,String value) {
		List<Manage> list_return = new ArrayList<Manage>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select new com.doubleselect.model.manage.vo.Manage(t.id,t.starttime,t.endtime,t.value,t.note) from Tmanage t ";
		
		if(null!=value && !"".equals(value)){
			if(flag){
				hql += " and t.value=:value" ;
				params.put("value",value);
			}else{
				hql += " where t.value=:value";
				params.put("value",value);
				flag=true;
			}
		}
		if((null!=starttime && !"".equals(starttime))||(null!=endtime && !"".equals(endtime))){

			if(null==starttime || "".equals(starttime)){
	
				if(flag){
					hql+= " and t.starttime<=:endtime or t.endtime<=:endtime";
					params.put("endtime", Long.parseLong(endtime));
				}else{
					hql+= " where t.starttime<=:endtime or t.endtime<=:endtime";
					params.put("endtime", Long.parseLong(endtime));
					flag=true;
				}
			}
			if(null==endtime || "".equals(endtime)){
				if(flag){
					hql+= " and t.starttime>=:starttime or t.endtime>=:starttime";
					params.put("starttime", Long.parseLong(starttime));
				}else{
					hql+= "where t.starttime>=:starttime or t.endtime>=:starttime";
					params.put("starttime", Long.parseLong(starttime));
				}
			}
			if((null!=starttime && !"".equals(starttime))&&(null!=endtime && !"".equals(endtime))){

				if(flag){
					hql+= " and t.starttime>=:starttime and t.starttime<=:endtime or t.endtime>=:starttime and t.endtime<=:endtime";
					params.put("starttime", Long.parseLong(starttime));
					params.put("endtime", Long.parseLong(endtime));
				}else{
					hql+= " where t.starttime>=:starttime and t.starttime<=:endtime or t.endtime>=:starttime and t.endtime<=:endtime";
					params.put("starttime", Long.parseLong(starttime));
					params.put("endtime", Long.parseLong(endtime));
					flag = true;
				}
			}
		}
	/*	if((null!=endtime && !"".equals(endtime))){
			if(flag){
				hql += " and t.endtime<=:endtime" ;		
				params.put("endtime", Long.parseLong(endtime));
			}else{
				hql += " where t.endtime<=:endtime";
				flag = true;
				params.put("endtime",Long.parseLong(endtime));				
			}
		}
		if(null!=value && !"".equals(value)){
			if(flag){
				hql += " and t.value=:value" ;
				params.put("value",value);							
			}else{
				hql += " where t.value=:value";
				params.put("value",value);						
			}
		}*/
		//System.out.println("hql"+hql);
		list_return = this.manageDao.getHJ(hql, page, row, params);
		return list_return;
	}

	public List<Manage> getById(String Id){
		List<Manage> list_return = new ArrayList<Manage>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select new com.doubleselect.model.manage.vo.Manage(t.id,t.starttime,t.endtime,t.value,t.note) from Tmanage t ";
				hql += " where t.id=:Id";
				params.put("Id",Integer.parseInt(Id));						
		list_return = this.manageDao.getById(hql, params);
		return list_return;
	}
	
	public int queryTotal(String starttime,String endtime,String value) {
		// TODO Auto-generated method stub
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select count(*) from Tmanage t ";
		if(null!=starttime && !"".equals(starttime)){
			hql+= " where t.starttime>=:starttime";
			params.put("starttime", Long.parseLong(starttime));
			flag = true;
		}
		if(null!=endtime && !"".equals(endtime)){
			if(flag){
				hql += " and t.endtime<=:endtime" ;		
				params.put("endtime", Long.parseLong(endtime));
			}else{
				hql += " where t.endtime<=:endtime";
				flag = true;
				params.put("endtime",Long.parseLong(endtime));				
			}
		}
		if(null!=value && !"".equals(value)){
			if(flag){
				hql += " and t.value=:value" ;
				params.put("value",value);							
			}else{
				hql += " where t.value=:value";
				params.put("value",value);						
			}
		}
		return this.manageDao.count(hql, params).intValue();
	}


	public List<Manage> getByValue(String value) {
		List<Manage> list_return = new ArrayList<Manage>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select new com.doubleselect.model.manage.vo.Manage(t.id,t.starttime,t.endtime,t.value,t.note) from Tmanage t ";
				hql += " where t.value=:value";
				params.put("value",value);						
		list_return = this.manageDao.getByValue(hql, params);
		return list_return;
	}

}
