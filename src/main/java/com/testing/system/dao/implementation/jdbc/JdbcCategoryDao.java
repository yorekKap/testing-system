package com.testing.system.dao.implementation.jdbc;

import com.testing.system.dao.constants.OrderingMode;
import com.testing.system.dao.interfaces.CategoryDao;
import com.testing.system.dao.parsers.ObjectToColumnValueMapParser;
import com.testing.system.entities.Category;
import com.testing.system.entities.TutorCategoryAccessRight;
import com.testing.system.entities.enums.AccessRight;
import com.testing.system.exceptions.dao.MySQLException;
import com.testing.system.utils.DaoUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by yuri on 27.09.17.
 */
public class JdbcCategoryDao extends AbstractJdbcDaoAdapter<Category> implements CategoryDao {


    public JdbcCategoryDao(DataSource dataSource) {
        super(dataSource, Category.class);
    }

    @Override
    public Map<String, Object> getValuesMap(Category object) {
        return ObjectToColumnValueMapParser.parse(object);
    }

    @Override
    public List<TutorCategoryAccessRight> getCategoriesWithRightsByTutorId(Long userId) {
        return builder.select("*")
                .from(DaoUtils.getTableName(TutorCategoryAccessRight.class) + " accessRight")
                .join(this.tableName + " category")
                .on("accessRight.category_id", "category.id")
                .where("accessRight.tutor_id")
                .isEquals(userId)
                .orderBy("category.id", OrderingMode.DESC)
                .execute(TutorCategoryAccessRight.class);
    }

    @Override
    public Category createCategory(String categoryTitle, Boolean openToAll, Long userId) {
        Long categoryId = null;

        try {
            builder.beginTransaction();
            builder.insert()
                    .setValue("title", categoryTitle)
                    .setValue("open_to_all", openToAll)
                    .execute();

            categoryId = findLastSavedId();

            builder.insert()
                    .into("tutors_categories_access_rights")
                    .setValue("category_id", categoryId)
                    .setValue("tutor_id", userId)
                    .setValue("access_right", AccessRight.RW.toString())
                    .execute();

            builder.commit();
        }catch (Exception e){
            builder.rollback();
            throw new MySQLException(e.getMessage());
        }

        return new Category(categoryId, categoryTitle, openToAll);
    }

}
