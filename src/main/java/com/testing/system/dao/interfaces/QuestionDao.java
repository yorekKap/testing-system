package com.testing.system.dao.interfaces;

import com.testing.system.entities.Question;

import java.util.List;

/**
 * DAO class for managing {@link Question} entities
 *
 * @author yuri
 */
public interface QuestionDao extends GenericDao<Question, Long> {
    List<Question> findByTestId(Long testId);

    List<Question> findByTestRecordId(Long testRecordId);

}
