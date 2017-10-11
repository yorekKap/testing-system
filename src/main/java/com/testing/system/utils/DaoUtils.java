package com.testing.system.utils;

import com.testing.system.dao.annotations.Id;
import com.testing.system.dao.annotations.Table;
import com.testing.system.exceptions.dao.MySQLException;

/**
 * Provide convenient way to get corresponding db info from class
 */
public class DaoUtils {

    public static String getTableName(Class<?> clazz){
        Table table = clazz.getAnnotation(Table.class);
        if(table == null){
            throw new MySQLException("Dao entities should be " +
                    "annotated with Table");
        }

        return table.value();
    }

    public static String getPKColumnName(Class<?> clazz){
        Id id = ReflectionUtils.findAnnotationInClassFields(clazz, Id.class);
        if(id == null){
            throw new MySQLException("Dao entities should have " +
                    "field annotated with Id");
        }

        return id.name();
    }
}
