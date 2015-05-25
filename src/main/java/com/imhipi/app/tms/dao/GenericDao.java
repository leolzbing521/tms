package com.imhipi.app.tms.dao;

import java.util.List;
import java.util.Map;

import com.imhipi.app.tms.model.BaseModel;
import com.imhipi.app.tms.model.Pagination;


/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 *
 * <p>Extend this interface if you want typesafe (no casting necessary) DAO's for your
 * domain objects.
 *
 */
public interface GenericDao {

    /**
     * Generic method to get an object of specified type and properties.
     *
     * @param model the object with specified type and properties
     * @return a populated object
     */
    <T extends BaseModel> T findObject(T model);
    
    /**
     * Generic method used to get all objects of specified type and properties.
     * 
     * @param model the objects with specified type and properties
     * @return List of populated objects
     */
    <T extends BaseModel> List<T> findMulti(T model);
    
    <T extends BaseModel> List<T> findMulti(T model, Pagination page);
    
    <T extends BaseModel> List<T> findMulti(BaseModel model, Class<T> requiredType);
    
    <T extends BaseModel> List<T> findMulti(BaseModel model, Class<T> requiredType, Pagination page);
    
    /**
     * get total num by model
     * @param model
     * @return
     */
    <T extends BaseModel> Long countTotalNum(T model);
    
    <T extends BaseModel> Long countTotalNum(T model, Object queryParamsObject);

    /**
     * Checks for existence of object of specified type and properties.
     * @param model the object with specified type and properties
     * @return - true if exist, false if not
     */
    <T extends BaseModel> boolean exists(T model);

    /**
     * Generic method to save an object - handles insert.
     * @param model the object to save
     */
    <T extends BaseModel> void save(T model);
    
    <T extends BaseModel> boolean batchSave(List<T> modelList);
    
    <T extends BaseModel> boolean batchUpdate(List<T> modelList);
    
    /**
     * Generic method to save an object - handles update.
     * @param model the object to update
     * @return the number of rows effected.
     */
    <T extends BaseModel> int update(T model);
    
    /**
     * Generic method to delete objects of specified type and properties
     * @param model the object with specified type and properties
     * @return the number of rows effected.
     */
    <T extends BaseModel> int remove(T model);
    
    int remove(String statementName, Map<String, Object> paramMap, Class<? extends BaseModel> claz);
    
    <T extends BaseModel> List<T> findByNamedQuery(String queryName, Object queryParams, Class<T> claz);
    
    <T extends BaseModel> List<T> findByNamedQuery(String queryName, Object queryParamsObject, T baseModel);
    
    <T extends BaseModel> List<T> findByNamedAndPageQuery(String queryName, Object queryParams, Pagination page, Class<T> claz);
    
    <T extends BaseModel> List<T> findByNamedAndPageQuery(String queryName, Pagination page, Class<T> claz);
    
    <T> List<T> findByNamedQuery(String queryName, Object queryParamsObject, BaseModel baseModel, Class<T> returnType);
    
    <T> List<T> findByNamedQuery(String queryName, Object queryParamsObject, Class<? extends BaseModel> claz, Class<T> returnType);
    
    <T> List<T> findByNamedAndPageQuery(String queryName, Pagination page, Class<? extends BaseModel> claz, Class<T> returnType);

    <T> List<T> findByNamedAndPageQuery(String queryName, Object queryParamsObject, Pagination page, Class<? extends BaseModel> claz, Class<T> returnType);
    
    <T> List<T> findSingleColumnByNameQuery(String queryName, Map<String, Object> queryParams, Class<? extends BaseModel> claz, Class<T> returnType);

    Long countByNameQuery(String queryName, Object queryParamsObject, Class<? extends BaseModel> claz);
    
    int update(String updateName, Object queryParams, Class<? extends BaseModel> claz);
    
    <T extends BaseModel> int update(String updateName, Object queryParams, T model);
    
    Long save(String insertName, Object queryParams, Class<? extends BaseModel> claz);
    
}