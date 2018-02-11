package com.doubleselect.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.doubleselect.dao.IBaseDao;



/**
 * 类描述：DAO层，CRUD公用方法
 * 类名称：IBaseDaoImpl
 * 创建人： zyy
 * 创建时间：2014-8-9 下午5:01:38
 * @version 1.0
 */
@Repository
/*@Cacheable("checkAuth")*/
public class IBaseDaoImpl<T>  implements IBaseDao<T>  {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 获得当前事物的session
	 * 
	 * @return org.hibernate.Session
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@CacheEvict(value="checkAuth", allEntries=true)
	public Serializable save(T o) {
		if (o != null) {
			return this.getCurrentSession().save(o);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	@Cacheable("checkAuth")
	public T get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Cacheable({"checkAuth","shiroAuthorizationCache"})
	public T get(String hql, Map<String, Object> params) {
		//System.out.println("到达shiroAuthorizationCache");
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		@SuppressWarnings("unchecked")
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@CacheEvict(value="checkAuth", allEntries=true)
	public void delete(T o) {
		if (o != null) {
			this.getCurrentSession().delete(o);
		}
	}

	@CacheEvict(value="checkAuth", allEntries=true)
	public void delete(int id, Class<T> clazz) throws Exception {
		if(id!=0 && !"".equals(clazz.getName())){
			String hql = "delete from " + clazz.getName() + " as t where t.id=" + id;
			this.executeHql(hql);
		}else{
			throw new Exception("删除失败！");
		}
		
	}
	
	
	@CacheEvict(value="checkAuth", allEntries=true)
	public void delete(int[] ids,Class<T> clazz) throws Exception {
		int count = ids.length;
		int temp = 0;
		if(count!=0){
			for(int i=0;i<count;i++){
				String hql = "delete from " + clazz.getName() + " as t where t.id=" + ids[i];
				if(this.executeHql(hql)==0){
					throw new Exception("删除失败！");
				}
				temp++;
			}
			if(temp!=count){
				throw new Exception("删除失败！");
			}

		}
		
		
	}
	
	@CacheEvict(value={"checkAuth","shiroAuthorizationCache"}, allEntries=true)
	public void update(T o) {
		if (o != null) {
			this.getCurrentSession().update(o);
		}
	}

	@CacheEvict(value="checkAuth", allEntries=true)
	public void saveOrUpdate(T o) {
		if (o != null) {
			this.getCurrentSession().saveOrUpdate(o);
		}
	}

	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<T> find(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
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
	public List<T> find(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Cacheable("checkAuth")
	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	@Cacheable("checkAuth")
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	@CacheEvict(value="checkAuth", allEntries=true)
	public int executeHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	@CacheEvict(value="checkAuth", allEntries=true)
	public int executeHql(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<Object[]> findBySql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<Object[]> findBySql(String sql, int page, int rows) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<Object[]> findBySql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Cacheable("checkAuth")
	public List<Object[]> findBySql(String sql, Map<String, Object> params, int page, int rows) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@CacheEvict(value="checkAuth", allEntries=true)
	public int executeSql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return q.executeUpdate();
	}

	@CacheEvict(value="checkAuth", allEntries=true)
	public int executeSql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	@Cacheable("checkAuth")
	public BigInteger countBySql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return (BigInteger) q.uniqueResult();
	}

	@Cacheable("checkAuth")
	public BigInteger countBySql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (BigInteger) q.uniqueResult();
	}
}
