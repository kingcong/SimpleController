package com.ustc.framework.interceptor;
/** 
* @author 王聪
* @version 创建时间：2016年12月8日 下午2:41:06 
* 类说明 
*/
public class SecondInterceptor implements Interceptor {

	public Object intercept(ActionInvocation invocation) throws Throwable {
		System.out.println(2);
		Object result;
		result = invocation.invoke();
		System.out.println(-2);
		return result;
	}

}
