package com.testing.system.web.controllers.tutor;

import com.testing.system.entities.Category;
import com.testing.system.exceptions.web.BadRequestExceptionBuilder;
import com.testing.system.service.interfaces.CategoryService;
import com.testing.system.utils.CurrentUserFetcher;
import com.testing.system.web.constants.ApplicationScopeAttributes;
import com.testing.system.web.container.CategoryContainer;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;
import com.testing.system.web.dispatcher.enums.ActionType;

import java.io.IOException;

/**
 * Created by yuri on 27.09.17.
 */
public class CategoriesController extends Controller {

    private final static String ACTION_PARAMETER = "action";

    CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String post(RequestService requestService) {
        ActionType action = requestService.getAction();

        if (action == ActionType.CREATE) {
            createCategory(requestService.getParametersAsObject(Category.class),
                    CurrentUserFetcher.getCurrentUserId(getRequest()));
        } else if (action == ActionType.DELETE) {
            deleteCategory(requestService.getLong("categoryId"));
        } else if (action == ActionType.UPDATE) {
            updateCategory(requestService);
        } else {
            throw BadRequestExceptionBuilder.noAction();
        }

        return null;
    }

    public void createCategory(Category category, Long userId) {
        Category newCategory = categoryService.
                createCategory(category.getTitle(), userId);

        CategoryContainer categoryContainer = (CategoryContainer) getRequest()
                .getServletContext()
                .getAttribute(ApplicationScopeAttributes.CATEGORY_CONTAINER);

        categoryContainer.addCategory(newCategory);

        try {
            getResponse().getWriter().write(newCategory.getId().toString());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteCategory(Long categoryId) {
        categoryService.deleteCategory(categoryId);

        CategoryContainer categoryContainer = (CategoryContainer) getRequest()
                .getServletContext()
                .getAttribute(ApplicationScopeAttributes.CATEGORY_CONTAINER);

        categoryContainer.removeCategory(categoryId);
    }

    public void updateCategory(RequestService requestService) {
        String categoryTitle = requestService.getString("title");
        Long categoryId = requestService.getLong("id");

        categoryService.updateCategory(categoryTitle, categoryId);

        CategoryContainer categoryContainer = (CategoryContainer) getRequest()
                .getServletContext()
                .getAttribute(ApplicationScopeAttributes.CATEGORY_CONTAINER);

        categoryContainer.updateCategory(categoryId, categoryTitle);
    }}
