package com.testing.system.web.controllers.student;

import com.testing.system.exceptions.web.BadRequestException;
import com.testing.system.exceptions.web.BadRequestExceptionBuilder;
import com.testing.system.service.interfaces.QuestionOptionService;
import com.testing.system.service.interfaces.QuestionService;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;

/**
 * Created by yuri on 03.10.17.
 */
public class DescribeTestRecordController extends Controller{

    QuestionService questionService;
    QuestionOptionService questionOptionService;

    public DescribeTestRecordController(QuestionService questionService,
                                        QuestionOptionService questionOptionService) {
        this.questionService = questionService;
        this.questionOptionService = questionOptionService;
    }

    @Override
    public String get(RequestService requestService) {
        Long testRecordId = requestService.getLong("testRecordId");

        requestService.setAttribute("questions",
                questionService.findByTestRecordId(testRecordId));
        requestService.setAttribute("chosenOptionIds",
                questionOptionService.findIdsByTestRecordId(testRecordId));

        return "test-record";

    }
}
