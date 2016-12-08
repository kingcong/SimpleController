package com.ustc.framework.interceptor;
/** 
* @author 王聪
* @version 创建时间：2016年12月8日 下午2:40:33 
* 类说明 
*/
public class FirstInterceptor implements Interceptor {

	public Object intercept(ActionInvocation invocation) throws Throwable {
		System.out.println(1);
		Object result = null;
		result = invocation.invoke();
		System.out.println(-1);
		return result;
	}

}
