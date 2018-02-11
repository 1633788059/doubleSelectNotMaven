package com.doubleselect.model.image;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理表Timage,对应数据库的信息
 * @author com
 *
 */
@Entity
@Table(name="timage")
public class Timage {

	/**
	 * 主键 id
	 */
	private int id;
	
	/**
	 * 老师的工号或者学生的学号
	 */
	private String sno;
	
	@Column(name = "sno", nullable = false,length = 20,unique=true)
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	
	@Column(name = "imagename", nullable = false, length = 255)
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	
	@Column(name = "imagepath", nullable = false, length = 255)
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
	
	/**
	 * 图片名称
	 */
	private String imagename;
	
	/**
	 * 图片路径
	 */
	private String imagepath;
	
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
