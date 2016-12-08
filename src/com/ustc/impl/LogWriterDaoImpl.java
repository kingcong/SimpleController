package com.ustc.impl;

import java.io.File;
import java.sql.Time;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ustc.dao.LogWriterDao;
import com.ustc.framework.bean.LogInfo;
import com.ustc.util.XMLUtil;

/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月8日 下午8:20:44 
* 
*/
public class LogWriterDaoImpl implements LogWriterDao{

	@Override
	public void addLog(LogInfo logInfo) {
		// TODO Auto-generated method stub
		try {
			File file = new File("/Users/kingcong/Desktop/log.xml");
			Document doc = null;
			Element rootElem = null;
			if(!file.exists()){
				/**
				 * 需求： 把contact对象保存到xml文件中
				 */
				//如果没有xml文件，则创建xml文件
				doc = DocumentHelper.createDocument();
				//创建根标签
				rootElem = doc.addElement("log");
			}else{
				//如果有xml文件，则读取xml文件
				doc = XMLUtil.getDocument();
				//如果有xml文件，读取根标签
				rootElem = doc.getRootElement();
			}

			//添加action标签
			Element actionElem = rootElem.addElement("action");
			
			/**
			 * 由系统自动生成随机且唯一的ID值，赋值给联系人
			 */
			String uuid = UUID.randomUUID().toString().replace("-","");
			
//			actionElem.addAttribute("id", uuid);
			actionElem.addElement("name").setText(logInfo.getName());
			String s_time = logInfo.getStartTime() == null ? "" : logInfo.getStartTime().toString();
			actionElem.addElement("s-time").setText(s_time);
			String end_time = logInfo.getEndTime() == null ? "" : logInfo.getEndTime().toString();
			actionElem.addElement("e-time").setText(end_time);
			String result = logInfo.getResult() == null ? "" : logInfo.getResult();
			actionElem.addElement("result").setText(result);
			
			//把Document写出到xml文件
			XMLUtil.write2xml(doc);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
