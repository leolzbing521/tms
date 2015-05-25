package com.imhipi.app.tms.enums;

public enum DictRootKey {

	ROOT("root");
	
	private String name;
	
	private DictRootKey(String name) {
		this.name = name;
	}
	
	public String value() {
		return name;
	}
	
}
