package com.doubleselect.model.match.vo;

public class Match {
	
	/**
	 * 学生学号，为volunteer主键
	 */
	private String studentsno;
	
	/**
	 * 教师工号,为volunteer主键
	 */
	private String teachersno;
	
	

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

	public Match(){
		
	}

	public Match(String studentsno, String teachersno) {
		super();
		this.studentsno = studentsno;
		this.teachersno = teachersno;
	}
}
