package com.testing.system.web.controllers.tutor;

import com.testing.system.exceptions.web.BadRequestException;
import com.testing.system.service.interfaces.TestService;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;
import com.testing.system.web.dispatcher.enums.ActionType;
import com.testing.system.web.dto.CreateTestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yuri on 29.09.17.
 */
public class TutorTestsController extends Controller{

    private TestService testService;

    public TutorTestsController(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String get(RequestService requestService) {
        Long categoryId = requestService.getLong("categoryId");

        requestService.setAttribute("categoryId", categoryId);
        return "create-new-test";
    }

    @Override
    public String post(RequestService requestService) {
        ActionType actionType = requestService.getAction();

        if(actionType == ActionType.CREATE){
            createTest(requestService);
        } if(actionType == ActionType.UPDATE){
            updateTest(requestService);
        } if (actionType == ActionType.DELETE){
            deleteTest(requestService);
        }

        return null;
    }

    public void createTest(RequestService requestService){
        CreateTestDto dto = requestService.
                getContentAsObject(CreateTestDto.class);

        testService.createTest(dto.getCategoryId(), dto.getTitle(), dto.getQuestions());
    }

    public void updateTest(RequestService requestService){
        String title = requestService.getString("title");
        Long id = requestService.getLong("id");

        testService.updateTest(id, title);
    }

    public void deleteTest(RequestService requestService){
        Long testId = requestService.getLong("testId");
        testService.deleteTest(testId);
    }
}