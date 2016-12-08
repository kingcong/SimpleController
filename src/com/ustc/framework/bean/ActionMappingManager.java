package com.ustc.framework.bean;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sun.org.apache.xml.internal.security.Init;
import com.ustc.framework.interceptor.Interceptor;


/** 
* @author 王聪 E-mail: 2441413514@qq.com
* @version 创建时间：2016年11月30日 下午8:59:56 
* 加载controller.xml文件并解析
*/
public class ActionMappingManager {
	
	// 保存action的集合
	private Map<String,ActionMapping> allActions;
		
	public ActionMappingManager() {
		allActions = new HashMap<String,ActionMapping>();
		this.Init();
		
	}
	
	/**
	 * 根据请求路径名称，返回Action的映射对象
	 * @param actionName   当前请求路径
	 * @return             返回配置文件中代表action节点的AcitonMapping对象
	 */
	public ActionMapping getActionMapping(String actionName) {
		if (actionName == null) {
			throw new RuntimeException("传入参数有误，请查看controller.xml配置的路径。");
		}
		
		ActionMapping actionMapping = allActions.get(actionName);
		if (actionMapping == null) {
			throw new RuntimeException("没有请求的资源");
		}
		return actionMapping;
	}
		
	private void Init() {
		/********DOM4J读取配置文件***********/
		try {
			// 1. 得到解析器
			SAXReader reader = new SAXReader();
			// 得到src/mystruts.xml  文件流
			InputStream inStream = this.getClass().getResourceAsStream("/controller.xml");
			// 2. 加载文件
			Document doc = reader.read(inStream);
			
			// 3. 获取根
			Element root = doc.getRootElement();
			
			// 4.获取package节点下，所有的interceptor节点
			List<Element> listInterceptor = root.elements("interceptor");
			
			// 4. 得到package节点下，  所有的action子节点
			List<Element> listAction = root.elements("action");
			
			// 5.遍历 ，封装
			for (Element ele_action : listAction) {
				// 5.1 封装一个ActionMapping对象
				ActionMapping actionMapping = new ActionMapping();
				
				// 5.2 封装当前action节点下的class视图
				Element ele_name = ele_action.element("name");
				actionMapping.setName(ele_name.getText());
	
				Element ele_class = ele_action.element("class");
				actionMapping.setClassName(ele_class.element("name").getText());
				actionMapping.setMethod(ele_class.element("method").getText());
				
				// 5.3 封装当前action下的拦截器
				List<InterceptorInfo> interceptors = new ArrayList<InterceptorInfo>();
				Iterator<Element> iterator = ele_action.elementIterator("interceptor-ref");
				while (iterator.hasNext()) {
					Element element = (Element) iterator.next();
					String ele_interceptor_namevalue = element.element("name").getText();
					if (ele_interceptor_namevalue != null) {	// 说明定义了拦截器
						// 开始对拦截器列表进行搜索过滤
						for (Element interceptor : listInterceptor) {
							Element interceptor_name = interceptor.element("name");
							String list_nameValue = interceptor_name.getText();
							if (ele_interceptor_namevalue.equals(list_nameValue)) {	// 匹配
								InterceptorInfo info = new InterceptorInfo();
								info.setName(interceptor_name.toString());
								info.setClassName(interceptor.element("class").element("name").getText());
								info.setMethodName(interceptor.element("class").element("method").getText());
								interceptors.add(info);
								break;
							}
						}
					}
				}
				System.out.println("Interceptors:"+interceptors);

				// 5.3 封装当前aciton节点下所有的结果视图
				Map<String,Result> results = new HashMap<String, Result>();
				
				// 5.4 得到当前action节点下所有的result子节点
				 Iterator<Element> it = ele_action.elementIterator("result");
				 while (it.hasNext()) {
					 // 当前迭代的每一个元素都是 <result...>
					 Element ele_result = it.next();
					 
					 // 封装对象
					 Result res = new Result();
					 res.setName(ele_result.element("name").getText());
					 res.setType(ele_result.element("type").getText());
					 res.setValue(ele_result.element("value").getText());
					 
					 // 添加到集合
					 results.put(res.getName(), res);
				 }
				
				// 设置到actionMapping中
				actionMapping.setResults(results);
				actionMapping.setInterceptors(interceptors);
				
				// 5.x actionMapping添加到map集合
				allActions.put(actionMapping.getName(), actionMapping);
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException("启动时候初始化错误",e);
		}
	}
	
}
