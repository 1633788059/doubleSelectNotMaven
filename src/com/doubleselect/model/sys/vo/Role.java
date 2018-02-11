package com.doubleselect.model.sys.vo;

/**
 * 
 * 类描述：角色类
 * 类名称：Role
 * 创建人： hy
 * 创建时间：2014-8-3 下午7:14:05
 * @version 1.0
 */
public class Role implements java.io.Serializable {

	/**
	 * 角色id
	 */
	private int id;
	/**
	 * 角色名称
	 */
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
