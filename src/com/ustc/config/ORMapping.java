package com.ustc.config;

import java.util.List;

/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月26日 下午2:38:21 
* 
*/
public class ORMapping {
	
	// Jdbc配置信息
	private JdbcInfo jdbcInfo;
	// Class配置信息
	private List<ClassInfo> classInfos;

	/**
	 * @return the classInfos
	 */
	public List<ClassInfo> getClassInfos() {
		return classInfos;
	}
	/**
	 * @param classInfos the classInfos to set
	 */
	public void setClassInfos(List<ClassInfo> classInfos) {
		this.classInfos = classInfos;
	}
	/**
	 * @return the jdbcInfo
	 */
	public JdbcInfo getJdbcInfo() {
		return jdbcInfo;
	}
	/**
	 * @param jdbcInfo the jdbcInfo to set
	 */
	public void setJdbcInfo(JdbcInfo jdbcInfo) {
		this.jdbcInfo = jdbcInfo;
	}
}

