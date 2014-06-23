package com.imhipi.app.tms.model;

import java.io.Serializable;

/**
 *
 * @author michael
 */
public class Dictionary extends BaseModel {
    
	private static final long serialVersionUID = 1L;

	private Long id;
    
    private String key;
    
    private String type;
    
    private String name;
    
    private String parentKey;
    
    private Integer viewOrder;
    
	public Dictionary() {
        
    }
    
    public Dictionary(Long id) {
        this.id = id;
    }
    
    public Dictionary(String parentKey) {
        this.parentKey = parentKey;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public Integer getViewOrder() {
		return viewOrder;
	}

	public void setViewOrder(Integer viewOrder) {
		this.viewOrder = viewOrder;
	}

	@Override
    public Serializable getPrimaryKey() {
        return id;
    }

    @Override
    public void setPrimaryKey(Object o) {
        id = (Long)o;
    }

    @Override
    public void prepareForSave() {
        super.prepareForSave();
    }
    
}
