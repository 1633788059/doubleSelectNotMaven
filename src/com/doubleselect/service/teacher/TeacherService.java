package com.doubleselect.service.teacher;

import java.util.List;

import com.doubleselect.model.teacher.Tteacher;
import com.doubleselect.model.teacher.vo.Teacher;
import com.doubleselect.service.IBaseService;


public interface TeacherService extends IBaseService<Teacher> {
	/**
	 * 方法描述:根据教师工号，教师姓名,性别查询教师信息返回符合条件的全部信息
	 * 方法名：getByPage
	 * @param page
	 * @param row
	 * @param teachersno 学生学号
	 * @param teachername 学生姓名
	 * @param teachersex 学生性别
	 * @param teacherId 
	 * @return
	 * 返回值：List<Teacher>
	 */
	public List<Teacher> getByPage(int page, int row, String teachersno,String teachername,String teachersex, String teacherId);
	
	/**
	 * 方法描述：根据条件查询符合条件的信息的数量的多少
	 * 方法名：queryTotal
	 * @param teachersno 学生学号
	 * @param teachername 学生姓名
	 * @param teachersex 学生性别
	 * @param teacherId 
	 * @return
	 * 返回值：int
	 */
	public int queryTotal(String teachersno,String teachername,String teachersex, String teacherId);
	
	/**
	 * 根据教师teacherId查找学生的详细信息
	 * @param teacherId
	 * @return
	 */
	public List<Teacher> getByteacherId(String teacherId);
	
	/**
	 * 根据teachersno获取教师的所有信息
	 * @param teacherSno
	 * @return
	 */
	public List<Teacher> getByTeacherSno(String teacherSno);
	/**
	 * 查询所有教师的信息
	 * @return
	 */
	public List<Tteacher> findAll();
}
