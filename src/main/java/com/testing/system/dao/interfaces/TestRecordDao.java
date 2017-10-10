package com.testing.system.dao.interfaces;

import com.testing.system.dao.dto.TestRecordWithStudentInfoDto;
import com.testing.system.entities.TestRecord;

import java.util.List;

/**
 * Created by yuri on 01.10.17.
 */
public interface TestRecordDao extends GenericDao<TestRecord, Long> {
    Long saveTestRecordWithAnswers(Long userId, Long testId,
                                   String mark,
                                   List<Long> selectedOptionIds);

    List<TestRecord> findByTestId(Long testId);

    List<TestRecord> findByTestIdAndStudentId(Long testId, Long studentId);

    List<TestRecordWithStudentInfoDto> findWithStudentInfoByTestId(Long test);
}

