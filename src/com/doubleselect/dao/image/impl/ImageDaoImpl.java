package com.doubleselect.dao.image.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.doubleselect.dao.image.ImageDao;
import com.doubleselect.dao.impl.IBaseDaoImpl;
import com.doubleselect.model.image.Timage;
import com.doubleselect.model.image.vo.Image;

@Repository
public class ImageDaoImpl extends IBaseDaoImpl<Timage> implements ImageDao {


	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<Image> getHJ(String hql,int page, int rows, Map<String, Object> params){
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
	public List<Image> getBySno(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	
}
