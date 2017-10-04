package com.testing.system.service.interfaces;

import java.util.List;

/**
 * Created by yuri on 03.10.17.
 */
public interface QuestionOptionService {
    List<Long> findIdsByTestRecordId(Long testRecordId);
}
