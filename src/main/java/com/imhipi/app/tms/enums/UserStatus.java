package com.imhipi.app.tms.enums;

public enum UserStatus {

	OK("ok"), STOP("stop"), EXITWORK("exitWork"), DELETE("delete");
	
	private String name;
	
	private UserStatus(String name) {
		this.name = name;
	}
	
	public String value() {
		return name;
	}
	
}
