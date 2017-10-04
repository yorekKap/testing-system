package com.testing.system.service.impl;

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
}
