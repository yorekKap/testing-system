package com.testing.system.dao.implementation.jdbc;

import com.testing.system.dao.constants.OrderingMode;
import com.testing.system.dao.interfaces.CategoryDao;
import com.testing.system.entities.Category;
import com.testing.system.entities.enums.AccessRight;
import com.testing.system.exceptions.dao.MySQLException;
import com.testing.system.utils.DaoUtils;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by yuri on 27.09.17.
 */
public class JdbcCategoryDao extends AbstractJdbcDaoAdapter<Category> implements CategoryDao {


    public JdbcCategoryDao(DataSource dataSource) {
        super(dataSource, Category.class);
    }

    @Override
    public Category createCategory(String categoryTitle, Long userId) {
        Long categoryId = null;

        try {
            builder.beginTransaction();
            builder.insert()
                    .newValuesList()
                    .addValue("title", categoryTitle)
                    .execute();

            categoryId = getLastSavedId();

            builder.commit();
        }catch (Exception e){
            builder.rollback();
            throw new MySQLException(e.getMessage());
        }

        return new Category(categoryId, categoryTitle);
    }
}
