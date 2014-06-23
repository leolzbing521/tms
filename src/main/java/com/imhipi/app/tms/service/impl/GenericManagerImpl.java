package com.imhipi.app.tms.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.imhipi.app.tms.dao.GenericDao;
import com.imhipi.app.tms.model.BaseModel;
import com.imhipi.app.tms.model.Pagination;
import com.imhipi.app.tms.service.GenericManager;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *     &lt;bean id="userManager" class="com.ngnsoft.ngp.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="com.ngnsoft.ngp.dao.hibernate.GenericDaoHibernate"&gt;
 *                 &lt;constructor-arg value="com.ngnsoft.ngp.model.User"/&gt;
 *                 &lt;property name="sessionFactory" ref="sessionFactory"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * <p/>
 * <p>If you're using iBATIS instead of Hibernate, use:
 * <pre>
 *     &lt;bean id="userManager" class="com.ngnsoft.ngp.service.impl.GenericManagerImpl"&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class="com.ngnsoft.ngp.dao.ibatis.GenericDaoiBatis"&gt;
 *                 &lt;constructor-arg value="com.ngnsoft.ngp.model.User"/&gt;
 *                 &lt;property name="dataSource" ref="dataSource"/&gt;
 *                 &lt;property name="sqlMapClient" ref="sqlMapClient"/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 *
 * @param <T> a type variable
 */
@Service("genericManager")
public class GenericManagerImpl implements GenericManager {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    @Qualifier("genericDao")
    protected GenericDao genericDao;

    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public GenericManagerImpl() {
    }

    public <T extends BaseModel> GenericManagerImpl(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> T get(Object pk, Class<T> clazz) {
        T model = null;
        try {
            model = clazz.newInstance();
            model.setPrimaryKey(pk);
            model = findObject(model);
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        return model;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> T get(Object pk, T model) {
        model = findObject(model);
        return model;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> boolean exist(Object pk, T model) {
        if (get(pk, model) == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> boolean exist(Object pk, Class<T> clazz) {
        if (get(pk, clazz) == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> T findObject(T model) {
        return genericDao.findObject(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> List<T> findMulti(T model) {
        return genericDao.findMulti(model);
    }

    @Override
    public <T extends BaseModel> List<T> findMulti(T model, Pagination page) {
        return genericDao.findMulti(model, page);
    }
        
    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> List<T> findMulti(BaseModel model, Class<T> requiredType) {
        return genericDao.findMulti(model, requiredType);
    }
    
    @Override
    public <T extends BaseModel> List<T> findMulti(BaseModel model, Class<T> requiredType, Pagination page) {
        return genericDao.findMulti(model, requiredType, page);
    }

    @Override
    public <T extends BaseModel> Long countTotalNum(T model) {
        return genericDao.countTotalNum(model);
    }
    
    @Override
    public <T extends BaseModel> Long countTotalNum(T model,
    		Object queryParamsObject) {
    	return genericDao.countTotalNum(model, queryParamsObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> boolean exists(T model) {
        return genericDao.exists(model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> boolean save(T model) {
        genericDao.save(model);
        return true;
    }
    
    @Override
    public <T extends BaseModel> boolean batchSave(List<T> modelList) {
    	return genericDao.batchSave(modelList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> boolean saveIgnoreDke(T model) {
        boolean ret = false;
        try {
            genericDao.save(model);
            ret = true;
        } catch (DuplicateKeyException de) {
//        	de.printStackTrace();
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> boolean saveIfNotExist(T model) {
        boolean ret = false;
        if (model.getPrimaryKey() != null) {
            Class<T> clazz = (Class<T>) model.getClass();
            if (!exist(model.getPrimaryKey(), clazz)) {
                ret = save(model);
            }
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> int update(T model) {
        return genericDao.update(model);
    }
    
    @Override
    public <T extends BaseModel> boolean batchUpdate(List<T> modelList) {
    	return genericDao.batchUpdate(modelList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> boolean saveOrUpdate(T model) {
        boolean ret = false;
        if (model.getPrimaryKey() == null) {
            ret = save(model);
        } else {
            if (exist(model.getPrimaryKey(), model)) {
                int n = update(model);
                if (n > 0) {
                    ret = true;
                }
            } else {
                ret = save(model);
            }
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> int remove(Object pk, Class<T> clazz) {
        int ret = 0;
        try {
            T model = clazz.newInstance();
            model.setPrimaryKey(pk);
            ret = remove(model);
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> int remove(T model) {
        return genericDao.remove(model);
    }

    @Override
    public int remove(String statementName, Map<String, Object> paramMap,
            Class<? extends BaseModel> claz) {
        return genericDao.remove(statementName, paramMap, claz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> List<T> findByNamedQuery(String queryName, Object queryParams, Class<T> claz) {
        return genericDao.findByNamedQuery(queryName, queryParams, claz);
    }
    
    @Override
    public <T extends BaseModel> List<T> findByNamedQuery(String queryName, Object queryParamsObject, T baseModel) {
    	return genericDao.findByNamedQuery(queryName, queryParamsObject, baseModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends BaseModel> List<T> findByNamedAndPageQuery(String queryName, Object queryParams, Pagination page, Class<T> claz) {
        return genericDao.findByNamedAndPageQuery(queryName, queryParams, page, claz);
    }
    
    @Override
    public <T extends BaseModel> List<T> findByNamedAndPageQuery(String queryName, Pagination page, Class<T> claz) {
        return genericDao.findByNamedQuery(queryName, page, claz);
    }
    
    @Override
    public <T> List<T> findByNamedQuery(String queryName, Object queryParamsObject, Class<? extends BaseModel> claz, Class<T> returnType) {
        return genericDao.findByNamedQuery(queryName, queryParamsObject, claz, returnType);
    }
    
    @Override
    public <T> List<T> findByNamedQuery(String queryName, Object queryParamsObject, BaseModel baseModel, Class<T> returnType) {
    	return genericDao.findByNamedQuery(queryName, queryParamsObject, baseModel, returnType);
    }

    @Override
    public <T> List<T> findByNamedAndPageQuery(String queryName, Object queryParamsObject, Pagination page, Class<? extends BaseModel> claz, Class<T> returnType) {
        return genericDao.findByNamedAndPageQuery(queryName, queryParamsObject, page, claz, returnType);
    }
    
    @Override
    public <T> List<T> findByNamedAndPageQuery(String queryName, Pagination page, Class<? extends BaseModel> claz, Class<T> returnType) {
        return genericDao.findByNamedQuery(queryName, page, claz, returnType);
    }
    
    @Override
    public <T> List<T> findSingleColumnByNameQuery(String queryName, Map<String, Object> queryParams, Class<? extends BaseModel> claz, Class<T> returnType) {
        return genericDao.findSingleColumnByNameQuery(queryName, queryParams, claz, returnType);
    }

    @Override
    public Long countByNameQuery(String queryName, Object queryParamsObject, Class<? extends BaseModel> claz) {
        return genericDao.countByNameQuery(queryName, queryParamsObject, claz);
    }

    @Override
    public int update(String updateName, Object queryParams, Class<? extends BaseModel> claz) {
        return genericDao.update(updateName, queryParams, claz);
    }
    
    @Override
    public <T extends BaseModel> int update(String updateName, Object queryParams, T model) {
        return genericDao.update(updateName, queryParams, model);
    }
    
    @Override
    public Long save(String insertName, Object queryParams, Class<? extends BaseModel> claz) {
        return genericDao.save(insertName, queryParams, claz);
    }

}
