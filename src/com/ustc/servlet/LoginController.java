package com.ustc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ustc.bean.UserBean;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/**
		 * 1.初始化UserBean
		 */
		UserBean userBean = new UserBean();
		userBean.setName("wangcong");
		userBean.setPassword("123456");
		
		/**
		 * 2.获取请求中的用户名和密码
		 */
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		/**
		 * 3.判断是否登录成功
		 */
		if (username.equals(userBean.getName()) && password.equals(userBean.getPassword())) {
//			this.getServletContext().getRequestDispatcher("/login_success.jsp").forward(request, response);
			response.sendRedirect("/SimpleController/login_success.jsp");

		} else {
//			this.getServletContext().getRequestDispatcher("/login_fail.jsp").forward(request, response);
			response.sendRedirect("/SimpleController/login_fail.jsp");
		}
		
		
	}

}
