package com.testing.system.service.interfaces;

import com.testing.system.entities.Category;

import java.util.List;


/**
 * Business layer API for convenient support of operations with {@link Category}
 * entity.
 *
 * @author yuri
 *
 */
public interface CategoryService {
    Category createCategory(String categoryTitle, Long userId);

    void updateCategory(String title, Long id);

    void deleteCategory(Long categoryId);

    List<Category> getAllCategories();

    Category findById(Long id);
}
