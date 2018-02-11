package com.doubleselect.service.volunteer;

import java.util.List;

import com.doubleselect.model.vo.TeachersnoNum;
import com.doubleselect.model.volunteer.vo.Volunteer;
import com.doubleselect.service.IBaseService;


public interface VolunteerService extends IBaseService<Volunteer> {

	/**
	 * 根据学生学号，老师工号，第几志愿查询填报志愿信息
	 * @param page 表示第几页
	 * @param row  表示一页有多少数据
	 * @param studentsno  学生学号
	 * @param teachersno  老师工号
	 * @param rank  表示志愿信息
	 * @return
	 */
	public List<Volunteer> getByPage(int page, int row,String studentsno,String teachersno,String rank);
	
	/**
	 * 查询符合条件的总数据的个数
	 * @param studentsno
	 * @param teachersno
	 * @param rank
	 * @return
	 */
	public int queryTotal(String studentsno,String teachersno,String rank);

	/**
	 * 导师与研究生进行匹配符合条件的所有信息
	 * @return
	 */
	public List<Volunteer> marry();
	
	/**
	 * 导师与研究生进行匹配，查找符合条件的导师的第几志愿的信息
	 * @param teachersno
	 * @return
	 */
	public List<Volunteer> marryByteachersnoAndrank(String teachersno,String rank);
	
	/**
	 * 获取匹配成功的导师的信息
	 * @return
	 */
	public List<TeachersnoNum> marryTeachersno() ;
	
	/**
	 * 删除学生的所有志愿的信息或者所有教师的志愿的信息
	 * @param studentsno
	 */
	public void deleteBystudentsnoAndteachersno(String studentsno,String teachersno);
	
 }
