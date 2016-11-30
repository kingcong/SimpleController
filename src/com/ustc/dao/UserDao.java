package com.ustc.dao;

import com.ustc.bean.UserBean;


/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月1日 上午12:18:34 
* 
*/
public class UserDao {

		// 模拟登陆
		public UserBean login(UserBean user){
			if ("wangcong".equals(user.getName()) && "123456".equals(user.getPassword()) ){
				// 登陆成功
				return user;
			}
			// 登陆失败
			return null;
		}
		
		// 模拟注册
		public void register(UserBean user) {
			System.out.println("注册成功：用户，" + user.getName());
		}
}
