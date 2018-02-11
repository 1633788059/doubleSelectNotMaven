package com.doubleselect.dao.sys;

import java.util.List;

import com.doubleselect.dao.IBaseDao;
import com.doubleselect.model.sys.Tshujuzd;

public interface IShujuzdDao extends IBaseDao<Tshujuzd>{
	/**
	 * 
	 * 方法描述：批量保存对象
	 * 方法名：batchSave
	 * 创建人：hy
	 * 创建时间：2014-8-24 下午5:14:25
	 * @param objList
	 * 返回值：void
	 */
	public void batchSave(List<Tshujuzd> objList);
	
	
	/**
	 * 
	 * 方法描述：批量更新对象
	 * 方法名：batchUpdate
	 * 创建人：hy
	 * 创建时间：2014-8-24 下午5:14:45
	 * @param objList
	 * 返回值：void
	 */
	public void batchUpdate(List<Tshujuzd> objList);
}
