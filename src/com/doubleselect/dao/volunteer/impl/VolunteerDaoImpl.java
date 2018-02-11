package com.doubleselect.dao.volunteer.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.mapping.Array;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.doubleselect.dao.impl.IBaseDaoImpl;
import com.doubleselect.dao.volunteer.VolunteerDao;
import com.doubleselect.model.vo.TeachersnoNum;
import com.doubleselect.model.volunteer.Tvolunteer;
import com.doubleselect.model.volunteer.vo.Volunteer;

@Repository
public class VolunteerDaoImpl extends IBaseDaoImpl<Tvolunteer> implements  VolunteerDao{


	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<Volunteer> getHJ(String hql,int page, int rows, Map<String, Object> params){
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
	@Cacheable("checkAuth")
	public List<Volunteer> marry(String hqlString) {
		Query q = this.getCurrentSession().createSQLQuery(hqlString).addEntity("t1",Tvolunteer.class);
		List<Volunteer> list=new ArrayList<Volunteer>();
		for(Object obj:q.list()){
			Tvolunteer tvolunteer=(Tvolunteer)obj;
			Volunteer tdd = new Volunteer();
			BeanUtils.copyProperties(tvolunteer, tdd);
			list.add(tdd);
		}
		System.out.println(q.list().size()+"===============");
		return list;
	}

	@Cacheable("checkAuth")
	public List<TeachersnoNum> marryTeachersno(String hqlString) {
	/*	Query q = this.getCurrentSession().createSQLQuery(hqlString).setResultTransformer(Transformers.aliasToBean(TeachersnoNum.class));
		List<TeachersnoNum> list=new ArrayList<TeachersnoNum>();
		for(Object obj:q.list()){
			Object[] objs=(Object[])obj;
			System.out.println(objs[0].toString());
			System.out.println(objs[1].toString());
			TeachersnoNum tdd=(TeachersnoNum)obj;
			list.add(tdd);
		}
		System.out.println(q.list().size()+"===============");
		return list;*/
		/**
		 * 此两种方法都可以查找到指定的值
		 */
		Query q = this.getCurrentSession().createSQLQuery(hqlString);
		Query q1 = this.getCurrentSession().createSQLQuery(hqlString).addScalar("teachersnoMarry",StandardBasicTypes.STRING)
				.addScalar("conditionNum",StandardBasicTypes.INTEGER);
		List<TeachersnoNum> list=new ArrayList<TeachersnoNum>();
		for(Object obj:q.list()){
			Object[] objs=(Object[])obj;
			TeachersnoNum tdd=new TeachersnoNum();
			tdd.setConditionNum(Integer.parseInt(objs[1].toString()));
			tdd.setTeachersnoMarry(objs[0].toString());
			list.add(tdd);
		}
		System.out.println(q.list().size()+"===============");
		return list;
	}

	@Cacheable("checkAuth")
	public List<Volunteer> marryByteachersnoAndrank(String hqlString,
			Map<String, Object> params) {
		Query q = this.getCurrentSession().createSQLQuery(hqlString).addEntity("t1",Tvolunteer.class);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<Volunteer> list=new ArrayList<Volunteer>();
		for(Object obj:q.list()){
			Tvolunteer tvolunteer=(Tvolunteer)obj;
			Volunteer tdd = new Volunteer();
			BeanUtils.copyProperties(tvolunteer, tdd);
			list.add(tdd);
		}
		System.out.println(q.list().size()+"++++");
		return list;
	}

	@CacheEvict(value="checkAuth", allEntries=true)
	public void deleteBystudentsnoAndteachersno(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		q.executeUpdate();
	}
	
}
