package com.doubleselect.util.FormTool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 类描述：防止表单重复提交 （自定义注解实现，标注要实现的方法上）
 * 类名称：Token
 * 创建人： hy
 * 创建时间：2014-7-27 上午9:51:24
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
 
     boolean save() default false ;
 
     boolean remove() default false ;
}
