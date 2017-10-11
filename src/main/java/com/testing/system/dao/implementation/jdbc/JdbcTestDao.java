package com.testing.system.dao.implementation.jdbc;

import com.testing.system.dao.builder.InsertQuery;
import com.testing.system.dao.interfaces.TestDao;
import com.testing.system.dao.parsers.ObjectToColumnValueMapParser;
import com.testing.system.entities.Question;
import com.testing.system.entities.QuestionOption;
import com.testing.system.entities.Test;
import com.testing.system.web.dto.CreateTestDto;

import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTestDao extends AbstractJdbcDaoAdapter<Test> implements TestDao {

    public JdbcTestDao(DataSource dataSource) {
        super(dataSource, Test.class);
    }

    @Override
    public List<Test> findAllByCategoryId(Long categoryId) {
        return builder.select("*")
                .where("category_id").isEquals(categoryId)
                .execute(Test.class);
    }

    @Override
    public void createTest(Long categoryId, String title, List<Question> questions) {
        try {
            builder.beginTransaction();

            builder.insert()
                    .newValuesList()
                    .addValue("category_id", categoryId)
                    .addValue("title", title)
                    .execute();

            Long testId = getLastSavedId();

            InsertQuery questionsInsertQuery = builder.insert()
                    .into("questions");

            for (Question question : questions) {
                questionsInsertQuery.newValuesList()
                        .addValue("text", question.getText())
                        .addValue("order_number", question.getOrderNumber())
                        .addValue("mark", question.getMark())
                        .addValue("test_id", testId);
            }
            questionsInsertQuery.execute();


            Map<Integer, Long> questionIds = new HashMap<>();
            builder.select("id", "order_number")
                    .from("questions")
                    .where("test_id").isEquals(testId)
                    .execute(rs -> {
                        questionIds.put(rs.getInt("order_number"),
                                rs.getLong("id"));
                        return null;
                    });

            InsertQuery optionsInsertQuery = builder.insert()
                    .into("question_options");
            for (Question question : questions) {
                for (QuestionOption option : question.getOptions()) {
                    optionsInsertQuery.newValuesList()
                            .addValue("text", option.getText())
                            .addValue("is_right", option.getRight())
                            .addValue("question_id",
                                    questionIds.get(question.getOrderNumber()));
                }
            }

            optionsInsertQuery.execute();

            builder.commit();
        } catch (Exception e) {
            builder.rollback();
        }
    }
}
