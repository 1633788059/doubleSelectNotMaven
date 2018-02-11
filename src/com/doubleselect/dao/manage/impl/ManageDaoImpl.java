package com.doubleselect.dao.manage.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.doubleselect.dao.impl.IBaseDaoImpl;
import com.doubleselect.dao.manage.ManageDao;
import com.doubleselect.model.manage.Tmanage;
import com.doubleselect.model.manage.vo.Manage;

@Repository
public class ManageDaoImpl extends IBaseDaoImpl<Tmanage> implements ManageDao {


	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<Manage> getHJ(String hql,int page, int rows, Map<String, Object> params){
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<Manage> getById(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}
	@Cacheable("checkAuth")
	public List<Manage> getByValue(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		
		return q.list();
	}

	
}
