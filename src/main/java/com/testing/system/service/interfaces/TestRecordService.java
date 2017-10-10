package com.testing.system.service.interfaces;

import com.testing.system.dao.dto.TestRecordWithStudentInfoDto;
import com.testing.system.entities.TestRecord;

import java.util.List;

/**
 * Created by yuri on 03.10.17.
 */
public interface TestRecordService {
    List<TestRecord> findByTestId(Long testId);

    List<TestRecord> findByTestIdAndStudentId(Long testId, Long studentId);

    List<TestRecordWithStudentInfoDto> findWithStudentInfoByTestId(Long test);

}
