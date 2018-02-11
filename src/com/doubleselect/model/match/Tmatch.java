package com.doubleselect.model.match;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="tmatch")
@IdClass(IdMapKey.class) 
public class Tmatch {
	
	/**
	 * 学生学号，为tmatch主键
	 */
	private String studentsno;
	
	/**
	 * 教师工号,为tmatch主键
	 */
	private String teachersno;
	
	

	@Id
	@Column(name="studentsno",length=20,nullable = false)
	public String getStudentsno() {
		return studentsno;
	}

	public void setStudentsno(String studentsno) {
		this.studentsno = studentsno;
	}
	
	@Id
	@Column(name="teachersno",length=20,nullable = false)
	public String getTeachersno() {
		return teachersno;
	}

	public void setTeachersno(String teachersno) {
		this.teachersno = teachersno;
	}
}
