package com.ustc.framework.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.sun.swing.internal.plaf.metal.resources.metal;
import com.ustc.framework.bean.ActionMapping;
import com.ustc.framework.bean.InterceptorInfo;

/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月8日 下午3:07:54 
* 
*/
public class ActionProxy implements InvocationHandler {

	private Object obj;		// 调用对象
	private Object[] args;		// 参数
	private Method method;		// 调用方法
	
	// 调用方式
	private ActionInvocation invocation;
	
	/**
	 * 构造方法
	 * @param object	对象
	 * @param interceptors 拦截器列表
	 * @param actionName 事件名称
	 * @param methodName 方法名称
	 * @param executeResult 是否返回执行结果
	 */
	public ActionProxy(Object object, List<InterceptorInfo> interceptors,String actionName, String methodName, boolean executeResult) {
		this.obj = object;
		this.invocation = new ActionInvocation(this,interceptors,actionName,methodName);
	}
	
	/**
	 * 获取Action对象
	 * @return action
	 */
    public Object getAction() {
		return null;
    }

    /**
     * 获取Action名字
     * @return
     */
    public String getActionName() {
		return null;
    }

    /**
     * 获取ActionMapping配置文件
     * @return
     */
    public ActionMapping getConfig() {
		return null;
    	
    }

    public void setExecuteResult(boolean executeResult) {
    	
    }

    public boolean getExecuteResult() {
		return false;
    	
    }

    public ActionInvocation getInvocation() {
		return null;
    }

    public boolean isMethodSpecified() {
		return false;
    }
    

	public Method getMethod() {
		return method;
	}
	

	/**
	 * @param args the args to set
	 */
	public Object[] getArgs() {
		return args;
	}

	
	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		this.method = method;
		this.args = args;
		
		System.out.println("before");
//		Object result = method.invoke(obj, args);
		Object result = invocation.invoke();
		System.out.println("after");
		return result;
	}

}
