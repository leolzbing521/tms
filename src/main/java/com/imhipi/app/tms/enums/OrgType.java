package com.imhipi.app.tms.enums;

public enum OrgType {

	BATCH("batch"), DEPARTMENT("department");
	
	private String name;
	
	private OrgType(String name) {
		this.name = name;
	}
	
	public String value() {
		return name;
	}
	
}
