package com.doubleselect.service.volunteer.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doubleselect.dao.volunteer.VolunteerDao;
import com.doubleselect.model.teacher.vo.Teacher;
import com.doubleselect.model.vo.TeachersnoNum;
import com.doubleselect.model.volunteer.Tvolunteer;
import com.doubleselect.model.volunteer.vo.Volunteer;
import com.doubleselect.service.volunteer.VolunteerService;

@Service
public class VolunteerServiceImpl implements VolunteerService{
	
	@Autowired
	private VolunteerDao volunteerDao;

	
	
	public void save(Volunteer t) throws Exception {
		Tvolunteer tdd = new Tvolunteer();
		BeanUtils.copyProperties(t, tdd);
		this.volunteerDao.save(tdd);	
	}

	
	public void delete(Volunteer t) throws Exception {
		Tvolunteer tdd = new Tvolunteer();
		BeanUtils.copyProperties(t, tdd);
		this.volunteerDao.delete(tdd);
		
	}

	
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		this.volunteerDao.delete(id, Tvolunteer.class);
	}

	
	public void delete(int[] ids) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Volunteer t) throws Exception {
		// TODO Auto-generated method stub
		Tvolunteer tdd = new Tvolunteer();
		BeanUtils.copyProperties(t, tdd);
		this.volunteerDao.update(tdd);	
	}

	
	public List<Volunteer> getByPage(int page, int row, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Volunteer getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int queryTotal(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public List<Volunteer> getByPage(int page, int row,String studentsno,String teachersno,String rank){
		List<Volunteer> list_return = new ArrayList<Volunteer>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select new com.doubleselect.model.volunteer.vo.Volunteer("
				+ "t.studentsno,t.teachersno,t.rank) from Tvolunteer t ";
		if(null!=studentsno && !"".equals(studentsno)){
			hql+= " where t.studentsno=:studentsno";
			params.put("studentsno", studentsno);
			flag = true;
		}
		if(null!=teachersno && !"".equals(teachersno)){
			if(flag){
				hql += " and t.teachersno=:teachersno" ;		
				params.put("teachersno", teachersno);
			}else{
				hql += " where t.teachersno=:teachersno";
				flag = true;
				params.put("teachersno", teachersno);				
			}
		}
		if(null!=rank && !"".equals(rank)&&!("5".equals(rank))){
			if(flag){
				hql += " and t.rank=:rank" ;
				params.put("rank",Integer.parseInt(rank));							
			}else{
				hql += " where t.rank=:rank";
				params.put("rank",Integer.parseInt(rank));						
			}
		}
		if("5".equals(rank)){
			if(flag){
				hql += " and t.rank!=:rank" ;
				params.put("rank",4);							
			}else{
				hql += " where t.rank!=:rank";
				params.put("rank",4);						
			}
		}
		list_return = this.volunteerDao.getHJ(hql, page, row, params);
		return list_return;
	}

	public int queryTotal(String studentsno,String teachersno,String rank){
		// TODO Auto-generated method stub
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select count(*) from Tvolunteer t ";
		if(null!=studentsno && !"".equals(studentsno)){
			hql+= " where t.studentsno=:studentsno";
			params.put("studentsno", studentsno);
			flag = true;
		}
		if(null!=teachersno && !"".equals(teachersno)){
			if(flag){
				hql += " and t.teachersno=:teachersno" ;		
				params.put("teachersno", teachersno);
			}else{
				hql += " where t.teachersno=:teachersno";
				flag = true;
				params.put("teachersno", teachersno);				
			}
		}
		if(null!=rank && !"".equals(rank)&&!("5".equals(rank))){
			if(flag){
				hql += " and t.rank=:rank" ;
				params.put("rank",Integer.parseInt(rank));							
			}else{
				hql += " where t.rank=:rank";
				params.put("rank",Integer.parseInt(rank));						
			}
		}
		if("5".equals(rank)){
			if(flag){
				hql += " and t.rank!=:rank" ;
				params.put("rank",4);							
			}else{
				hql += " where t.rank!=:rank";
				params.put("rank",4);						
			}
		}
		return this.volunteerDao.count(hql, params).intValue();
	}


	public List<Volunteer> marry() {
		List<Volunteer> list_return = new ArrayList<Volunteer>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		/*String hqlString="select new com.doubleselect.model.volunteer.vo.Volunteer("
				+ "t.studentsno,t.teachersno,t.rank) from Tvolunteer t,"
				+ "(SELECT t1.studentsno,t1.teachersno FROM Tvolunteer t1 GROUP BY t1.studentsno,t1.teachersno HAVING COUNT(*)>1 ) t2 "
				+ "WHERE t.studentsno=t2.studentsno AND t.teachersno=t2.teachersno  AND t.rank<4 GROUP BY t.studentsno,t.teachersno";*/
	/*	String hqlString="select new com.doubleselect.model.volunteer.vo.Volunteer("
				+ "t.studentsno,t.teachersno,t.rank) from Tvolunteer t "
				+ "WHERE t.studentsno=t.studentsno AND t.teachersno=t.teachersno  AND t.rank<4 GROUP BY t.studentsno,t.teachersno";*/
		String hqlString="SELECT t1.* FROM tvolunteer t1,(SELECT t.studentsno,t.teachersno,MAX(t.rank) trank FROM tvolunteer t  GROUP BY t.studentsno,t.teachersno HAVING COUNT(*)>1 ) t2 "
				+" WHERE t1.studentsno=t2.studentsno AND t1.teachersno=t2.teachersno AND t2.trank=4 AND t1.rank<4 "
				+" GROUP BY studentsno,teachersno";
		list_return = this.volunteerDao.marry(hqlString);
		return list_return;
	}
	
	public List<TeachersnoNum> marryTeachersno() {
		List<TeachersnoNum> list_return = new ArrayList<TeachersnoNum>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hqlString=" SELECT t3.teachersno teachersnoMarry,COUNT(*) conditionNum FROM (SELECT t1.* "
				+"	FROM tvolunteer t1,(SELECT studentsno,teachersno,MAX(rank) trank FROM tvolunteer GROUP BY studentsno,teachersno"
				+"	HAVING COUNT(*)>1 ) t2 "
				+"	WHERE t1.studentsno=t2.studentsno AND t1.teachersno=t2.teachersno AND t2.trank=4 AND t1.rank<4 "
					+" GROUP BY studentsno,teachersno) t3 "
					+"	GROUP BY t3.teachersno; ";
		list_return = this.volunteerDao.marryTeachersno(hqlString);
		return list_return;
	}


	public List<Volunteer> marryByteachersnoAndrank(String teachersno,String rank) {
		List<Volunteer> list_return = new ArrayList<Volunteer>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql="SELECT t1.* FROM tvolunteer t1,(SELECT t.studentsno,t.teachersno,MAX(t.rank) trank FROM tvolunteer t  GROUP BY t.studentsno,t.teachersno HAVING COUNT(*)>1 ) t2 "
				+" WHERE t1.studentsno=t2.studentsno AND t1.teachersno=t2.teachersno AND t2.trank=4 AND t1.rank<4 ";
		if(null!=teachersno && !"".equals(teachersno)){
				hql += " and t1.teachersno=:teachersno";		
				params.put("teachersno", teachersno);
		}
		if(null!=rank && !"".equals(rank)){
			hql += " and t1.rank=:rank";		
			params.put("rank", Integer.parseInt(rank));
		}
		hql +=" GROUP BY studentsno,teachersno";
		list_return = this.volunteerDao.marryByteachersnoAndrank(hql,params);
		return list_return;
	}


	public void deleteBystudentsnoAndteachersno(String studentsno,String teachersno) {
		Map<String ,Object> params = new HashMap<String,Object>();
		String hql = "delete from Tvolunteer as t";
		boolean flag = false;
		if(null!=studentsno && !"".equals(studentsno)){
			hql+= " where t.studentsno=:studentsno";
			params.put("studentsno", studentsno);
			flag = true;
		}
		if(null!=teachersno && !"".equals(teachersno)){
			if(flag){
				hql += " and t.teachersno=:teachersno" ;		
				params.put("teachersno", teachersno);
			}else{
				hql += " where t.teachersno=:teachersno";
				flag = true;
				params.put("teachersno", teachersno);				
			}
		}
		this.volunteerDao.deleteBystudentsnoAndteachersno(hql,params);
	}
}
