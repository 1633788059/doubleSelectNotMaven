package com.doubleselect.util;

/**
 * 
 * 类描述：判断查询条件是否为空
 * 类名称：ConditionValidate
 * 创建人： hy
 * 创建时间：2014-8-23 上午10:36:54
 * @version 1.0
 */
public class ConditionValidate {

	public static boolean isEmpty(Object obj){
		if(obj instanceof String){
			obj = ((String) obj).trim();
		}
		if(obj == null || "".equals(obj)){
			return true;
		}
		return false;
	}
}
