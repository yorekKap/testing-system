package com.testing.system.service.interfaces;

import com.testing.system.entities.Category;

import java.util.List;

/**
 * Created by yuri on 27.09.17.
 */
public interface CategoryService {
    Category createCategory(String categoryTitle, Long userId);

    void updateCategory(String title, Long id);

    void deleteCategory(Long categoryId);

    List<Category> getAllCategories();

    Category findById(Long id);
}
