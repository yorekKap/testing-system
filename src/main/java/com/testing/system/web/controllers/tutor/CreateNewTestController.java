package com.testing.system.web.controllers.tutor;

import com.sun.org.apache.regexp.internal.RE;
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
public class CreateNewTestController extends Controller{

    private TestService testService;

    public CreateNewTestController(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String get(RequestService requestService) {
        Long categoryId = requestService.getLong("categoryId")
                .orElseThrow(() ->new BadRequestException("No categoryId parameter"));

        requestService.setAttribute("categoryId", categoryId);
        return "create-new-test";
    }

    @Override
    public String post(RequestService requestService) {
        ActionType actionType = requestService.getAction();

        if(actionType == ActionType.CREATE){
            createTest(requestService);
        }

        return null;
    }

    public void createTest(RequestService requestService){
        CreateTestDto createTestDto = requestService.
                getContentAsObject(CreateTestDto.class);

        testService.createTest(createTestDto);
    }
}