package com.testing.system.web.controllers.common;

import com.testing.system.exceptions.web.BadRequestException;
import com.testing.system.service.interfaces.TestService;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yuri on 29.09.17.
 */
public class CategoryTestsController extends Controller {

    private TestService testService;

    public CategoryTestsController(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String get(RequestService requestService) {
        Long categoryId = requestService.getLong("categoryId")
                .orElseThrow(() -> new BadRequestException("No categoryId parameter"));

        requestService.setAttribute("tests", testService.findAllByCategoryId(categoryId));
        requestService.setAttribute("categoryId", categoryId);

        return "category-tests";
    }
}
