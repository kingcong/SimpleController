package com.ustc.framework.interceptor;
/** 
* @author 王聪
* @version 创建时间：2016年12月8日 下午2:41:56 
* 拦截器接口
*/
public interface Interceptor {
	public Object intercept(ActionInvocation invocation) throws Throwable;
}
