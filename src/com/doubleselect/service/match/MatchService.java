package com.doubleselect.service.match;

import java.util.List;

import com.doubleselect.model.match.vo.Match;
import com.doubleselect.model.volunteer.vo.Volunteer;
import com.doubleselect.service.IBaseService;


public interface MatchService extends IBaseService<Match> {

	/**
	 * 根据学生学号，老师工号查询匹配的信息
	 * @param page 表示第几页
	 * @param row  表示一页有多少数据
	 * @param studentsno  学生学号
	 * @param teachersno  老师工号
	 * @return
	 */
	public List<Match> getByPage(int page, int row,String studentsno,String teachersno);
	
	/**
	 * 查询符合条件的总数据的个数
	 * @param studentsno
	 * @param teachersno
	 * @return
	 */
	public int queryTotal(String studentsno,String teachersno);
	
 }
