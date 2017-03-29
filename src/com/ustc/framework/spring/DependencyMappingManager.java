package com.ustc.framework.spring;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ustc.framework.bean.DependencyBean;
import com.ustc.framework.bean.PropertyBean;


public class DependencyMappingManager {
	public static Map<String,DependencyBean> getDependency()
	{
		Map<String,DependencyBean> map = new HashMap<String,DependencyBean>();
			
		try {
			SAXReader reader = new SAXReader();
			InputStream instream = DependencyMappingManager.class.getResourceAsStream("/di.xml");
			Document doc = reader.read(instream);
			Element root = doc.getRootElement();
			
			List<Element> list_beans = root.elements("bean");
			for (Element element : list_beans) {
				DependencyBean dependency = new DependencyBean();
				String name = element.elementText("name");
				String classpath = element.elementText("class"); 
				
				Map<String,PropertyBean> properties = new HashMap<String,PropertyBean>();
				List<Element> list_properties = element.elements("property");
				for (Element element2 : list_properties) {
					PropertyBean property = new PropertyBean();
					String propertyName = element2.elementText("name");
					String ref_class = element2.elementText("ref-class");
					property.setName(propertyName);
					property.setRef_class(ref_class);
					properties.put(propertyName, property);
				}
				dependency.setName(name);
				dependency.setClasspath(classpath);
				dependency.setProperties(properties);
				map.put(name, dependency);
			}
			return map;
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;		
	}
}
