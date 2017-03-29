package com.ustc.framework.bean;

public class PropertyBean {
	private String name;
	private String ref_class;
	public PropertyBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PropertyBean(String name, String ref_class) {
		super();
		this.name = name;
		this.ref_class = ref_class;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRef_class() {
		return ref_class;
	}
	public void setRef_class(String ref_class) {
		this.ref_class = ref_class;
	}
	@Override
	public String toString() {
		return "PropertyBean [name=" + name + ", ref_class=" + ref_class + "]";
	}	
}
