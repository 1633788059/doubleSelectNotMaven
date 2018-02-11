package com.doubleselect.dao.match;

import java.util.List;
import java.util.Map;

import com.doubleselect.dao.IBaseDao;
import com.doubleselect.model.match.Tmatch;
import com.doubleselect.model.match.vo.Match;


public interface MatchDao extends IBaseDao<Tmatch>{
	
	/**
	 * 根据学生学号，教师工号所需要的信息
	 * @param hql
	 * @param page
	 * @param row
	 * @param param
	 * @return
	 */
	public List<Match> getHJ(String hql,int page, int row,Map<String,Object> param);


}
