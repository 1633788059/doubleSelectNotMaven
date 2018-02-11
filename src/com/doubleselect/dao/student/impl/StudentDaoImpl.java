package com.doubleselect.dao.student.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.doubleselect.dao.impl.IBaseDaoImpl;
import com.doubleselect.dao.student.StudentDao;
import com.doubleselect.model.student.Tstudent;
import com.doubleselect.model.student.vo.Student;

@Repository
public class StudentDaoImpl extends IBaseDaoImpl<Tstudent> implements StudentDao {


	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<Student> getHJ(String hql,int page, int rows, Map<String, Object> params){
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
	public List<Student> getBystudentId(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}
	@Cacheable("checkAuth")
	public List<Student> getBystudentSno(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		
		return q.list();
	}

	
}
