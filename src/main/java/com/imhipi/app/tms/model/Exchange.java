package com.imhipi.app.tms.model;

import java.io.Serializable;

/**
 *
 * @author michael
 */
public class Exchange extends BaseModel {
    
	private static final long serialVersionUID = 1L;

	private Long id;
    
    private String purchaseItemId;
    
    private String gemType;
    
    private String type;
    
    private Double weight;
    
	public Exchange() {
        
    }
    
    public Exchange(Long id) {
        this.id = id;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPurchaseItemId() {
		return purchaseItemId;
	}

	public void setPurchaseItemId(String purchaseItemId) {
		this.purchaseItemId = purchaseItemId;
	}

	public String getGemType() {
		return gemType;
	}

	public void setGemType(String gemType) {
		this.gemType = gemType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
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
