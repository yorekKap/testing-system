package com.testing.system.web.controllers.tutor;

import com.testing.system.entities.TestRecord;
import com.testing.system.exceptions.web.BadRequestExceptionBuilder;
import com.testing.system.service.interfaces.TestRecordService;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;

import java.util.List;
import java.util.Optional;

/**
 * Created by yuri on 03.10.17.
 */
public class TutorTestRecordsController extends Controller {

    TestRecordService testRecordService;

    public TutorTestRecordsController(TestRecordService testRecordService) {
        this.testRecordService = testRecordService;
    }

    @Override
    public String get(RequestService requestService) {
        Long testId = requestService.getLong("testId");

        requestService.setAttribute("testRecords",
                testRecordService.findWithStudentInfoByTestId(testId));
        return "tutor-test-records";
    }
}
