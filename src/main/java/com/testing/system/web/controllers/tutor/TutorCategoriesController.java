package com.testing.system.web.controllers.tutor;

import com.testing.system.service.interfaces.CategoryService;
import com.testing.system.utils.CurrentUserFetcher;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dto.CreateCategoryDto;
import com.testing.system.web.parsers.RequestContentParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public String get(HttpServletRequest request, HttpServletResponse response) {
        Long userId = CurrentUserFetcher.getCurrentUserId(request);
        request.setAttribute("categoryAccessRights",
                categoryService.getCategoriesWithRightsByTutorId(userId));

        return "tutor-categories";
    }

    @Override
    public String post(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter(ACTION_PARAMETER);

        if (action.equals("CREATE")) {
            createCategory(RequestContentParser.parse(request, CreateCategoryDto.class),
                    CurrentUserFetcher.getCurrentUserId(request));
        } else if (action.equals("DELETE")) {
            deleteCategory(Long.valueOf(request.getParameter("categoryId")));
        }

        return null;
    }

    public void createCategory(CreateCategoryDto dto, Long userId) {
        categoryService.createCategory(dto.getCategoryTitle(), dto.getOpenToAll(), userId);
    }

    public void deleteCategory(Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }


}
