package com.doubleselect.service.sys;

import java.util.List;

import com.doubleselect.model.sys.vo.User;



public interface IUserService {
	
	
	
	/**
	 * 
	 * 方法描述：获得用户能够访问的用户资源地址
	 * 方法名：resourceList
	 * 创建人：hy
	 * 创建时间：2014-7-29 下午2:51:35
	 * @param id
	 * @return
	 * 返回值：List<String>
	 */
	public List<String> authList(String id);
	/**
	 * 
	 * 方法描述：用户登录
	 * 方法名：login
	 * 创建人：hy
	 * 创建时间：2014-7-29 下午2:45:49
	 * @param user
	 * @return
	 * 返回值：User
	 */
	public User login(User user);
	
	/**
	 * 
	 * 方法描述：添加用户
	 * 方法名：add
	 * 创建人：hy
	 * 创建时间：2014-8-2 上午10:57:09
	 * @param user
	 * @throws Exception
	 * 返回值：void
	 */
	public void add(User user) throws Exception;
	
	/**
	 * 
	 * 方法描述：单个删除用户
	 * 方法名：delete
	 * 创建人：hy
	 * 创建时间：2014-8-2 上午10:58:37
	 * @param id
	 * 返回值：void
	 */
	public void delete(int[] ids,User user);
	
	/**
	 * 
	 * 方法描述：查询用户总数
	 * 方法名：queryTotal
	 * 创建人：hy
	 * 创建时间：2014-8-1 下午5:41:09
	 * @param name
	 * @return
	 * 返回值：long
	 */
	public long queryTotal(String name);
	
	/**
	 * 
	 * 方法描述：根据条件查询
	 * 方法名：query
	 * 创建人：hy
	 * 创建时间：2014-8-1 下午5:41:38
	 * @param page
	 * @param rows
	 * @param name
	 * @return
	 * 返回值：List<User>
	 */
	public List<User> query(int page, int rows,String name);
	/**
	 * 
	 * 方法描述：为用户授予相应的角色
	 * 方法名：updateRole
	 * @param id 用户id
	 * @param rid 对应用户id所拥有的角色id
	 * 返回值：void
	 */
	public void updateRole(int id,int[] rid) ;
	
	/**
	 * 修改密码
	 * @param user
	 */
	public void updatepass(User user);
	
	
	public User getPassword(User user);
	
	/**
	 * 根据用户名查找用户信息
	 * @param username
	 * @return
	 */
	public User findByusername(String username);
}
