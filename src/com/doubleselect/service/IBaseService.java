package com.doubleselect.service;

import java.util.List;
import java.util.Map;



/**
 * 类描述：此接口为service层的父接口，该层其他的接口都要继承此接口，如果需要添加其他的方法,<br />
 *       可以在要添加方法的接口中添加
 * 类名称：IBaseService
 * @version 1.0
 */
public interface IBaseService <T>{

	
	/**
	 * 方法描述：保存一条数据
	 * 方法名：save
	 * @param t
	 * @throws Exception
	 * 返回值：void
	 */
	void save(T t) throws Exception;
	
	
	/**
	 * 方法描述：删除一条数据
	 * 方法名：delete
	 * @param t
	 * @throws Exception
	 * 返回值：void
	 */
	void delete(T t) throws Exception;
	
	
	void delete(int id) throws Exception;
	
	/**
	 * 方法描述：根据对象的id批量删除数据
	 * 方法名：delete
	 * @param ids
	 * @throws Exception
	 * 返回值：void
	 */
	void delete(int [] ids) throws Exception;
	
	/**
	 * 方法描述：更新一条数据
	 * 方法名：update
	 * @param t
	 * @throws Exception
	 * 返回值：void
	 */
	void update(T t) throws Exception;
	
	/**
	 * 方法描述：根据参数，分页查询数据
	 * 方法名：getByPage

	 * @param page
	 * @param row
	 * @param params
	 * @return
	 * @throws Exception
	 * 返回值：List<T>
	 */
	List<T> getByPage(int page,int row,Map<String,Object> params) throws Exception;
	
	/**
	 * 方法描述：根据id查询数据
	 * 方法名：getById
	 * @param id
	 * @return
	 * @throws Exception
	 * 返回值：T
	 */
	T getById(int id) throws Exception;
	
	/**
	 * 方法描述：根据参数查询数据的总记录数
	 * 方法名：queryTotal
	 * @param params
	 * @return
	 * @throws Exception
	 * 返回值：int
	 */
	int queryTotal(Map<String,Object> params)throws Exception;


	
}
