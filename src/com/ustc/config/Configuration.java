package com.ustc.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ustc.bean.UserBean;


/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年12月25日 下午9:20:12 
* 
*/
public class Configuration {
	
	private ORMapping orMapping = null;
	
	public Configuration() {
		Init();
	}
	
	public void config() {
		
	}
	
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		System.out.println(configuration.orMapping);
		Conversation conversation = configuration.createConversation();
		
		UserBean u = new UserBean();
		u.setName("dengnan");
		u.setPassword("haha");
		
		try {
//			conversation.insertUser(u);
//			conversation.baseConfigCreateTable();
			UserBean userBean = conversation.queryObject("name", "wangcong");
			System.out.println("UserBean:"+userBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void Init() {
		/********DOM4J读取配置文件***********/
		try {
			orMapping = new ORMapping();
			
			// 1. 得到解析器
			SAXReader reader = new SAXReader();
			// 得到src/mystruts.xml  文件流
			InputStream inStream = this.getClass().getResourceAsStream("/or_mapping.xml");
			// 2. 加载文件
			Document doc = reader.read(inStream);
			
			// 3. 获取根
			Element root = doc.getRootElement();
			
			// 4.获取根节点下的jdbc节点
			Element jdbcElement = root.element("jdbc");
			List<Element> listJdbcProperty = jdbcElement.elements("property");
			
			Map<String, Object> mappingProperty = new HashMap<String, Object>();
			for (Element element : listJdbcProperty) {
				String name = element.element("name").getText();
				String value = element.element("value").getText();
				mappingProperty.put(name, value);
			}
			// 赋值属性
			JdbcInfo jdbcInfo = new JdbcInfo();
			BeanUtils.populate(jdbcInfo, mappingProperty);
			orMapping.setJdbcInfo(jdbcInfo);
			
			// 5.获取根节点的所以class节点
			List<Element> listClass = root.elements("class");
			List<ClassInfo> classInfos = new ArrayList<ClassInfo>();
			for (Element element : listClass) {
				ClassInfo classInfo = new ClassInfo();
				classInfo.setName(element.element("name").getText());
				classInfo.setTable(element.element("table").getText());
				classInfo.setId_name(element.element("id").element("name").getText());
				
				// 下面设置列的属性
				List<Element> listcolumnProperty = element.elements("property");
				List<ColumnInfo> columnInfos = new ArrayList<ColumnInfo>();
				for (Element propertyElement : listcolumnProperty) {
					ColumnInfo columnInfo = new ColumnInfo();
					columnInfo.setName(propertyElement.element("name").getText());
					columnInfo.setColumn(propertyElement.element("column").getText());
					columnInfo.setType(propertyElement.element("type").getText());
					columnInfo.setLazy(propertyElement.element("lazy").getText());
					columnInfos.add(columnInfo);
				}
				classInfo.setColumnInfos(columnInfos);
				classInfos.add(classInfo);
			}
			orMapping.setClassInfos(classInfos);
			
		} catch (Exception e) {
			throw new RuntimeException("启动时候初始化错误",e);
		}
	}
	
	/**
	 * 创建Conversation会话类
	 * @return 
	 */
	public Conversation createConversation() {
		Conversation conversation = Conversation.getInstance();
		conversation.setOrMapping(orMapping);
		return conversation;
	}
}
