package com.doubleselect.model.student;

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
 * 学生信息表Tstudent,对应数据库的信息
 * @author com
 *
 */
@Entity
@Table(name="tstudent")
public class Tstudent {

	/**
	 * 主键 id
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
	 * 毕业学校
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, length = 20)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="studentsno",unique=true,length=20,nullable = false)
	public String getStudentsno() {
		return studentsno;
	}
	public void setStudentsno(String studentsno) {
		this.studentsno = studentsno;
	}
	
	@Column(name="studentmajor",length=20,nullable = false)
	public String getStudentmajor() {
		return studentmajor;
	}
	public void setStudentmajor(String studentmajor) {
		this.studentmajor = studentmajor;
	}
	
	@Column(name="finaldegree",length=20,nullable = false)
	public String getFinaldegree() {
		return finaldegree;
	}
	public void setFinaldegree(String finaldegree) {
		this.finaldegree = finaldegree;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", nullable = false)
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	@Column(name="expertise",nullable=false,length=20)
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	
	@Column(name="graduation",nullable=false,length=20)
	public String getGraduation() {
		return graduation;
	}
	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}
	
	@Column(name="studentname",nullable=false,length=20)
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	
	@Column(name="studentsex",nullable=false,length=20)
	public String getStudentsex() {
		return studentsex;
	}
	public void setStudentsex(String studentsex) {
		this.studentsex = studentsex;
	}
}
