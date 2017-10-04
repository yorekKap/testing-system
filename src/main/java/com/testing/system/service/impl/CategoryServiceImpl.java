package com.testing.system.service.impl;

import com.testing.system.dao.interfaces.CategoryDao;
import com.testing.system.entities.Category;
import com.testing.system.entities.TutorCategoryAccessRight;
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
    public List<TutorCategoryAccessRight> getCategoriesWithRightsByTutorId(Long userId) {
        return categoryDao.getCategoriesWithRightsByTutorId(userId);
    }

    @Override
    public Category createCategory(String categoryTitle, Boolean openToAll, Long userId) {
        return categoryDao.createCategory(categoryTitle, openToAll, userId);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryDao.delete(categoryId);
    }

    @Override
    public List<Category> getCategoriesOpenForStudentId(Long studentId) {
        return categoryDao.findCategoriesOpenForStudentId(studentId);
    }
}