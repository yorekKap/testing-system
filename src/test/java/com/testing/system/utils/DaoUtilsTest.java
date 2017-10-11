package com.testing.system.utils;

import com.testing.system.entities.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by yuri on 11.10.17.
 */
public class DaoUtilsTest {

    private Class<User> clazz = User.class;
    private String tableName = "users";
    private String pkColumnName = "id";

    @Test
    public void getTableName(){
        String actualTableName = DaoUtils.getTableName(clazz);

        assertEquals(tableName, actualTableName);
    }

    @Test
    public void getPKColumnName(){
        String actualPkColumName = DaoUtils.getPKColumnName(clazz);

        assertEquals(pkColumnName, actualPkColumName);
    }
}
