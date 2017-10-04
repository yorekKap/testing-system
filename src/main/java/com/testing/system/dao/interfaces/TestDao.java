package com.testing.system.dao.interfaces;

import com.testing.system.entities.Question;
import com.testing.system.entities.Test;
import com.testing.system.web.dto.CreateTestDto;

import java.util.List;

/**
 * Created by yuri on 29.09.17.
 */
public interface TestDao extends GenericDao<Test, Long> {
    List<Test> findAllByCategoryId(Long categoryId);
    void createTest(CreateTestDto createTestDto);

}
