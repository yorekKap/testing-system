package com.testing.system.web.controllers.common;

import com.testing.system.exceptions.web.BadRequestExceptionBuilder;
import com.testing.system.service.interfaces.TestRecordService;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;

/**
 * Created by yuri on 03.10.17.
 */
public class TestRecordsController extends Controller{

    TestRecordService testRecordService;

    public TestRecordsController(TestRecordService testRecordService) {
        this.testRecordService = testRecordService;
    }

    @Override
    public String get(RequestService requestService) {
       Long testId = requestService.getLong("testId")
               .orElseThrow(() -> BadRequestExceptionBuilder.noParameter("testId"));

       requestService.setAttribute("testRecords",
               testRecordService.findByTestId(testId));

       return "test-records";
    }
}
