package com.testing.system.web.controllers.student;

import com.testing.system.exceptions.web.BadRequestException;
import com.testing.system.service.interfaces.QuestionService;
import com.testing.system.service.interfaces.TestService;
import com.testing.system.utils.CurrentUserFetcher;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;
import com.testing.system.web.dto.PassTestDto;

import java.io.IOException;

/**
 * Created by yuri on 01.10.17.
 */
public class PassTestController extends Controller {

    private QuestionService questionService;
    private TestService testService;

    public PassTestController(QuestionService questionService, TestService testService) {
        this.questionService = questionService;
        this.testService = testService;
    }

    @Override
    public String get(RequestService requestService) {
        Long testId = requestService.getLong("testId");

        requestService.setAttribute("questions",
                questionService.findByTestId(testId));
        requestService.setAttribute("testId", testId);

        return "pass-test";
    }

    @Override
    public String post(RequestService requestService) {
        PassTestDto passTestDto = requestService.getContentAsObject(PassTestDto.class);
        Long userId = CurrentUserFetcher.getCurrentUserId(getRequest());

        Long testRecordId = testService.passTest(userId, passTestDto.getTestId(),
                passTestDto.getSelectedOptionIds());

        try {
            getResponse().getWriter().write(testRecordId.toString());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return null;
    }
}
