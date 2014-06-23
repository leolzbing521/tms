package com.imhipi.app.tms.enums;

public enum GemType {

	RING("ring"), NECKLACE("necklace"), EARRING("earring"),
	
	BRACELET("bracelet"), PENDANT("PENDANT");
	
	private String name;
	
	private GemType(String name) {
		this.name = name;
	}
	
	public String value() {
		return name;
	}
	
}
