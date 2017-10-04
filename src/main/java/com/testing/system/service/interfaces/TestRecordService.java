package com.testing.system.service.interfaces;

import com.testing.system.entities.TestRecord;

import java.util.List;

/**
 * Created by yuri on 03.10.17.
 */
public interface TestRecordService {
    List<TestRecord> findByTestId(Long testId);
}
