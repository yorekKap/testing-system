package com.testing.system.service.interfaces;

import com.testing.system.entities.Question;
import com.testing.system.entities.Test;
import com.testing.system.web.dto.CreateTestDto;

import java.util.List;

/**
 * Business layer API for convenient support of operations with {@link Test}
 * entity.
 *
 * @author yuri
 *
 */
public interface TestService {
    List<Test> findAllByCategoryId(Long categoryId);

    void createTest(CreateTestDto createTestDto);

    Long passTest(Long userId, Long testId, List<Long> selectedOptionIds);

    void updateTest(Long id, String title, Integer orderNumber);

    void deleteTest(Long testId);
}
