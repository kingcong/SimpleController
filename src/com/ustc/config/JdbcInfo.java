package com.ustc.config;
/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月26日 下午2:39:07 
* 
*/
public class JdbcInfo {
	
	// 驱动名称
	private String driver_class;
	// 数据库连接
	private String url_path;
	// 用户名
	private String db_username;
	// 密码
	private String db_userpassword;
	
	/**
	 * @return the driver_class
	 */
	public String getDriver_class() {
		return driver_class;
	}
	/**
	 * @param driver_class the driver_class to set
	 */
	public void setDriver_class(String driver_class) {
		this.driver_class = driver_class;
	}
	/**
	 * @return the url_path
	 */
	public String getUrl_path() {
		return url_path;
	}
	/**
	 * @param url_path the url_path to set
	 */
	public void setUrl_path(String url_path) {
		this.url_path = url_path;
	}
	/**
	 * @return the db_username
	 */
	public String getDb_username() {
		return db_username;
	}
	/**
	 * @param db_username the db_username to set
	 */
	public void setDb_username(String db_username) {
		this.db_username = db_username;
	}
	/**
	 * @return the db_userpassword
	 */
	public String getDb_userpassword() {
		return db_userpassword;
	}
	/**
	 * @param db_userpassword the db_userpassword to set
	 */
	public void setDb_userpassword(String db_userpassword) {
		this.db_userpassword = db_userpassword;
	}

}
