package com.doubleselect.dao.student;

import java.util.List;
import java.util.Map;

import com.doubleselect.dao.IBaseDao; 
import com.doubleselect.model.student.Tstudent;
import com.doubleselect.model.student.vo.Student;

public interface StudentDao extends IBaseDao<Tstudent>{
	/**
	 * 方法描述:根据学生学号，学生姓名，学生性别查询学生信息返回符合条件的全部信息
	 * 方法名：getHJ
	 * @param page
	 * @param row
	 * @param studentsno 学生学号
	 * @param studentname 学生姓名
	 * @param studentsex 学生性别
	 * @return
	 * @throws Exception
	 * 返回值：List<Student>
	 */
	public List<Student> getHJ(String hql,int page, int row,Map<String,Object> param);

	/**
	 * 根据studentId获取学生的所有信息
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<Student> getBystudentId(String hql, Map<String, Object> params);

	/**
	 * 根据学号获取学生信息
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<Student> getBystudentSno(String hql, Map<String, Object> params);
}
