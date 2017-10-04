package com.testing.system.dao.implementation.jdbc;

import com.testing.system.dao.interfaces.QuestionDao;
import com.testing.system.entities.Question;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by yuri on 01.10.17.
 */
public class JdbcQuestionDao extends AbstractJdbcDaoAdapter<Question> implements QuestionDao {

    public JdbcQuestionDao(DataSource dataSource) {
        super(dataSource, Question.class);
    }

    @Override
    public List<Question> findByTestId(Long testId) {
        return builder.select("*")
                .from("questions q")
                .leftJoin("question_options o")
                .on("q.id", "o.question_id")
                .where("test_id").isEquals(testId)
                .execute(Question.class);
    }

    @Override
    public List<Question> findByTestRecordId(Long testRecordId) {
        return builder.select("*")
                .from("questions q")
                .leftJoin("tests t")
                .on("q.test_id", "t.id")
                .leftJoin("test_records r")
                .on("t.id", "r.test_id")
                .leftJoin("question_options o")
                .on("q.id", "o.question_id")
                .where("r.id").isEquals(testRecordId)
                .execute(Question.class);
    }
}
