package com.ustc.framework.interceptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/** 
* @author 王聪
* @version 创建时间：2016年12月8日 下午2:39:49 
* 类说明 
*/
import java.util.ArrayList;
import java.util.List;

import com.ustc.framework.bean.InterceptorInfo;
public class ActionInvocation {
	List<Interceptor> interceptors = new ArrayList<Interceptor>();
	List<InterceptorInfo> infos = new ArrayList<InterceptorInfo>();
	
	private String actionName;	// action名称
	private String actionType;	// action类型
	
	public String getActionName() {
		return actionName;
	}
	
	public String getActionType() {
		return actionType;
	}
	
	
	int index = -1;
//	Action a = new Action();
	ActionProxy actionProxy;
	
	/**
	 * 构造方法
	 * @param actionProxy ActionProxy参数
	 * @param interceptors 拦截器列表
	 * @throws Exception 
	 */
	public ActionInvocation(ActionProxy actionProxy,List<InterceptorInfo> interceptors,String actionName, String actionType) {

		revertInterceptors(interceptors);
		this.actionProxy = actionProxy;
		this.infos = interceptors;
		this.actionName = actionName;
		this.actionType = actionType;
	}
	
	// 转换
	private void revertInterceptors(List<InterceptorInfo> infos) {
		
		for (InterceptorInfo interceptorInfo : infos) {
			Class<?> clazz;
			try {
				clazz = Class.forName(interceptorInfo.getClassName());
				Object obj = clazz.newInstance();
				this.interceptors.add((Interceptor) obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}
	
	public Object invoke() throws Throwable {
		index ++;
		Object result = null;
		if(index >= this.interceptors.size()) {
//				result = method.invoke();
//			System.out.println("我被执行了");
			Method method = actionProxy.getMethod();
			Object[] args = actionProxy.getArgs();
			Object object = actionProxy.getObj();
			result = method.invoke(object, args);
			
		} else {
			
			result = this.interceptors.get(index).intercept(this);
		}
		return result;
	}
}
