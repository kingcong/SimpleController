package com.ustc.framework.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ustc.bean.UserBean;
import com.ustc.service.UserService;

/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月1日 上午1:04:57 
* 
*/
public class RegisterAction {
	/**
	 * @see RegisterAction#register(HttpServletRequest request, HttpServletResponse response)
	 * @return 根据返回的uri对象即是查找结果集的name
	 */
	public Object login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Object uri = null;

		// 1. 获取请求数据，封装
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		UserBean user = new UserBean();
		user.setName(name);
		user.setPassword(password);

		// 2. 调用Service
		UserService userService = new UserService();
		userService.register(user);
		uri = "success";
		
		return uri;
	}
}
