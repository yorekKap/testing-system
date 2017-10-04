package com.testing.system.web.dto;

import com.testing.system.dao.annotations.Column;
import com.testing.system.dao.annotations.Id;
import com.testing.system.entities.Question;

import java.util.List;

/**
 * Created by yuri on 29.09.17.
 */
public class CreateTestDto {

    private Long categoryId;

    private String title;

    private Integer orderNumber;

    private List<Question> questions;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "CreateTestDto{" +
                "title='" + title + '\'' +
                ", orderNumber=" + orderNumber +
                ", questions=" + questions +
                '}';
    }
}
