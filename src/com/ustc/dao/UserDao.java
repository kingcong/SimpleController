package com.ustc.dao;

import java.sql.Connection;

import com.ustc.bean.UserBean;

/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月18日 下午7:06:11 
* 
*/
public interface UserDao {
//	/**
//	 * 1.负责打开 MySQL 数据库连接
//	 * @return
//	 */
//	public Connection openDBConnection();  
//	
//	/**
//	 * 2.负责关闭 MySQL 数据库连接
//	 * @return
//	 */
//	public boolean closeDBConnection();  
	
	/**
	 * 3.负责根据参数查询对象表记录
	 * @param userName
	 * @return
	 */
	public UserBean queryUser(String userName);
	
	/**
	 * 4.负责根据参数增加对象表记录
	 * @param u
	 */
	public boolean insertUser(UserBean u);
	
	/**
	 * 5.负责根据参数修改对象表记录
	 * @param u
	 */
	public boolean updateUser(UserBean u);  
	
	/**
	 * 6.负责根据参数删除对象表记录
	 * @param u
	 */
	public boolean deleteUser(UserBean u); 
}
 