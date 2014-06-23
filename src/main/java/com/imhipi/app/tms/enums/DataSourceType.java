package com.imhipi.app.tms.enums;

public enum DataSourceType {

	UPDATE("update"), SELECT("select");
	
	private String value;
	
	private DataSourceType(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
