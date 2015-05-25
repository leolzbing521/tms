package com.imhipi.app.tms.enums;

public enum Sex {

	MALE("male"), FEMALE("female");
	
	private String name;
	
	private Sex(String name) {
		this.name = name;
	}
	
	public String value() {
		return name;
	}
	
}
