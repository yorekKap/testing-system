package com.testing.system.dao.interfaces;

import com.testing.system.entities.QuestionOption;

import java.util.List;

/**
 * Created by yuri on 03.10.17.
 */
public interface QuestionOptionDao extends GenericDao<QuestionOption, Long> {
    List<QuestionOption> findByTestRecordId(Long testRecordId);
    List<Long> findIdsByTestRecordId(Long testRecordId);
}
