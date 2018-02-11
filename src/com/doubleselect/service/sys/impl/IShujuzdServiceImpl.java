package com.doubleselect.service.sys.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doubleselect.dao.sys.IShujuzdDao;
import com.doubleselect.model.sys.Tshujuzd;
import com.doubleselect.service.sys.IShujuzdService;
import com.doubleselect.util.ConditionValidate;

@Service
public class IShujuzdServiceImpl implements IShujuzdService{

	@Autowired
	private IShujuzdDao shujuzdDao;

	
	public long queryTotal(String zdlb) {
		long total = 0;
		Map<String,Object> params = new HashMap<String, Object>();
		String hql = null;
		if(ConditionValidate.isEmpty(zdlb)){
			hql = "select count(*) from Tshujuzd t where 1 = 1";
		}else{
			hql = "select count(*) from Tshujuzd t where  zdlb = :zdlb";
			params.put("zdlb", zdlb);
		}
		total = shujuzdDao.count(hql, params);
		return total;
	}

	
	public List<Tshujuzd> query(int page, int rows, String zdlb) {
		//System.out.println("从数据库中读取。。。。");
		Map<String,Object> params = new HashMap<String,Object>();
		List<Tshujuzd> sjzdList = null;
		String hql = null;
		if(ConditionValidate.isEmpty(zdlb)){
			hql = "from Tshujuzd t order by zdlb ";
		}else{
			hql = "from Tshujuzd t where zdlb = :zdlb order by id desc";
			params.put("zdlb", zdlb);
		}
		sjzdList = shujuzdDao.find(hql, params,page,rows);
		return sjzdList;
	}

	
	public void add(List<Tshujuzd> objList) {
		//System.out.println("数据字典批量添加");
		shujuzdDao.batchSave(objList);
	}

	
	public void update(List<Tshujuzd> objList) {
		//System.out.println("数据字典批量更行");
		shujuzdDao.batchUpdate(objList);
	}

	
	public boolean add(Tshujuzd sjzd) {
		
		Serializable ser = shujuzdDao.save(sjzd);
		return ser != null;
	}

	
	public void delete(int id) throws Exception {
		
		shujuzdDao.delete(id, Tshujuzd.class);
	}

	
	public void update(Tshujuzd bean) {
		
		shujuzdDao.update(bean);
	}
}
