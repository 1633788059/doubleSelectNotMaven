package com.doubleselect.model.sys.vo;

/**
 * 
 * 类描述：用户类
 * 类名称：User
 * 创建人： hy
 * 创建时间：2014-8-3 下午7:14:49
 * @version 1.0
 */
public class User implements java.io.Serializable {

	/**
	 * 唯一标志
	 */
	private int id;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 密码
	 */
	private String pwd;
	/**
	 * 对应的角色id
	 */
	private String roleIds;
	/**
	 * 对应拥有角色的名字
	 */
	private String roleNames;
	
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	
}
