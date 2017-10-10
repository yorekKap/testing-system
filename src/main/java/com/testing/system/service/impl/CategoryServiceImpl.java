package com.testing.system.service.impl;

import com.testing.system.dao.interfaces.CategoryDao;
import com.testing.system.entities.Category;
import com.testing.system.service.interfaces.CategoryService;

import java.util.List;

/**
 * Created by yuri on 27.09.17.
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Category createCategory(String categoryTitle, Long userId) {
        return categoryDao.createCategory(categoryTitle, userId);
    }

    @Override
    public void updateCategory(String title, Long id) {
        categoryDao.update(new Category(id, title));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryDao.delete(categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findByPK(id);
    }
}