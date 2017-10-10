package com.testing.system.web.controllers.student;

import com.testing.system.entities.TestRecord;
import com.testing.system.service.interfaces.TestRecordService;
import com.testing.system.utils.CurrentUserFetcher;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;

import java.util.List;
import java.util.Optional;

/**
 * Created by yuri on 08.10.17.
 */
public class StudentTestRecordsController extends Controller {
    TestRecordService testRecordService;

    public StudentTestRecordsController(TestRecordService testRecordService) {
        this.testRecordService = testRecordService;
    }

    @Override
    public String get(RequestService requestService) {
        Long testId = requestService.getLong("testId");
        Long studentId = CurrentUserFetcher.getCurrentUserId(getRequest());

        requestService.setAttribute("testRecords",
                testRecordService.findByTestIdAndStudentId(testId, studentId));

        return "test-records";
    }
}
