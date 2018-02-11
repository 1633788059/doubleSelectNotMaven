package com.doubleselect.dao.teacher;

import java.util.List;
import java.util.Map;

import com.doubleselect.dao.IBaseDao; 
import com.doubleselect.model.teacher.Tteacher;
import com.doubleselect.model.teacher.vo.Teacher;

public interface TeacherDao extends IBaseDao<Tteacher>{
	/**
	 * 方法描述:根据教师工号，教师姓名，性别查询教师信息返回符合条件的全部信息
	 * 方法名：getHJ
	 * @param page
	 * @param row
	 * @param teachersno 教师工号
	 * @param teachername 教师姓名
	 * @param teachersex 教师性别
	 * @return
	 * @throws Exception
	 * 返回值：List<Teacher>
	 */
	public List<Teacher> getHJ(String hql,int page, int row,Map<String,Object> param);

	/**
	 * 根据teacherId获取教师的所有信息
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<Teacher> getByteacherId(String hql, Map<String, Object> params);

	/**
	 * 根据teacherSno获取教师的所有信息
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<Teacher> getByteacherSno(String hql, Map<String, Object> params);
}
