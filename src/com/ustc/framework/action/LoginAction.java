package com.ustc.framework.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ustc.bean.UserBean;
import com.ustc.service.UserService;


/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月1日 上午12:20:39 
* 
*/
public class LoginAction {

	/**
	 * @see LoginAction#doPost(HttpServletRequest request, HttpServletResponse response)
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
		UserBean userInfo = userService.login(user);
		// 3. 跳转
		if (userInfo == null) {
			// 登陆失败
			uri = "fail";  
		} else {
			// 登陆成功
			request.getSession().setAttribute("userInfo", userInfo);
			// 返回uri
			uri = "success"; 
		}
		return uri;
	}
}
