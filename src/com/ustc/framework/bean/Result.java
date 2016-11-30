package com.ustc.framework.bean;
/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年11月30日 下午9:00:35 
* 
*/
public class Result {

	// 跳转的结果标记
	private String name;
	// 跳转类型，默认为转发； "redirect"为重定向
	private String type;
	// 跳转的页面
	private String value;
	
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
