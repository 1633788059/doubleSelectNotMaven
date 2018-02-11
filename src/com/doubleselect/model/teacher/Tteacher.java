package com.doubleselect.model.teacher;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.hibernate.type.TrueFalseType;

/**
 * 教师信息表Tteacher,对应数据库的信息
 * @author com
 *
 */
@Entity
@Table(name="tteacher")
public class Tteacher {

	/**
	 * 主键 id
	 */
	private int id;
	/**
	 * 教师工号
	 */
	private String teachersno;
	/**
	 * 教师专业
	 */
	private String teachermajor;
	/**
	 * 学位(博士/硕士/学士)
	 */
	private String degree;
	
	/**
	 * 姓名
	 */
	private String teachername;
	
	/**
	 * 性别
	 */
	private String teachersex;
	
	/**
	 * 教师指导的最少的学生
	 */
	private int guidemin=1;
	
	/**
	 *教师指导的最大的学生
	 */
	private int guidemax;
	
	@Column(name="teachersno",unique=true,length=20,nullable = false)
	public String getTeachersno() {
		return teachersno;
	}
	public void setTeachersno(String teachersno) {
		this.teachersno = teachersno;
	}
	
	@Column(name="teachermajor",length=20,nullable = false)
	public String getTeachermajor() {
		return teachermajor;
	}
	public void setTeachermajor(String teachermajor) {
		this.teachermajor = teachermajor;
	}
	
	@Column(name="degree",length=20,nullable = false)
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	@Column(name="teachername",length=20,nullable = false)
	public String getTeachername() {
		return teachername;
	}
	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}
	
	@Column(name="teachersex",length=20,nullable = false)
	public String getTeachersex() {
		return teachersex;
	}
	public void setTeachersex(String teachersex) {
		this.teachersex = teachersex;
	}
	
	@Column(name="guidemin",length=20,nullable = false)
	public int getGuidemin() {
		return guidemin;
	}
	public void setGuidemin(int guidemin) {
		this.guidemin = guidemin;
	}
	
	@Column(name="guidemax",length=20,nullable = false)
	public int getGuidemax() {
		return guidemax;
	}
	public void setGuidemax(int guidemax) {
		this.guidemax = guidemax;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 20)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
