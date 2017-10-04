package com.testing.system.dao.interfaces;

import com.testing.system.entities.Question;

import java.util.List;

/**
 * Created by yuri on 01.10.17.
 */
public interface QuestionDao extends GenericDao<Question, Long> {
    List<Question> findByTestId(Long testId);
    List<Question> findByTestRecordId(Long testRecordId);

}
