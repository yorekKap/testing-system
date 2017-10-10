package com.testing.system.web.controllers.common;

import com.testing.system.entities.Category;
import com.testing.system.entities.enums.Role;
import com.testing.system.service.interfaces.CategoryService;
import com.testing.system.service.interfaces.TestService;
import com.testing.system.utils.CurrentUserFetcher;
import com.testing.system.web.constants.SessionScopeAttributes;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;
import com.testing.system.web.dispatcher.enums.ActionType;
import com.testing.system.web.security.UserPrincipal;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by yuri on 29.09.17.
 */
public class CategoryTestsController extends Controller {

    private TestService testService;
    private CategoryService categoryService;

    public CategoryTestsController(TestService testService, CategoryService categoryService) {
        this.testService = testService;
        this.categoryService = categoryService;
    }

    @Override
    public String get(RequestService requestService) {
        Long categoryId = requestService.getLong("categoryId");

        Category category = categoryService.findById(categoryId);
        if(category == null){
            UserPrincipal userPrincipal = requestService.getSessionService()
                    .findAttribute(SessionScopeAttributes.PRINCIPAL, UserPrincipal.class);

            String toRedirect = userPrincipal.getRole() == Role.TUTOR ? "/tutor" : "/student";

            try {
                getResponse().sendRedirect(toRedirect);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        requestService.setAttribute("tests",
                testService.findAllByCategoryId(categoryId));

        requestService.setAttribute("category",
                category);

        getRequest().getSession().setAttribute(SessionScopeAttributes.CURRENT_CATEGORY_ID, categoryId);
        return "category-tests";
    }

}
