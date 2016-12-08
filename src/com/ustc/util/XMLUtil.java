package com.ustc.util;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * xml的工具类
 * @author APPle
 *
 */
public class XMLUtil {
	
	/**
	 * 读取xml文档方法
	 * @return
	 */
	public static Document getDocument(){
		try {
			Document doc = new SAXReader().read(new File("/Users/kingcong/Desktop/log.xml"));
			return doc;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	/**
	 * 写出到xml文档中
	 */
	public static void write2xml(Document doc){
		try {
			FileOutputStream out = new FileOutputStream("/Users/kingcong/Desktop/log.xml");
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			XMLWriter writer = new XMLWriter(out,format);
			writer.write(doc);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
