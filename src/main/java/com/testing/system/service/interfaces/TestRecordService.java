package com.testing.system.service.interfaces;

import com.testing.system.dao.dto.TestRecordWithStudentInfoDto;
import com.testing.system.entities.TestRecord;

import java.util.List;

/**
 * Business layer API for convenient support of operations with {@link TestRecord}
 * entity.
 *
 * @author yuri
 *
 */
public interface TestRecordService {
    List<TestRecord> findByTestId(Long testId);

    List<TestRecord> findByTestIdAndStudentId(Long testId, Long studentId);

    List<TestRecordWithStudentInfoDto> findWithStudentInfoByTestId(Long test);
}
