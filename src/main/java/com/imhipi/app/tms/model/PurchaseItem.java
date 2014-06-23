package com.imhipi.app.tms.model;

import java.io.Serializable;

/**
 *
 * @author michael
 */
public class PurchaseItem extends BaseModel {
    
	private static final long serialVersionUID = 1L;

	private Long id;
    
    private String name;
    
    private String barcode;
    
    private String material;
    
    private String type;
    
    private Double weight;
    
    private String status;
    
    private Double amount;
    
    private Long purchaseId;
    
    private String diamondDimension;
    
    private Double diamondAmount;
    
    private Long cuserId;
    
    private Long uuserId;
    
	public PurchaseItem() {
        
    }
    
    public PurchaseItem(Long id) {
        this.id = id;
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getDiamondDimension() {
		return diamondDimension;
	}

	public void setDiamondDimension(String diamondDimension) {
		this.diamondDimension = diamondDimension;
	}

	public Double getDiamondAmount() {
		return diamondAmount;
	}

	public void setDiamondAmount(Double diamondAmount) {
		this.diamondAmount = diamondAmount;
	}

	public Long getCuserId() {
		return cuserId;
	}

	public void setCuserId(Long cuserId) {
		this.cuserId = cuserId;
	}

	public Long getUuserId() {
		return uuserId;
	}

	public void setUuserId(Long uuserId) {
		this.uuserId = uuserId;
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
