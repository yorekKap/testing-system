package com.testing.system.service.impl;

import com.testing.system.dao.interfaces.QuestionDao;
import com.testing.system.entities.Question;
import com.testing.system.service.interfaces.QuestionService;

import java.util.List;

/**
 * Created by yuri on 01.10.17.
 */
public class QuestionServiceImpl implements QuestionService {

    QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> findByTestId(Long testId) {
        return questionDao.findByTestId(testId);
    }

    @Override
    public List<Question> findByTestRecordId(Long testRecordId) {
        return questionDao.findByTestRecordId(testRecordId);
    }
}
