package com.doubleselect.model.manage;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 管理表Tmanage,对应数据库的信息
 * @author com
 *
 */
@Entity
@Table(name="tmanage")
public class Tmanage {

	/**
	 * 主键 id
	 */
	private int id;
	
	/**
	 * 起始时间,用时间戳表示
	 */
	private long starttime;
	
	/**
	 * 终止时间，用时间戳表示
	 */
	private long endtime;
	
	/**
	 * 判别那个区域设置时间段
	 */
	private String value;
	
	/**
	 * 注释,可为空
	 */
	private String note;

	@Column(name = "starttime", nullable = false, length = 11)
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	
	@Column(name = "endtime", nullable = false, length = 11)
	public long getEndtime() {
		return endtime;
	}
	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}
	
	@Column(name = "value", nullable = false, length = 255,unique=true)
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Column(name = "note", nullable = false, length = 255)
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
