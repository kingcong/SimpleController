package com.ustc.util;

import java.util.Date;

import com.ustc.dao.LogWriterDao;
import com.ustc.framework.bean.LogInfo;
import com.ustc.framework.interceptor.ActionInvocation;
import com.ustc.framework.interceptor.Interceptor;
import com.ustc.impl.LogWriterDaoImpl;

/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月7日 下午10:55:58 
* 
*/
public class LogWriter implements Interceptor{
	
	/**
	 * 记录log信息
	 */
	public void log(ActionInvocation invocation,Object result) {
		LogInfo logInfo = new LogInfo();
		logInfo.setName(invocation.getActionName());
		logInfo.setType(invocation.getActionType());
		if (result == null) {	// 开始之前
			logInfo.setStartTime(new Date());
		} else {	// 开始之后
			logInfo.setEndTime(new Date());
			logInfo.setResult((String)result);
		}
		System.out.println("LogInfo"+logInfo.getName()+logInfo.getType()+logInfo.getStartTime()+logInfo.getEndTime());
		LogWriterDao logWriter = new LogWriterDaoImpl();
		logWriter.addLog(logInfo);
	}

	@Override
	public Object intercept(ActionInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("LogWriter拦截器执行之前...");
		log(invocation,null);	// 记录日志
		Object result;
		result = invocation.invoke();
		System.out.println("LogWriter拦截器执行之后...");
		log(invocation, result);	// 记录日志
		return result;
	}

}
