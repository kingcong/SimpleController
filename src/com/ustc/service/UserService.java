package com.ustc.service;

import com.ustc.bean.UserBean;
import com.ustc.dao.UserDao;


/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月1日 上午12:28:24 
* 
*/
public class UserService {
	private UserDao ud = new UserDao();

	// 模拟登陆
	public UserBean login(UserBean user){
		return ud.login(user);
	}
	
	// 模拟注册
	public void register(UserBean user) {
		ud.register(user);
	}
}
