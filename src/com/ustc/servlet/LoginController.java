package com.ustc.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ustc.bean.UserBean;
import com.ustc.framework.bean.ActionMapping;
import com.ustc.framework.bean.ActionMappingManager;
import com.ustc.framework.bean.Result;


/**
 * Servlet implementation class LoginController
 */
//@WebServlet("/LoginController")
public class LoginController extends HttpServlet { 
	
	private ActionMappingManager actionMappingManager;
	
	// 只执行一次  (希望启动时候执行)
	@Override
	public void init() throws ServletException {
		System.out.println("LoginController.init()");
		actionMappingManager = new ActionMappingManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			// 1.获取请求uri,得到请求路径名称 如login
			String uri = request.getRequestURI();
			// 得到login
			String actionName = uri.substring(uri.lastIndexOf("/")+1, uri.indexOf(".scaction"));
			
			System.out.println("ActionName："+actionName);
			// 2.根据路径名称，读取配置文件，得到类的全名 【com.ustc.servlet.LoginController】
			ActionMapping actionMapping = actionMappingManager.getActionMapping(actionName);
			String className = actionMapping.getClassName();
			
			// 当前请求的处理方法  【method="login"】
			String method = actionMapping.getMethod();
			
			// 3.反射：创建对象，然后调用方法，获取方法返回的标记
			Class<?> clazz = Class.forName(className);
			Object obj = clazz.newInstance();
			Method m = clazz.getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			// 调用方法后返回标记
			String returnFlag = (String)m.invoke(obj, request,response);
			
			// 4.用该标记，读取配置文件得到标记对应的页面，跳转类型
			Result result = actionMapping.getResults().get(returnFlag);
			String type = result.getType();	 // 类型
			String page = result.getValue();	// 页面
			
			// 跳转页面
			if ("redirect".equals(type)) {	// 重定向类型
				response.sendRedirect(request.getContextPath()+"/"+page);
			} else {	// 转发类型
				request.getRequestDispatcher(page).forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
