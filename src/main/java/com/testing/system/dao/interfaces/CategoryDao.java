package com.testing.system.dao.interfaces;

import com.testing.system.entities.Category;

import java.util.List;

/**
 * Created by yuri on 27.09.17.
 */
public interface CategoryDao extends GenericDao<Category, Long> {
    Category createCategory(String categoryTitle, Long userId);
}


