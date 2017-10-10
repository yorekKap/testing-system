package com.testing.system.service.impl;

import com.testing.system.dao.interfaces.QuestionDao;
import com.testing.system.dao.interfaces.TestDao;
import com.testing.system.dao.interfaces.TestRecordDao;
import com.testing.system.entities.Question;
import com.testing.system.entities.Test;
import com.testing.system.entities.TestRecord;
import com.testing.system.service.interfaces.TestService;
import com.testing.system.web.dto.CreateTestDto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by yuri on 29.09.17.
 */
public class TestServiceImpl implements TestService {

    private TestDao testDao;
    private QuestionDao questionDao;
    private TestRecordDao testRecordDao;

    public TestServiceImpl(TestDao testDao, QuestionDao questionDao, TestRecordDao testRecordDao) {
        this.testDao = testDao;
        this.questionDao = questionDao;
        this.testRecordDao = testRecordDao;
    }

    @Override
    public List<Test> findAllByCategoryId(Long categoryId) {
        return testDao.findAllByCategoryId(categoryId);
    }

    @Override
    public void createTest(CreateTestDto createTestDto) {
        testDao.createTest(createTestDto);
    }

    @Override
    public Long passTest(Long userId, Long testId, List<Long> selectedOptionIds) {
        List<Question> answeredQuestions = questionDao.findByTestId(testId);

        double maxMark = 0;
        double actualMark = 0;
        for (Question question : answeredQuestions) {
            maxMark += question.getMark();
            if (question.isAnsweredCorrectly(selectedOptionIds)) {
                actualMark += question.getMark();
            }
        }
        String mark = getTestRecordMark(maxMark, actualMark);

        return testRecordDao.saveTestRecordWithAnswers(userId, testId,
                mark, selectedOptionIds);
    }

    private String getTestRecordMark(double maxMark, double actualMark) {
        if (maxMark % 1 == 0) {
            return (int) actualMark + "/" + (int) maxMark;
        } else {
            DecimalFormat df = new DecimalFormat("#.##");
            return df.format(actualMark) + "/" + df.format(maxMark);
        }
    }

    @Override
    public void updateTest(Long id, String title, Integer orderNumber) {
        testDao.update(new Test(id, title, orderNumber));
    }

    @Override
    public void deleteTest(Long testId) {
        testDao.delete(testId);
    }
}
