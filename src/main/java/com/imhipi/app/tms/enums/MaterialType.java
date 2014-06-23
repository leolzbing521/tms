package com.imhipi.app.tms.enums;

public enum MaterialType {

	GOLD("gold"), PLATINUM("platinum"), JADEITE("jadeite");
	
	private String name;
	
	private MaterialType(String name) {
		this.name = name;
	}
	
	public String value() {
		return name;
	}
	
}
