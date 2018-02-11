package com.doubleselect.service.student;

import java.util.List;

import com.doubleselect.model.student.Tstudent;
import com.doubleselect.model.student.vo.Student;
import com.doubleselect.service.IBaseService;


public interface StudentService extends IBaseService<Student> {
	/**
	 * 方法描述:根据学生学号，学生姓名，学生性别查询学生信息返回符合条件的全部信息
	 * 方法名：getByPage
	 * @param page
	 * @param row
	 * @param studentsno 学生学号
	 * @param studentname 学生姓名
	 * @param studentsex 学生性别
	 * @param studentId 
	 * @return
	 * 返回值：List<Student>
	 */
	public List<Student> getByPage(int page, int row, String studentsno,String studentname,String studentsex, String studentId);
	
	/**
	 * 方法描述：根据条件查询符合条件的信息的数量的多少
	 * 方法名：queryTotal
	 * @param studentsno 学生学号
	 * @param studentname 学生姓名
	 * @param studentsex 学生性别
	 * @param studentId 
	 * @return
	 * 返回值：int
	 */
	public int queryTotal(String studentsno,String studentname,String studentsex, String studentId);
	
	/**
	 * 根据学生id查找学生的详细信息
	 * @param studentId
	 * @return
	 */
	public List<Student> getByStudentId(String studentId);
	
	/**
	 *根据学生学号查找学生信息
	 * @param studentSno
	 * @return
	 */
	public List<Student> getByStudentSno(String studentSno);
	
	/**
	 * 查找所有学生的信息
	 * @return
	 */
	public List<Tstudent> findAll();
}
