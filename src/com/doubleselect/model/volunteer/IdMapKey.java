package com.doubleselect.model.volunteer;

import java.io.Serializable;

/**
 * 此类为tvolunteer的主键类
 * 需要满足以下要求：
    1. 主键类必须实现序列化接口（implements Serializable）；
    2. 主键类必须有默认的public无参数的构造方法；
    3. 主键类必须覆盖equals和hashCode方法。
 */
public class IdMapKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IdMapKey(String studentsno, String teachersno, int rank) {
		super();
		this.studentsno = studentsno;
		this.teachersno = teachersno;
		this.rank = rank;
	}

	public IdMapKey(){
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rank;
		result = prime * result
				+ ((studentsno == null) ? 0 : studentsno.hashCode());
		result = prime * result
				+ ((teachersno == null) ? 0 : teachersno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdMapKey other = (IdMapKey) obj;
		if (rank != other.rank)
			return false;
		if (studentsno == null) {
			if (other.studentsno != null)
				return false;
		} else if (!studentsno.equals(other.studentsno))
			return false;
		if (teachersno == null) {
			if (other.teachersno != null)
				return false;
		} else if (!teachersno.equals(other.teachersno))
			return false;
		return true;
	}

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
	
	
}
