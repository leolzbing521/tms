package com.imhipi.app.tms.dao.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.imhipi.app.tms.dao.GenericDao;
import com.imhipi.app.tms.enums.DataSourceType;
import com.imhipi.app.tms.model.BaseModel;
import com.imhipi.app.tms.model.Pagination;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="com.ngnsoft.ngp.dao.ibatis.GenericDaoIbatis"&gt;
 *          &lt;constructor-arg value="com.ngnsoft.ngp.model.Foo"/&gt;
 *          &lt;property name="sqlMapClient" ref="sqlMapClient"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 *
 */
@Repository("genericDao")
public class GenericDaoIbatis implements GenericDao {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    private Map<String, SqlMapClientTemplate> sqlMapClientTemplateMap = new HashMap<String, SqlMapClientTemplate>();
    
    @Resource
    @Qualifier("sqlMapClients")
    private Map<String, Object> sqlMapClients = new HashMap<String, Object>();

    public GenericDaoIbatis() {
    }
    
    /**
     * 
     */
    @PostConstruct
    private void init() {
    	if(sqlMapClientTemplateMap.size() == 0) {
    		for(String dbName : sqlMapClients.keySet()) {
    			Object dbSourceObject = sqlMapClients.get(dbName);
    			if(dbSourceObject instanceof SqlMapClient) {
    				SqlMapClientTemplate sqlMapClientTemplate = new SqlMapClientTemplate();
        			sqlMapClientTemplate.setSqlMapClient((SqlMapClient)sqlMapClients.get(dbName));
        			
        			sqlMapClientTemplateMap.put(dbName + "_" + DataSourceType.UPDATE.value(), sqlMapClientTemplate);
        			sqlMapClientTemplateMap.put(dbName + "_" + DataSourceType.SELECT.value(), sqlMapClientTemplate);
    			} 
    		}
    	}
    }

    /**
     * Return the SqlMapClientTemplate for this DAO, pre-initialized with the
     * SqlMapClient or set explicitly.
     *
     * @return an initialized SqlMapClientTemplate
     */
    public final SqlMapClientTemplate getSqlMapClientTemplate(String dbName, DataSourceType type) {
		return this.sqlMapClientTemplateMap.get(dbName + "_" + type.value());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> T findObject(T model) {
        Object object = getSqlMapClientTemplate(model.getDbName(), DataSourceType.SELECT).queryForObject(
                IbatisDaoUtils.getFindQuery(model.getModelName()), model);
        if (object == null) {
            return null;
        }
        return (T) object;
    }

    @Override
    public <T extends BaseModel> List<T> findMulti(T model) {
        return getSqlMapClientTemplate(model.getDbName(), DataSourceType.SELECT).queryForList(
                IbatisDaoUtils.getFindQuery(model.getModelName()), model);
    }

    @Override
    public <T extends BaseModel> List<T> findMulti(T model, Pagination page) {
        return getSqlMapClientTemplate(model.getDbName(), DataSourceType.SELECT).queryForList(
                IbatisDaoUtils.getFindQuery(model.getModelName()), model,
                page.getSkip(),
                page.getPageCount());
    }
    
    @Override
    public <T extends BaseModel> List<T> findMulti(BaseModel model, Class<T> requiredType) {
        return getSqlMapClientTemplate(model.getDbName(), DataSourceType.SELECT).queryForList(
                IbatisDaoUtils.getFindQuery(model.getModelName()), model);
    }

    @Override
    public <T extends BaseModel> List<T> findMulti(BaseModel model, Class<T> requiredType, Pagination page) {
        return getSqlMapClientTemplate(model.getDbName(), DataSourceType.SELECT).queryForList(
                IbatisDaoUtils.getFindQuery(model.getModelName()), model,
                page.getSkip(),
                page.getPageCount());
    }

    @Override
    public <T extends BaseModel> Long countTotalNum(T model) {
        return (Long) getSqlMapClientTemplate(model.getDbName(), DataSourceType.SELECT).queryForObject(IbatisDaoUtils.getCountQuery(model.getModelName()), model);
    }
    
    @Override
    public <T extends BaseModel> Long countTotalNum(T model,
    		Object queryParamsObject) {
    	return (Long) getSqlMapClientTemplate(model.getDbName(), DataSourceType.SELECT)
    			.queryForObject(IbatisDaoUtils.getCountQuery(model.getModelName()), queryParamsObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> boolean exists(T model) {
        List<T> l = findMulti(model);
        return l != null && l.size() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> void save(final T model) {
        IbatisDaoUtils.prepareObjectForSave(model);
        Object primaryKey = getSqlMapClientTemplate(model.getDbName(), DataSourceType.UPDATE).insert(IbatisDaoUtils.getInsertQuery(model.getModelName()), model);
        if (model.getPrimaryKey() == null) {
            model.setPrimaryKey(primaryKey);
        }
    }
    
    @SuppressWarnings({ "deprecation", "unchecked" })
	@Override
    public <T extends BaseModel> boolean batchSave(final List<T> modelList) {
    	if(!CollectionUtils.isEmpty(modelList)) {
    		SqlMapClientTemplate sqlMapClientTemplate = getSqlMapClientTemplate(modelList.get(0).getDbName(), DataSourceType.UPDATE);
			// 执行回调
			sqlMapClientTemplate.execute(new SqlMapClientCallback() {
				// 实现回调接口
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					// 开始批处理
					executor.startBatch();
					for (T model : modelList) {
						// insert
						IbatisDaoUtils.prepareObjectForSave(model);
						executor.insert(IbatisDaoUtils.getInsertQuery(model
								.getModelName()), model);
					}
					// 执行批处理
					executor.executeBatch();
					return true;
				}
			});
    	}
    	return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> int update(final T model) {
        IbatisDaoUtils.prepareObjectForUpdate(model);
        return getSqlMapClientTemplate(model.getDbName(), DataSourceType.UPDATE).update(IbatisDaoUtils.getUpdateQuery(model.getModelName()), model);
    }
    
    @Override
    public <T extends BaseModel> boolean batchUpdate(final List<T> modelList) {
    	if(!CollectionUtils.isEmpty(modelList)) {
    		SqlMapClientTemplate sqlMapClientTemplate = getSqlMapClientTemplate(modelList.get(0).getDbName(), DataSourceType.UPDATE);
			// 执行回调
			sqlMapClientTemplate.execute(new SqlMapClientCallback() {
				// 实现回调接口
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					// 开始批处理
					executor.startBatch();
					for (T model : modelList) {
						// insert
						IbatisDaoUtils.prepareObjectForUpdate(model);
						executor.update((IbatisDaoUtils.getUpdateQuery(model.getModelName())), model);
					}
					// 执行批处理
					executor.executeBatch();
					return true;
				}
			});
    	}
    	return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> int remove(T model) {
        return getSqlMapClientTemplate(model.getDbName(), DataSourceType.UPDATE).delete(
                IbatisDaoUtils.getDeleteQuery(model.getModelName()), model);
    }

    @Override
    public int remove(String statementName, Map<String, Object> paramMap,
            Class<? extends BaseModel> claz) {
        return getSqlMapClientTemplate(IbatisDaoUtils.getDbName(claz), DataSourceType.UPDATE).delete(statementName, paramMap);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> List<T> findByNamedQuery(String queryName,
            Object queryParams, Class<T> claz) {
        return getSqlMapClientTemplate(IbatisDaoUtils.getDbName(claz), DataSourceType.SELECT).queryForList(queryName, queryParams);
    }
    
    @Override
    public <T extends BaseModel> List<T> findByNamedQuery(String queryName, Object queryParamsObject, T baseModel) {
    	return getSqlMapClientTemplate(baseModel.getDbName(), DataSourceType.SELECT).queryForList(queryName, queryParamsObject);
    }
    
    @Override
    public <T extends BaseModel> List<T> findByNamedQuery(String queryName,
            Object queryParams, Pagination page, Class<T> claz) {
        return getSqlMapClientTemplate(IbatisDaoUtils.getDbName(claz), DataSourceType.SELECT).queryForList(queryName, queryParams,
                page.getSkip(),
                page.getPageCount());
    }

    @Override
    public <T> List<T> findByNamedQuery(String queryName, Object queryParamsObject, Class<? extends BaseModel> claz, Class<T> returnType) {
        return getSqlMapClientTemplate(IbatisDaoUtils.getDbName(claz), DataSourceType.SELECT).queryForList(queryName, queryParamsObject);
    }
    
    @Override
    public <T> List<T> findByNamedQuery(String queryName, Object queryParamsObject, BaseModel baseModel, Class<T> returnType) {
    	return getSqlMapClientTemplate(baseModel.getDbName(), DataSourceType.SELECT).queryForList(queryName, queryParamsObject);
    }

    @Override
    public <T> List<T> findByNamedQuery(String queryName, Object queryParamsObject,
            Pagination page, Class<? extends BaseModel> claz, Class<T> returnType) {
        return getSqlMapClientTemplate(IbatisDaoUtils.getDbName(claz), DataSourceType.SELECT).queryForList(queryName, queryParamsObject,
                page.getSkip(),
                page.getPageCount());
    }
    
    @Override
    public <T> List<T> findSingleColumnByNameQuery(String queryName, Map<String, Object> queryParams, Class<? extends BaseModel> claz, Class<T> returnType) {
        return getSqlMapClientTemplate(IbatisDaoUtils.getDbName(claz), DataSourceType.SELECT).queryForList(queryName, queryParams);
    }

    @Override
    public Long countByNameQuery(String queryName, Object queryParamsObject, Class<? extends BaseModel> claz) {
        return (Long) getSqlMapClientTemplate(IbatisDaoUtils.getDbName(claz), DataSourceType.SELECT).queryForObject(queryName, queryParamsObject);
    }

    @Override
    public int update(String updateName, Object queryParams, Class<? extends BaseModel> claz) {
        return getSqlMapClientTemplate(IbatisDaoUtils.getDbName(claz), DataSourceType.UPDATE).update(updateName, queryParams);
    }
    
    @Override
    public <T extends BaseModel> int update(String updateName, Object queryParams, T model) {
        return getSqlMapClientTemplate(model.getDbName(), DataSourceType.UPDATE).update(updateName, queryParams);
    }

	@Override
	public Long save(String insertName, Object queryParams,
			Class<? extends BaseModel> claz) {
		return (Long) getSqlMapClientTemplate(IbatisDaoUtils.getDbName(claz), DataSourceType.UPDATE).insert(insertName, queryParams);
	}
}
