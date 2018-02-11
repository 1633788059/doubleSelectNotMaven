package com.doubleselect.dao.sys.impl;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.doubleselect.dao.impl.IBaseDaoImpl;
import com.doubleselect.dao.sys.IShujuzdDao;
import com.doubleselect.model.sys.Tshujuzd;

@Repository
public class IShujuzdDaoImpl extends IBaseDaoImpl<Tshujuzd> implements IShujuzdDao{
	
	
	public void batchSave(List<Tshujuzd> objList) {
		Session session = this.getCurrentSession();
		Transaction tx = session.beginTransaction();
		for(int i=0;i<objList.size();i++){
			session.save(objList.get(i));
			if(i%50 == 0){
				//只是将hibernate缓存中的数据提交到数据库
				session.flush();
				//清楚缓存内部的全部数据，及时释放出占用的内存
				session.clear();
			}
		}
		tx.commit();
	}

	
	public void batchUpdate(List<Tshujuzd> objList) {
		Session session = this.getCurrentSession();
		Transaction tx = session.beginTransaction();
		ScrollableResults sjzds = session.getNamedQuery("GetTshujuzd")
				.setCacheMode(CacheMode.IGNORE)
				.scroll(ScrollMode.FORWARD_ONLY);
		int count = 0;
		while(sjzds.next()){
			Tshujuzd sjzd = (Tshujuzd) sjzds.get(0);
		    session.save(sjzd);
		    if ( ++count % 20 == 0 ) {
		        session.flush();
		        session.clear();
		    }
		}
		tx.commit();
		session.close();
	}

}
