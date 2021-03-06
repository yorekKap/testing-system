package com.testing.system.service.impl;

import com.testing.system.dao.dto.TestRecordWithStudentInfoDto;
import com.testing.system.dao.interfaces.TestRecordDao;
import com.testing.system.entities.TestRecord;
import com.testing.system.service.interfaces.TestRecordService;

import java.util.List;

/**
 * Created by yuri on 03.10.17.
 */
public class TestRecordServiceImpl implements TestRecordService {

    TestRecordDao testRecordDao;

    public TestRecordServiceImpl(TestRecordDao testRecordDao) {
        this.testRecordDao = testRecordDao;
    }

    @Override
    public List<TestRecord> findByTestId(Long testId) {
        return testRecordDao.findByTestId(testId);
    }

    @Override
    public List<TestRecord> findByTestIdAndStudentId(Long testId, Long studentId) {
        return testRecordDao.findByTestIdAndStudentId(testId, studentId);
    }

    @Override
    public List<TestRecordWithStudentInfoDto> findWithStudentInfoByTestId(Long testId) {
        return testRecordDao.findWithStudentInfoByTestId(testId);
    }
}
