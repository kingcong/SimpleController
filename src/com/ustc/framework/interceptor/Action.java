package com.ustc.framework.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* @author 王聪
* @version 创建时间：2016年12月8日 下午2:43:38 
* 类说明 
*/
public interface Action {
	
	public Object execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException;
}
