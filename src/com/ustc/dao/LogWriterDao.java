package com.ustc.dao;

import com.ustc.framework.bean.LogInfo;

/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月8日 下午8:19:08 
* 
*/
public interface LogWriterDao {
	public void addLog(LogInfo logInfo);	// 添加一条log日志
}
