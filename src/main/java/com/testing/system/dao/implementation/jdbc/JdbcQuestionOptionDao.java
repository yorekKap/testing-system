package com.testing.system.dao.implementation.jdbc;

import com.testing.system.dao.interfaces.QuestionOptionDao;
import com.testing.system.entities.QuestionOption;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by yuri on 03.10.17.
 */
public class JdbcQuestionOptionDao extends AbstractJdbcDaoAdapter<QuestionOption>
        implements QuestionOptionDao{

    public JdbcQuestionOptionDao(DataSource dataSource) {
        super(dataSource, QuestionOption.class);
    }

    @Override
    public List<QuestionOption> findByTestRecordId(Long testRecordId) {
        return builder.select("*")
                .from("question_options qo")
                .leftJoin("test_records_question_options trqo")
                .on("qo.id", "trqo.question_option_id")
                .where("trqo.test_record_id").isEquals(testRecordId)
                .execute(QuestionOption.class);
    }

    @Override
    public List<Long> findIdsByTestRecordId(Long testRecordId) {
        return builder.select("question_option_id")
                .from("test_records_question_options")
                .where("test_record_id").isEquals(testRecordId)
                .execute(rs -> rs.getLong("question_option_id"));
    }
}
