package com.ustc.framework.bean;
/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月8日 下午6:50:08 
* 
*/
public class InterceptorInfo {
	
	private String name;	// 拦截器名称
	private String className;	// 类名
	private String methodName;	// 方法名称
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}
	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}
