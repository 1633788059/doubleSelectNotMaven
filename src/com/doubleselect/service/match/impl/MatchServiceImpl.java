package com.doubleselect.service.match.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doubleselect.dao.match.MatchDao;
import com.doubleselect.model.match.Tmatch;
import com.doubleselect.model.match.vo.Match;
import com.doubleselect.service.match.MatchService;

@Service
public class MatchServiceImpl implements MatchService{
	
	@Autowired
	private MatchDao matchDao;

	
	
	public void save(Match t) throws Exception {
		Tmatch tdd = new Tmatch();
		BeanUtils.copyProperties(t, tdd);
		this.matchDao.save(tdd);	
	}

	
	public void delete(Match t) throws Exception {
		Tmatch tdd = new Tmatch();
		BeanUtils.copyProperties(t, tdd);
		this.matchDao.delete(tdd);
		
	}

	
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		this.matchDao.delete(id, Tmatch.class);
	}

	
	public void delete(int[] ids) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	public void update(Match t) throws Exception {
		// TODO Auto-generated method stub
		Tmatch tdd = new Tmatch();
		BeanUtils.copyProperties(t, tdd);
		this.matchDao.update(tdd);	
	}

	
	public List<Match> getByPage(int page, int row, Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Match getById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int queryTotal(Map<String, Object> params) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public List<Match> getByPage(int page, int row,String studentsno,String teachersno){
		List<Match> list_return = new ArrayList<Match>();
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select new com.doubleselect.model.match.vo.Match("
				+ "t.studentsno,t.teachersno) from Tmatch t ";
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
		
		list_return = this.matchDao.getHJ(hql, page, row, params);
		return list_return;
	}

	public int queryTotal(String studentsno,String teachersno){
		// TODO Auto-generated method stub
		Map<String ,Object> params = new HashMap<String,Object>();
		boolean flag = false;
		String hql = "select count(*) from Tmatch t ";
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
		return this.matchDao.count(hql, params).intValue();
	}
}
