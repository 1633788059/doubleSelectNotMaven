package com.doubleselect.service.sys;

import java.util.List;


import com.doubleselect.model.sys.Tauth;
import com.doubleselect.model.sys.vo.User;



public interface IAuthService {

	/**
	 * 
	 * 方法描述：获取管理菜单
	 * 方法名：buildMenuTree
	 * 创建人：hy
	 * 创建时间：2014-7-29 下午3:34:53
	 * @param user
	 * @return
	 * @throws Exception
	 * 返回值：String
	 */
	String buildMenuTree(User user) throws Exception;
	
	/**
	 * 
	 * 方法描述：设置权限是后去管理菜单
	 * 方法名：queryTreeMenu
	 * 创建人：hy
	 * 创建时间：2014-8-4 下午5:08:09
	 * @param rid
	 * @return
	 * 返回值：String
	 */
	String queryTreeMenu(int rid);
	
	
	/**
	 * 
	 * 方法描述：查询Tauth表中的所有数据
	 * 方法名：query
	 * 创建人：hy
	 * 创建时间：2014-8-4 下午6:04:44
	 * @return
	 * 返回值：List<Tauth>
	 */
	List<Tauth> query();
	
	/**
	 * 
	 * 方法描述：根据role id查询所有权限
	 * 方法名：queryAuthByRole
	 * 创建人：hy
	 * 创建时间：2014-8-4 下午6:05:13
	 * @param rid
	 * @return
	 * 返回值：List<Trole>
	 */
	List<Tauth> queryAuthByRole(int rid);

}