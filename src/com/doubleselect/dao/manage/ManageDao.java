package com.doubleselect.dao.manage;

import java.util.List;
import java.util.Map;

import com.doubleselect.dao.IBaseDao; 
import com.doubleselect.model.manage.Tmanage;
import com.doubleselect.model.manage.vo.Manage;
public interface ManageDao extends IBaseDao<Tmanage>{
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
	 * 返回值：List<Student>
	 */
	public List<Manage> getHJ(String hql,int page, int row,Map<String,Object> param);

	/**
	 * 根据Id获取manage的信息
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<Manage> getById(String hql, Map<String, Object> params);

	/**
	 * 根据value获取manage信息
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<Manage> getByValue(String hql, Map<String, Object> params);
}
