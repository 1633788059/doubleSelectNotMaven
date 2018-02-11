package com.doubleselect.model.volunteer.vo;

public class Volunteer {
	
	/**
	 * 学生学号，为volunteer主键
	 */
	private String studentsno;
	
	/**
	 * 教师工号,为volunteer主键
	 */
	private String teachersno;
	
	/**
	 * 填写志愿排名，判别为学生选导师还是导师选学生
	 * 1. 表示意思为学生选择导师的第一志愿
	 * 2. 表示意思为学生选择导师的第二志愿
	 * 3. 表示意思为学生选择导师的第三志愿
	 * 4. 表示的意思为导师选择学生
	 */
	private int rank;
	

	public String getStudentsno() {
		return studentsno;
	}

	public void setStudentsno(String studentsno) {
		this.studentsno = studentsno;
	}

	public String getTeachersno() {
		return teachersno;
	}

	public void setTeachersno(String teachersno) {
		this.teachersno = teachersno;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Volunteer(){
		
	}

	public Volunteer(String studentsno, String teachersno, int rank) {
		super();
		this.studentsno = studentsno;
		this.teachersno = teachersno;
		this.rank = rank;
	}
}
