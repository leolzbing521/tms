package com.imhipi.app.tms.model;

import java.io.Serializable;

/**
 *
 * @author michael
 */
public class Privilege extends BaseModel {
    
	private static final long serialVersionUID = 1L;

	private Long id;
    
    private String name;
    
    private String funAction;
    
    private Long parentId;
    
    private Integer treeLevel;
    
    private String type;
    
	public Privilege() {
        
    }
    
    public Privilege(Long id) {
        this.id = id;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFunAction() {
		return funAction;
	}

	public void setFunAction(String funAction) {
		this.funAction = funAction;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
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
