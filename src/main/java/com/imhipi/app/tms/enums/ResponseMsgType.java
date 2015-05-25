package com.imhipi.app.tms.enums;

public enum ResponseMsgType {

	SUCCESS("success"), INFO("info"), WARNING("warning"), ERROR("error");
	
	private String key;
	
	private ResponseMsgType(String key) {
		this.key = key;
	}
	
	public String value() {
		return key;
	}
	
}
