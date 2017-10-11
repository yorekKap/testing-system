package com.testing.system.service.interfaces;

import java.util.List;

/**
 * Business layer API for convenient support of operations with
 * {@link com.testing.system.entities.QuestionOption} entity.
 *
 * @author yuri
 *
 */
public interface QuestionOptionService {
    List<Long> findIdsByTestRecordId(Long testRecordId);
}
