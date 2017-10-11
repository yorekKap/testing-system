package com.testing.system.dao.interfaces;

import com.testing.system.entities.QuestionOption;

import java.util.List;

/**
 * DAO class for managing {@link QuestionOption} entities
 *
 * @author yuri
 */
public interface QuestionOptionDao extends GenericDao<QuestionOption, Long> {
    List<QuestionOption> findByTestRecordId(Long testRecordId);

    List<Long> findIdsByTestRecordId(Long testRecordId);
}
