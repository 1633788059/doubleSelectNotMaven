package com.doubleselect.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


/**
 * 
 * 类描述：基本控制器，作为控制器的父类
 * 类名称：BaseController
 * 创建时间：2014-7-27 下午3:39:37
 * @version 1.0
 */
public class BaseController {
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式  
		 */
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	dateFormat.setLenient(true);
    	binder.registerCustomEditor(Date.class, new CustomDateEditor(
    	dateFormat, true)); 
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

	}

}
