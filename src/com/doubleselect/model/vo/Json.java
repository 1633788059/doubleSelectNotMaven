package com.doubleselect.model.vo;

/**
 * 
 * 类描述：后台向前台换回的json对象
 * 类名称：Json
 * 创建人： hy
 * 创建时间：2014-7-27 上午11:48:12
 * @version 1.0
 */
public class Json {
	private boolean success = false;

	private String msg = "";

	private Object obj = null;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
