package com.testing.system.dao.implementation.jdbc;

import com.testing.system.dao.builder.InsertQuery;
import com.testing.system.dao.dto.TestRecordWithStudentInfoDto;
import com.testing.system.dao.interfaces.TestRecordDao;
import com.testing.system.entities.TestRecord;
import com.testing.system.exceptions.dao.MySQLException;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public class JdbcTestRecordDao extends AbstractJdbcDaoAdapter<TestRecord> implements TestRecordDao {

    public JdbcTestRecordDao(DataSource dataSource) {
        super(dataSource, TestRecord.class);
    }

    @Override
    public Long saveTestRecordWithAnswers(Long userId, Long testId,
                                          String mark, List<Long> selectedOptionIds) {
        try {
            builder.beginTransaction();
            builder.insert()
                    .newValuesList()
                    .addValue("test_id", testId)
                    .addValue("student_id", userId)
                    .addValue("mark", mark)
                    .addValue("date", new Date())
                    .execute();

            Long testRecordId = getLastSavedId();

            InsertQuery optionsInsertQuery = builder.insert()
                    .into("test_records_question_options");

            for (Long selectedId : selectedOptionIds) {
                optionsInsertQuery.newValuesList()
                        .addValue("test_record_id", testRecordId)
                        .addValue("question_option_id", selectedId);
            }

            optionsInsertQuery.execute();

            builder.commit();
            return testRecordId;

        } catch (Exception e) {
            builder.rollback();
            throw new MySQLException(e.getMessage());
        }
    }

    @Override
    public List<TestRecord> findByTestId(Long testId) {
        return builder.select("*")
                .where("test_id").isEquals(testId)
                .execute(TestRecord.class);
    }

    @Override
    public List<TestRecord> findByTestIdAndStudentId(Long testId, Long studentId) {
        return builder.select("*")
                .where("test_id").isEquals(testId)
                .and("student_id").isEquals(studentId)
                .execute(TestRecord.class);
    }

    @Override
    public List<TestRecordWithStudentInfoDto> findWithStudentInfoByTestId(Long testId) {
        return builder.select("*")
                .from("test_records tr")
                .join("users u")
                .on("tr.id", builder
                        .select("MAX(id)")
                        .from("test_records")
                        .where("student_id").isEqualVariable("u.id")
                        .and("test_id").isEquals(testId))
                .execute(TestRecordWithStudentInfoDto.class);

    }
}
