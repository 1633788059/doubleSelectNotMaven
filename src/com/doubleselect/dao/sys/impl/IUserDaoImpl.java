package com.doubleselect.dao.sys.impl;

import java.util.Map;

import org.hibernate.Query;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import com.doubleselect.dao.impl.IBaseDaoImpl;
import com.doubleselect.dao.sys.IUserDao;
import com.doubleselect.model.sys.Tuser;


@Repository
public class IUserDaoImpl extends IBaseDaoImpl<Tuser> implements IUserDao {

	@CacheEvict(value="checkAuth", allEntries=true)
	public void update(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		Query q = this.getCurrentSession().createSQLQuery(hql);
		if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					q.setParameter(key, params.get(key));
				}
			}
			q.executeUpdate();
		
	}

}
