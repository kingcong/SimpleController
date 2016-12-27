package com.ustc.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ustc.config.Configuration;
import com.ustc.config.Conversation;


/**
 * Application Lifecycle Listener implementation class TomcatListener
 *
 */
@WebListener
public class TomcatListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public TomcatListener() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println("Tomcat starting....");
    	Configuration configuration = new Configuration();
    	Conversation conversation = configuration.createConversation();
    	System.out.println("Conversation初始化成功..."+conversation);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println("Tomcat destory....");
    }
	
}
