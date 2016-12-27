package com.ustc.config;

import java.util.List;

/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月26日 下午2:39:23 
* 
*/
public class ClassInfo {
	
	private String name;
	private String table;
	private String id_name;
	private List<ColumnInfo> columnInfos;

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
	 * @return the table
	 */
	public String getTable() {
		return table;
	}
	/**
	 * @param table the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}
	/**
	 * @return the id_name
	 */
	public String getId_name() {
		return id_name;
	}
	/**
	 * @param id_name the id_name to set
	 */
	public void setId_name(String id_name) {
		this.id_name = id_name;
	}
	/**
	 * @return the columnInfos
	 */
	public List<ColumnInfo> getColumnInfos() {
		return columnInfos;
	}
	/**
	 * @param columnInfos the columnInfos to set
	 */
	public void setColumnInfos(List<ColumnInfo> columnInfos) {
		this.columnInfos = columnInfos;
	}

}
