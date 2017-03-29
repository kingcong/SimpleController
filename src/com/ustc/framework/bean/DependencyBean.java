package com.ustc.framework.bean;

import java.util.Map;

public class DependencyBean {
	private String name;
	private String classpath;
	private Map<String,PropertyBean> properties;
	public DependencyBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DependencyBean(String name, String classpath, Map<String, PropertyBean> properties) {
		super();
		this.name = name;
		this.classpath = classpath;
		this.properties = properties;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClasspath() {
		return classpath;
	}
	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}
	public Map<String, PropertyBean> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, PropertyBean> properties) {
		this.properties = properties;
	}
	@Override
	public String toString() {
		return "DependencyBean [name=" + name + ", classpath=" + classpath + ", properties=" + properties + "]";
	}	
}
