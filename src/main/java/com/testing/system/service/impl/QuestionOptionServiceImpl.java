package com.testing.system.service.impl;

import com.testing.system.dao.interfaces.QuestionOptionDao;
import com.testing.system.service.interfaces.QuestionOptionService;

import java.util.List;

/**
 * Created by yuri on 03.10.17.
 */
public class QuestionOptionServiceImpl implements QuestionOptionService {

    QuestionOptionDao questionOptionDao;

    public QuestionOptionServiceImpl(QuestionOptionDao questionOptionDao) {
        this.questionOptionDao = questionOptionDao;
    }

    @Override
    public List<Long> findIdsByTestRecordId(Long testRecordId) {
        return questionOptionDao.findIdsByTestRecordId(testRecordId);
    }
}
