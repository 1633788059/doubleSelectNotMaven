package com.doubleselect.dao.volunteer;

import java.util.List;
import java.util.Map;

import com.doubleselect.dao.IBaseDao;
import com.doubleselect.model.vo.TeachersnoNum;
import com.doubleselect.model.volunteer.Tvolunteer;
import com.doubleselect.model.volunteer.vo.Volunteer;


public interface VolunteerDao extends IBaseDao<Tvolunteer>{
	
	/**
	 * 根据学生学号，教师工号，第几志愿查询所需要的信息
	 * @param hql
	 * @param page
	 * @param row
	 * @param param
	 * @return
	 */
	public List<Volunteer> getHJ(String hql,int page, int row,Map<String,Object> param);

	/**
	 * 获取匹配成功的全部信息
	 */
	public List<Volunteer> marry(String hqlString);

	/**
	 * 获取匹配成功的教师的工号
	 * @param hqlString
	 * @return
	 */
	public List<TeachersnoNum> marryTeachersno(String hqlString);
	
	/**
	 * 获取匹配成功的选择导师的满足条件的所有信息
	 * @param hqlString
	 * @param params
	 * @return
	 */
	public List<Volunteer> marryByteachersnoAndrank(String hqlString,
			Map<String, Object> params);
	
	/**
	 * 删除学生的选择导师的信息或者教师选择导师的信息
	 * @param hql
	 * @param params
	 */
	public void deleteBystudentsnoAndteachersno(String hql,
			Map<String, Object> params);

}
