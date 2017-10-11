package com.testing.system.service.interfaces;

import com.testing.system.entities.Question;

import java.util.List;

/**
 * Business layer API for convenient support of operations with {@link Question}
 * entity.
 *
 * @author yuri
 *
 */
public interface QuestionService {
    List<Question> findByTestId(Long testId);

    List<Question> findByTestRecordId(Long testRecordId);
}
