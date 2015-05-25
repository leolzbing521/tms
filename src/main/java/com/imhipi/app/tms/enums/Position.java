package com.imhipi.app.tms.enums;

public enum Position {

	ADMIN("admin"), DM("dm");
	
	private String name;
	
	private Position(String name) {
		this.name = name;
	}
	
	public String value() {
		return name;
	}
	
}
