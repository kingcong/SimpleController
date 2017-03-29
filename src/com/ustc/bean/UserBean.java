package com.ustc.bean;

import com.ustc.dao.UserDao;
import com.ustc.impl.UserDaoImpl;

/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月18日 下午7:10:30 
* 
*/
public class UserBean {
	
	// 用户名
	private String name;
	// 密码
	private String password;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * 1.登录处理逻辑		
	 * @param name	用户名
	 * @param password 密码
	 * @return
	 */
	public boolean login(String name, String password) {
		UserDao userDao = new UserDaoImpl();
		
		UserBean userBean = userDao.queryUser(name);
		
		// 数据库中没有此对象，返回false
		if (userBean == null) return false;
		
		if (userBean.password.equals(password)) {
			return true;
		}
		
		return false;
	}
}
