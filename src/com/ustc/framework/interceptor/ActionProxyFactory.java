package com.ustc.framework.interceptor;

import java.util.ArrayList;
import java.util.List;

import com.ustc.framework.bean.InterceptorInfo;

/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月8日 下午3:08:10 
* 
*/
public class ActionProxyFactory {
	
	/**
	 * 创建ActionProxy对象
	 * @param object 对象
	 * @param inv 事件处理器
	 * @param list 拦截器列表
	 * @param actionName 事件名称
	 * @param methodName 方法名称
	 * @param executeResult 是否需要执行结果
	 * @return
	 */
	public static ActionProxy createActionProxy(Object object, List<InterceptorInfo> list,String actionName, String methodName, boolean executeResult) {

        ActionProxy proxy = new ActionProxy(object, list,actionName,methodName,executeResult);
        return proxy;
    }
	
}
