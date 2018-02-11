package com.doubleselect.model.manage.vo;

import java.util.Date;

public class Manage {
	
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Manage() {
		
	}
	
	public Manage(int id, long starttime, long endtime, String value,
			String note) {
		super();
		this.id = id;
		this.starttime = starttime;
		this.endtime = endtime;
		this.value = value;
		this.note = note;
	}

	public long getStarttime() {
		return starttime;
	}

	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}

	public long getEndtime() {
		return endtime;
	}

	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 注释,可为空
	 */
	private String note;
	
}
