package com.doubleselect.service.sys;

import java.util.List;

import com.doubleselect.model.sys.vo.Role;

/**
 * 
 * 类描述：角色服务接口
 * 类名称：IRoleService
 * 创建人： hy
 * 创建时间：2014-8-2 下午4:41:06
 * @version 1.0
 */
public interface IRoleService {

	/**
	 * 
	 * 方法描述：根据uid查询拥有的角色及所有的角色
	 * 方法名：curRole
	 * @param uid
	 * @return
	 * 返回值：String
	 */
	public String curRole(int uid) ;
	
	/**
	 * 
	 * 方法描述：根据用户名查询总数
	 * 方法名：queryTotal
	 * 创建人：hy
	 * 创建时间：2014-8-3 下午2:27:05
	 * @param name
	 * @return
	 * 返回值：long
	 */
	public long queryTotal(String name);
	
	/**
	 * 
	 * 方法描述：根据用户名查询
	 * 方法名：query
	 * 创建人：hy
	 * 创建时间：2014-8-3 下午2:27:40
	 * @param page
	 * @param rows
	 * @param name
	 * @return
	 * 返回值：List<Role>
	 */
	public List<Role> query(int page, int rows,String name);
	
	/**
	 * 
	 * 方法描述：更新权限
	 * 方法名：updateAuth
	 * 创建人：hy
	 * 创建时间：2014-8-4 下午6:41:31
	 * 返回值：void
	 */
    public void updateAuth(int rid ,String oaid, String aids);
	
	/**
	 * 
	 * 方法描述:删除角色
	 * 方法名：delete
	 * 创建人：hy
	 * 创建时间：2014-8-5 下午12:23:05
	 * @param ids
	 * 返回值：void
	 */
	public boolean delete(int[] ids);
	/**
	 * 
	 * 方法描述：添加角色
	 * 方法名：add
	 * 创建人：hy
	 * 创建时间：2014-8-5 下午12:25:57
	 * @param role
	 * @throws Exception
	 * 返回值：void
	 */
	public boolean add(Role role) throws Exception;
	
	/**
	 * 
	 * 方法描述：根据id查询角色
	 * 方法名：queryById
	 * 创建人：hy
	 * 创建时间：2014-8-5 下午12:26:35
	 * @param id
	 * @return
	 * 返回值：Role
	 */
	public Role queryById(int id);
	
	/**
	 * 
	 * 方法描述：修改角色
	 * 方法名：update
	 * 创建人：hy
	 * 创建时间：2014-8-5 下午12:26:39
	 * @param role
	 * 返回值：void
	 */
	public void update(Role role);
}
