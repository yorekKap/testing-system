package com.testing.system.web.dto;

import java.util.List;

/**
 * Created by yuri on 01.10.17.
 */
public class PassTestDto {
    private Long testId;
    private List<Long> selectedOptionIds;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public List<Long> getSelectedOptionIds() {
        return selectedOptionIds;
    }

    public void setSelectedOptionIds(List<Long> selectedOptionIds) {
        this.selectedOptionIds = selectedOptionIds;
    }
}
