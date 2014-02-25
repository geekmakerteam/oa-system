/**
 * 
 */
package com.dao;


import com.entity.User;

/**
 * UserDao Interface
 * @author xue__lang
 */
public interface UserDao extends BaseDao<User, Integer>{
	/**
	 * 判断此用户名的用户书否存在
	 * @param  username 姓名
	 * @return boolean true/存在  false/不存在
	 */
	public boolean isExist(String username);
	
	/**
	 * 根据用户民查找用户
	 * @param username 用户名
	 * @return 用户
	 */
	public User getByName(String username);

	
}
