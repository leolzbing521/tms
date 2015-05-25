package com.imhipi.app.tms.model;

import java.io.Serializable;

public class ResponseMsg implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String type;
	
	private String body;

	public ResponseMsg(String type, String body) {
		super();
		this.type = type;
		this.body = body;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
