package com.testing.system.service.interfaces;

import com.testing.system.entities.Question;

import java.util.List;

/**
 * Created by yuri on 01.10.17.
 */
public interface QuestionService {
    List<Question> findByTestId(Long testId);
    List<Question> findByTestRecordId(Long testRecordId);
}
