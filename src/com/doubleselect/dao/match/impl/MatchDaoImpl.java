package com.doubleselect.dao.match.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.mapping.Array;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.doubleselect.dao.impl.IBaseDaoImpl;
import com.doubleselect.dao.match.MatchDao;
import com.doubleselect.model.match.Tmatch;
import com.doubleselect.model.match.vo.Match;

@Repository
public class MatchDaoImpl extends IBaseDaoImpl<Tmatch> implements  MatchDao{


	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<Match> getHJ(String hql,int page, int rows, Map<String, Object> params){
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
}
