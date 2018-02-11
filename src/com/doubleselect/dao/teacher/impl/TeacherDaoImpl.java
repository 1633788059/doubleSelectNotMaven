package com.doubleselect.dao.teacher.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.doubleselect.dao.impl.IBaseDaoImpl;
import com.doubleselect.dao.teacher.TeacherDao;
import com.doubleselect.model.teacher.Tteacher;
import com.doubleselect.model.teacher.vo.Teacher;

@Repository
public class TeacherDaoImpl extends IBaseDaoImpl<Tteacher> implements TeacherDao {


	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<Teacher> getHJ(String hql,int page, int rows, Map<String, Object> params){
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
	public List<Teacher> getByteacherId(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@Cacheable("checkAuth")
	public List<Teacher> getByteacherSno(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	
}
