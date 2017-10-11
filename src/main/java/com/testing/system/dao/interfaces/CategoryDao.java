package com.testing.system.dao.interfaces;

import com.testing.system.entities.Category;

import java.util.List;

/**
 * DAO class for managing {@link Category} entities
 *
 * @author yuri
 */
public interface CategoryDao extends GenericDao<Category, Long> {
    Category createCategory(String categoryTitle, Long userId);
}


