package com.testing.system.dao.implementation.jdbc;

import com.testing.system.dao.interfaces.CategoryDao;
import com.testing.system.entities.Category;
import com.testing.system.exceptions.dao.MySQLException;

import javax.sql.DataSource;

public class JdbcCategoryDao extends AbstractJdbcDaoAdapter<Category> implements CategoryDao {

    public static final String CATEGORY_TITLE = "title";

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
                    .addValue(CATEGORY_TITLE, categoryTitle)
                    .execute();

            categoryId = getLastSavedId();

            builder.commit();
        } catch (Exception e) {
            builder.rollback();
            throw new MySQLException(e.getMessage());
        }

        return new Category(categoryId, categoryTitle);
    }
}
