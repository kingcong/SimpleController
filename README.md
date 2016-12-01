#JavaEE第二次作业
##1.问题要求
1)将E1中编写的Servlet子类LoginController，在web.xml 中的配置修改为:可以拦截“*.scaction”类型的请求

2)增加controller.xml配置文件

3)使用LoginController 进行 request 拦截，解析请求，获取请求的 action。

4)LoginController 获取请求 action 后，解析 controller.xml

(XML 解析，SAX、Dom 或其他)，查找对应 name 的 action。 如果在 controller.xml 中找到，则解析该 action 的配置，包括 class、result。如果没有找到，响应客户端信息为:不可识别的 action 请求。

5)LoginController 查找到 http request 请求的 action 资源 后，利用其 class 属性实例化所指向的 action class(Java 反射机 制，Reflection)，并执行指定的方法 action method。

6)action method 执行完毕后，返回字符串作为 result。 LoginController 根据该 action 配置的 result，查找匹配，将 result 指向的资源按 result type 所指定的方式返回到客户端。如果没 有匹配的 result，响应客户端为信息为:没有请求的资源。
##2.解决方法
###a.开发环境：Eclipse + Tomcat8.0
###b.测试：localhost:8080/SimpleController/login.scaction
###b.作业目的：主要是在理解Struts框架原理的基础上，仿造Struts框架，使用MVC设计模式，实现和Struts类似的功能。
###c.心得：昨天写的时候很懵逼，突然看到作业时感觉这和Struts没啥关系，然后加了老师微信，才知道作业中是不需要用框架的，老师的目的是自己写个框架出来，SSH框架会在实验中使用。
##3.代码部分
###a.web.xml中修改LoginController的配置
``` xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
  <display-name>SimpleController</display-name>
  
  <!-- 登录控制器  -->
  <servlet>
  	<servlet-name>LoginController</servlet-name>
  	<servlet-class>com.ustc.servlet.LoginController</servlet-class>
  	<load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
  	<servlet-name>LoginController</servlet-name>
  	<url-pattern>*.scaction</url-pattern>
  </servlet-mapping>
  
</web-app>
```
###b.controller.xml配置文件
```
<?xml version="1.0" encoding="UTF-8"?>
<action-controller>
	<action>
		<name>login</name>
		<class>
			<name>com.ustc.framework.action.LoginAction</name>
			<method>login</method>
		</class>
		<result>
			<name>success</name>
			<type>forward</type>
			<value>pages/login_success.jsp</value>
		</result>
		<result>
			<name>fail</name>
			<type>redirect</type>
			<value>pages/login_fail.jsp</value>
		</result>
	</action>
	<action>
		<name>register</name>
		<class>
			<name>com.ustc.servlet.RegisterAction</name>
			<method>register</method>
		</class>
		<!-- other results  -->
	</action>
	<!-- other results -->
</action-controller>
```
###c.修改LoginController的servlet-mapping中的url-mattern属性后，可以拦截后缀为*.scaction的请求.
###d.ActionMappingManager是对于Controller.xml文件进行解析的工具类，使用了DOM解析，
```
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
				
				// 5.x actionMapping添加到map集合
				allActions.put(actionMapping.getName(), actionMapping);
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException("启动时候初始化错误",e);
		}
	}
	
```
###e.ActionMapping和Result是对controller.xml解析出的模型文件
```
public class ActionMapping {

	private String name;	// 请求路径名称
	private String className;	// 处理action路径的全名
	private String method;	// 处理方法
	private Map<String, Result> results;	// 结果视图集合
	
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
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return the results
	 */
	public Map<String, Result> getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(Map<String, Result> results) {
		this.results = results;
	}

```
###f.在对应的LoginController中调用解析方法，使用反射调用对应的Action方法
```
	try {
			// 1.获取请求uri,得到请求路径名称 如login
			String uri = request.getRequestURI();
			// 得到login
			String actionName = uri.substring(uri.lastIndexOf("/")+1, uri.indexOf(".scaction"));
			
			System.out.println("ActionName："+actionName);
			// 2.根据路径名称，读取配置文件，得到类的全名 【com.ustc.servlet.LoginController】
			ActionMapping actionMapping = actionMappingManager.getActionMapping(actionName);
			String className = actionMapping.getClassName();
			
			// 当前请求的处理方法  【method="login"】
			String method = actionMapping.getMethod();
			
			// 3.反射：创建对象，然后调用方法，获取方法返回的标记
			Class<?> clazz = Class.forName(className);
			Object obj = clazz.newInstance();
			Method m = clazz.getDeclaredMethod(method, HttpServletRequest.class,HttpServletResponse.class);
			// 调用方法后返回标记
			String returnFlag = (String)m.invoke(obj, request,response);
			
			// 4.用该标记，读取配置文件得到标记对应的页面，跳转类型
			Result result = actionMapping.getResults().get(returnFlag);
			String type = result.getType();	 // 类型
			String page = result.getValue();	// 页面
			
			// 跳转页面
			if ("redirect".equals(type)) {	// 重定向类型
				response.sendRedirect(request.getContextPath()+"/"+page);
			} else {	// 转发类型
				request.getRequestDispatcher(page).forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
```
###g.每一个Servelt都会对应一个Action，下面是LoginAction：
```
/**
	 * @see LoginAction#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @return 根据返回的uri对象即是查找结果集的name
	 */
	public Object login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Object uri = null;

		// 1. 获取请求数据，封装
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		UserBean user = new UserBean();
		user.setName(name);
		user.setPassword(password);

		// 2. 调用Service
		UserService userService = new UserService();
		UserBean userInfo = userService.login(user);
		// 3. 跳转
		if (userInfo == null) {
			// 登陆失败
			uri = "fail";  
		} else {
			// 登陆成功
			request.getSession().setAttribute("userInfo", userInfo);
			// 返回uri
			uri = "success"; 
		}
		return uri;
	}
```
##3.总结
###需要对Struts的框架运行过程有个深刻的理解后再去做的话会简单很多，这次作业主要是xml的解析和反射的使用。
