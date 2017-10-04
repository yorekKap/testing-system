package com.testing.system.service.interfaces;

import com.testing.system.entities.Category;
import com.testing.system.entities.TutorCategoryAccessRight;

import java.util.List;

/**
 * Created by yuri on 27.09.17.
 */
public interface CategoryService {
    List<TutorCategoryAccessRight> getCategoriesWithRightsByTutorId(Long userId);

    Category createCategory(String categoryTitle, Boolean openToAll, Long userId);

    void deleteCategory(Long categoryId);

    List<Category> getCategoriesOpenForStudentId(Long studentId);
}
