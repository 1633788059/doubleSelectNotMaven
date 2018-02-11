package com.doubleselect.model.teacher.vo;

import java.util.Date;

public class Teacher {
	/**
	 * 主键 id
	 */
	private int id;
	/**
	 * 教师工号
	 */
	private String teachersno;
	
	/**
	 * 姓名
	 */
	private String teachername;
	

	/**
	 * 性别
	 */
	private String teachersex;
	
	/**
	 * 教师专业
	 */
	private String teachermajor;
	
	/**
	 * 教师指导的最少的学生
	 */
	private int guidemin=1;
	
	public Teacher(){
		
	}
	public Teacher(int id, String teachersno, String teachername,
			String teachersex, String teachermajor, int guidemin, int guidemax,
			String degree) {
		super();
		this.id = id;
		this.teachersno = teachersno;
		this.teachername = teachername;
		this.teachersex = teachersex;
		this.teachermajor = teachermajor;
		this.guidemin = guidemin;
		this.guidemax = guidemax;
		this.degree = degree;
	}

	/**
	 *教师指导的最大的学生
	 */
	private int guidemax;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeachersno() {
		return teachersno;
	}

	public void setTeachersno(String teachersno) {
		this.teachersno = teachersno;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getTeachersex() {
		return teachersex;
	}

	public void setTeachersex(String teachersex) {
		this.teachersex = teachersex;
	}

	public String getTeachermajor() {
		return teachermajor;
	}

	public void setTeachermajor(String teachermajor) {
		this.teachermajor = teachermajor;
	}

	public int getGuidemin() {
		return guidemin;
	}

	public void setGuidemin(int guidemin) {
		this.guidemin = guidemin;
	}

	public int getGuidemax() {
		return guidemax;
	}

	public void setGuidemax(int guidemax) {
		this.guidemax = guidemax;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * 学位(博士/硕士/学士)
	 */
	private String degree;
	
}
