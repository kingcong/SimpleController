package com.ustc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import com.sun.org.apache.xalan.internal.xsltc.cmdline.Transform;
import com.ustc.bean.UserBean;
import com.ustc.framework.bean.ActionMapping;
import com.ustc.framework.bean.ActionMappingManager;
import com.ustc.framework.bean.Result;
import com.ustc.framework.interceptor.Action;
import com.ustc.framework.interceptor.ActionProxy;
import com.ustc.framework.interceptor.ActionProxyFactory;
import com.ustc.util.TransformUtil;


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
			
			System.out.println("Inte:"+actionMapping.getInterceptors());
			
			// 当前请求的处理方法  【method="login"】
//			String method = actionMapping.getMethod();
			String method = "execute";
			
			// 3.反射：创建对象，然后调用方法，获取方法返回的标记
			Class<?> clazz = Class.forName(className);
			Object obj = clazz.newInstance();
			Method m = clazz.getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			// 调用方法后返回标记
//			String returnFlag = (String)m.invoke(obj, request,response);
//			ActionProxy handler = new ActionProxy(obj, null, method, method, false);
			ActionProxy handler = ActionProxyFactory.createActionProxy(obj, actionMapping.getInterceptors(), actionName, method, false);
			Action proxyObj = (Action)Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
//			String returnFlag = (String)m.invoke(proxyObj, HttpServletRequest.class,HttpServletResponse.class);
			String returnFlag = (String)proxyObj.execute(request, response);
			
			System.out.println("returnFlag:"+returnFlag);
			// 4.用该标记，读取配置文件得到标记对应的页面，跳转类型
			Result result = actionMapping.getResults().get(returnFlag);
			String type = result.getType();	 // 类型
			String page = result.getValue();	// 页面
					
			
			String t=Thread.currentThread().getContextClassLoader().getResource("").getPath();
			int num=t.indexOf(".metadata");
			String path=t.substring(1,num)+"SimpleController/WebContent/";
			
			// 1. 得到解析器
			SAXReader reader = new SAXReader();
			// 得到src/mystruts.xml  文件流
			InputStream inStream = this.getClass().getResourceAsStream("/controller.xml");
			// 2. 加载文件
			Document doc = reader.read(inStream);	
			
			// 跳转页面
			if ("redirect".equals(type)) {	// 重定向类型
				if (page.contains(".xml")) {
//					File file = new File(path+page);
//					File f = new File(getServletContext().getRealPath("alerts/rtp_bcklg_mail.html"));
					File file = new File(request.getServletContext().getRealPath(page));
					Document document = new SAXReader().read(file);
					String xslPath = request.getServletContext().getRealPath("pages/success_view.xsl");
					TransformUtil tUtil = new TransformUtil();
					String htmlStr = tUtil.transformXmlToHtml(document, xslPath);
					System.out.println("str="+htmlStr);
					response.getWriter().write(htmlStr);	
				} else {
					response.sendRedirect(request.getContextPath()+"/"+page);
				}
			} else {	// 转发类型
				if (page.contains(".xml")) {
					File file = new File(request.getServletContext().getRealPath(page));
					Document document = new SAXReader().read(file);
					String xslPath = request.getServletContext().getRealPath("pages/success_view.xsl");
					TransformUtil tUtil = new TransformUtil();
					String htmlStr = tUtil.transformXmlToHtml(document, xslPath);
					System.out.println("str="+htmlStr);
					response.getWriter().write(htmlStr);
				} else {
					request.getRequestDispatcher(page).forward(request, response);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public String getCurrentPath(){  
	       //取得根目录路径  
	       String rootPath=getClass().getResource("/").getFile().toString();  
	       //当前目录路径  
	       String currentPath1=getClass().getResource(".").getFile().toString();  
	       String currentPath2=getClass().getResource("").getFile().toString();  
	       //当前目录的上级目录路径  
	       String parentPath=getClass().getResource("../").getFile().toString();  
	          
	       return rootPath;         
	   }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
