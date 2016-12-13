package com.ustc.util;

import com.ustc.framework.interceptor.ActionInvocation;
import com.ustc.framework.interceptor.Interceptor;

/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月8日 下午11:33:49 
* 
*/
public class ParamInterceptor implements Interceptor {

	@Override
	public Object intercept(ActionInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println(4);
		Object result = null;
		result = invocation.invoke();
		System.out.println(-4);
		return result;
	}

}
