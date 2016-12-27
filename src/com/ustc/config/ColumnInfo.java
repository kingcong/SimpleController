package com.ustc.config;
/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月26日 下午2:47:07 
* 
*/
public class ColumnInfo {
	
	private String name;
	private String column;
	private String type;
	private String lazy;
	
	
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
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(String column) {
		this.column = column;
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
	 * @return the lazy
	 */
	public String isLazy() {
		return lazy;
	}
	/**
	 * @param lazy the lazy to set
	 */
	public void setLazy(String lazy) {
		this.lazy = lazy;
	}

}
