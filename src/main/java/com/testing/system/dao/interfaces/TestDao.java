package com.testing.system.dao.interfaces;

import com.testing.system.entities.Test;
import com.testing.system.web.dto.CreateTestDto;

import java.util.List;

/**
 * DAO class for managing {@link Test} entities
 *
 * @author yuri
 */
public interface TestDao extends GenericDao<Test, Long> {
    List<Test> findAllByCategoryId(Long categoryId);

    void createTest(CreateTestDto createTestDto);

}
