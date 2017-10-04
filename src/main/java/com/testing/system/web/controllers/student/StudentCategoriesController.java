package com.testing.system.web.controllers.student;

import com.testing.system.service.interfaces.CategoryService;
import com.testing.system.utils.CurrentUserFetcher;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;

/**
 * Created by yuri on 01.10.17.
 */
public class StudentCategoriesController extends Controller {

    private CategoryService categoryService;

    public StudentCategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String get(RequestService requestService) {
        Long userId = CurrentUserFetcher.getCurrentUserId(getRequest());

        requestService.setAttribute("categories",
                categoryService.getCategoriesOpenForStudentId(userId));

        return "student-categories";
    }
}
