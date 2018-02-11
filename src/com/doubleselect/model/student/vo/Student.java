package com.doubleselect.model.student.vo;

import java.util.Date;

public class Student {
	
	/**
	 * 学生id 主键
	 */
	private int id;
	
	/**
	 * 学号
	 */
	private String studentsno;
	/**
	 * 专业
	 */
	private String studentmajor;
	/**
	 * 最后学历
	 */
	private String finaldegree;
	/**
	 * 最后毕业学校
	 */
	private String graduation;
	/**
	 * 毕业时间
	 */
	private Date time;
	/**
	 * 专业特长
	 */
	private String expertise;
	/**
	 * 姓名
	 */
	private String studentname;
	/**
	 * 性别
	 */
	private String studentsex;
	public Student(){
		
	}
	public Student(int id, String studentsno,String studentname,String studentsex, String studentmajor,
			String finaldegree, String graduation, Date time, String expertise) {
		super();
		this.id = id;
		this.studentname=studentname;
		this.studentsex=studentsex;
		this.studentsno = studentsno;
		this.studentmajor = studentmajor;
		this.finaldegree = finaldegree;
		this.graduation = graduation;
		this.time = time;
		this.expertise = expertise;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentsno() {
		return studentsno;
	}
	public void setStudentsno(String studentsno) {
		this.studentsno = studentsno;
	}
	public String getStudentmajor() {
		return studentmajor;
	}
	public void setStudentmajor(String studentmajor) {
		this.studentmajor = studentmajor;
	}
	public String getFinaldegree() {
		return finaldegree;
	}
	public void setFinaldegree(String finaldegree) {
		this.finaldegree = finaldegree;
	}
	public String getGraduation() {
		return graduation;
	}
	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public String getStudentsex() {
		return studentsex;
	}
	public void setStudentsex(String studentsex) {
		this.studentsex = studentsex;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
}
