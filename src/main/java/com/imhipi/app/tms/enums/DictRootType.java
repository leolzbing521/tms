package com.imhipi.app.tms.enums;

public enum DictRootType {

	GEM_TYPE("gemType"), MATERIAL_TYPE("materialType"), HAS_DIAMOND("hasDiamond"),
	
	DIAMOND_DIMENSION("diamondDimension"), SEX("sex");
	
	private String name;
	
	private DictRootType(String name) {
		this.name = name;
	}
	
	public String value() {
		return name;
	}
	
}
