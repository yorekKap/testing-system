package com.testing.system.web.controllers.tutor;

import com.testing.system.entities.Category;
import com.testing.system.exceptions.web.BadRequestException;
import com.testing.system.exceptions.web.BadRequestExceptionBuilder;
import com.testing.system.service.interfaces.CategoryService;
import com.testing.system.utils.CurrentUserFetcher;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;
import com.testing.system.web.dispatcher.enums.ActionType;
import com.testing.system.web.dto.CreateCategoryDto;
import com.testing.system.web.dto.CreateTestDto;
import com.testing.system.web.parsers.RequestContentParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yuri on 27.09.17.
 */
public class TutorCategoriesController extends Controller {

    private final static String ACTION_PARAMETER = "action";

    CategoryService categoryService;

    public TutorCategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public String get(RequestService requestService) {
        Long userId = CurrentUserFetcher.getCurrentUserId(getRequest());
        requestService.setAttribute("categoryAccessRights",
                categoryService.getCategoriesWithRightsByTutorId(userId));

        return "tutor-categories";
    }

    @Override
    public String post(RequestService requestService) {
        ActionType action = requestService.getAction();

        if (action == ActionType.CREATE) {
            createCategory(requestService.getParametersAsObject(CreateCategoryDto.class),
                    CurrentUserFetcher.getCurrentUserId(getRequest()));
        } else if (action == ActionType.DELETE) {
            deleteCategory(requestService.getLong("categoryId")
                    .orElseThrow(() ->
                            BadRequestExceptionBuilder.noParameter("categoryId")));
        } else {
            throw BadRequestExceptionBuilder.noAction();
        }

        return null;
    }

    public void createCategory(CreateCategoryDto dto, Long userId) {
        Category newCategory = categoryService.
                createCategory(dto.getCategoryTitle(), dto.getOpenToAll(), userId);

        try {
            getResponse().getWriter().write(newCategory.getId().toString());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteCategory(Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }


}
