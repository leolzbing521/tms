package com.imhipi.app.tms.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Lrush
 */
public class Brand extends BaseModel {
    
	private static final long serialVersionUID = 1L;

	private Long id;
    
    private String brand;
    
    
    
    private String address;
    
    private String tel;
    
    private String manu;
    
    private Long parentId;
    
    private Integer viewOrder;
    
    private List<Brand> children;
    
	public Brand() {
        
    }
	
    public Brand(Long id) {
        this.id = id;
    }
    
	public List<Brand> getChildren() {
		return children;
	}

	public void setChildren(List<Brand> children) {
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	/**
	 * @return the manu
	 */
	public String getManu() {
		return manu;
	}

	/**
	 * @param manu the manu to set
	 */
	public void setManu(String manu) {
		this.manu = manu;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
    
}
