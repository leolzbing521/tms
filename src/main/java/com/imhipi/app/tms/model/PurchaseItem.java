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
    
    private Boolean sell;
    
    private Double amount;
    
    private Long purchaseId;
    
    private Boolean hasDiamond;
    
    private String diamondDimension;
    
    private Double diamondAmount;
    
    private Long cuserId;
    
    private Long uuserId;
    
    private Purchase purchase;
    
    private String typeName;
    
    private String materialName;
    
	public PurchaseItem() {
        
    }
    
    public PurchaseItem(Long id) {
        this.id = id;
    }
    
	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
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

	public Boolean getSell() {
		return sell;
	}

	public void setSell(Boolean sell) {
		this.sell = sell;
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

	public boolean getHasDiamond() {
		return hasDiamond;
	}

	public void setHasDiamond(boolean hasDiamond) {
		this.hasDiamond = hasDiamond;
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
        sell = false;
        if(hasDiamond == null)
        	hasDiamond = false;
    }
    
}
