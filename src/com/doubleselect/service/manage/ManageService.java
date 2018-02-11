package com.doubleselect.service.manage;

import java.util.List;

import com.doubleselect.model.manage.vo.Manage;
import com.doubleselect.service.IBaseService;


public interface ManageService extends IBaseService<Manage> {
	/**
	 * 方法描述:根据起始时间，终止时间，判别时间value识别查找管理的信息
	 * 方法名：getHJ
	 * @param page
	 * @param row
	 * @param  starttime 起始时间
	 * @param  endtime 终止时间
	 * @param  value 判别哪一个设置时间戳
	 * @return
	 * @throws Exception
	 * 返回值：List<Manage>
	 */
	public List<Manage> getByPage(int page, int row, String starttime,String endtime,String value);
	
	/**
	 * 方法描述：根据条件查询符合条件的信息的数量的多少
	 * 方法名：queryTotal
	 * @param  starttime 起始时间
	 * @param  endtime 终止时间
	 * @param  value 判别哪一个设置时间戳
	 * @return
	 * 返回值：int
	 */
	public int queryTotal(String starttime,String endtime,String value);
	
	/**
	 * 根据Id获取manage的信息
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<Manage> getById(String id);
	

	/**
	 * 根据value获取manage信息
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<Manage> getByValue(String value);
	
}
