package com.imhipi.app.tms.model;

import java.io.Serializable;

/**
 *
 * @author michael
 */
public class Purchase extends BaseModel {
    
	private static final long serialVersionUID = 1L;

	private Long id;
    
    private String name;
    
    private String amount;
    
    private String comment;
    
    private Long orgId;
    
    private Long cuserId;
    
    private Long uuserId;
    
    private User user;
    
    private Organization org;
    
	public Purchase() {
        
    }
    
    public Purchase(Long id) {
        this.id = id;
    }
    
	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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
