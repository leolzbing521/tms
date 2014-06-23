package com.imhipi.app.tms.dao.ibatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imhipi.app.tms.model.BaseModel;

/**
 * General iBATIS Utilities class with rules for primary keys and query names.
 *
 */
public final class IbatisDaoUtils {

    protected static final Logger logger = LoggerFactory.getLogger(IbatisDaoUtils.class);

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private IbatisDaoUtils() {
    }

    protected static void prepareObjectForSave(BaseModel model) {
        prepareObjectForSaveOrUpdate(model);
        model.prepareForSave();
    }

    protected static void prepareObjectForUpdate(BaseModel model) {
        prepareObjectForSaveOrUpdate(model);
        model.prepareForUpdate();
    }

    /**
     * @param model the model to examine
     */
    private static void prepareObjectForSaveOrUpdate(BaseModel model) {
    }

    /**
     * @return the select query name.
     * @param modelName the name of the model - returns "find" + modelName
     */
    public static String getFindQuery(String modelName) {
        return "find" + modelName;
    }

    /**
     * @return the count query name.
     * @param modelName the name of the model - returns "count" + modelName
     */
    public static String getCountQuery(String modelName) {
        return "count" + modelName;
    }

    /**
     * @return the insert query name.
     * @param modelName the name of the model - returns "save" + modelName
     */
    public static String getInsertQuery(String modelName) {
        return "save" + modelName;
    }

    /**
     * @return the update query name.
     * @param modelName the name of the model - returns "update" + modelName
     */
    public static String getUpdateQuery(String modelName) {
        return "update" + modelName;
    }

    /**
     * @return the delete query name.
     * @param modelName the name of the model - returns "delete" + modelName
     */
    public static String getDeleteQuery(String modelName) {
        return "remove" + modelName;
    }

    public static <T extends BaseModel> String getDbName(Class<T> clazz) {
        BaseModel model;
        try {
            model = clazz.newInstance();
            return model.getDbName();
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        return null;
    }
}
