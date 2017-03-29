package com.ustc.framework.spring;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import javax.servlet.http.HttpServletRequest;

public class IntrospectManager {
	
	public static void getValueFromRequest(Object bean,String proName,HttpServletRequest request)
	{		
		try {
			String value = request.getParameter(proName);
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
			PropertyDescriptor[] descriptor = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < descriptor.length; i++) {
				PropertyDescriptor propertyDescriptor = descriptor[i];
				if (propertyDescriptor.getName().equals(proName)) {
					propertyDescriptor.getWriteMethod().invoke(bean, value);
				}				
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setBeanToProperty(Object bean,String proName,Object proOwner)
	{
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(proOwner.getClass(),Object.class);
			PropertyDescriptor[] descriptor = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < descriptor.length; i++) {
				PropertyDescriptor propertyDescriptor = descriptor[i];
				if (propertyDescriptor.getName().equals(proName)) {
					propertyDescriptor.getWriteMethod().invoke(proOwner, bean);
				}				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
