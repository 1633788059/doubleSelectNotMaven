package com.doubleselect.model.volunteer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="tvolunteer")
@IdClass(IdMapKey.class) 
public class Tvolunteer {
	
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
	
	@Id
	@Column(name="rank",length=20,nullable = false)
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	
}
