package com.doubleselect.model.match;

import java.io.Serializable;

/**
 * 此类为tmatch的主键类
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

	public IdMapKey(){
		
	}

	public IdMapKey(String studentsno, String teachersno) {
		super();
		this.studentsno = studentsno;
		this.teachersno = teachersno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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

	/**
	 * 学生学号，为volunteer主键
	 */
	private String studentsno;
	
	/**
	 * 教师工号,为volunteer主键
	 */
	private String teachersno;
	
	
}
