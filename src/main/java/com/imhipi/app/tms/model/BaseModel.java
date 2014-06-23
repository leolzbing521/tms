package com.imhipi.app.tms.model;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.util.ClassUtils;

import com.imhipi.app.tms.util.MiscUtil;

/**
 *
 * @author michael
 */
public abstract class BaseModel implements Serializable {
    
	private static final long serialVersionUID = 1L;

	protected String dbName;
    
    protected String tableName;
    
    protected String modelName;
    
    protected Date ctime;
    
    protected Date utime;
    
    public static final String SCM_DB_NAME = "scm";
    
    public BaseModel() {
        dbName = SCM_DB_NAME;
        tableName = MiscUtil.classToDbName(this.getClass());
        modelName = genModelName();
    }
    
    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    
    public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getUtime() {
		return utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}

	private String genModelName() {
        return ClassUtils.getShortName(this.getClass());
    }
    
    public abstract Serializable getPrimaryKey();
    
    public abstract void setPrimaryKey(Object o);
    
    public void prepareForSave() {
        ctime = new Date();
        utime = ctime;
    }
    
    public void prepareForUpdate() {
        if (utime != null) {
            utime = new Date();
        }
    }
    
    /**
     * 
     * @return the JSONObject represent of this object with valid properties
     */
    protected JSONObject doJSONObject() {
        JSONObject jo = new JSONObject(this);
        jo.remove("dbName");
        jo.remove("tableName");
        jo.remove("modelName");
        jo.remove("primaryKey");
        jo.remove("createTime");
        jo.remove("updateTime");
        return jo;
    }
    
    /**
     * 
     * @return the JSONObject represent of this object with specified properties
     */
    public JSONObject toJSONObject() {
        return doJSONObject();
    }
    
}
