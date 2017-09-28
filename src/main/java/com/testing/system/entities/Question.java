package com.testing.system.entities;

import com.testing.system.dao.annotations.*;

import java.util.Set;

/**
 * Created by yuri on 19.09.17.
 */

@Table("questions")
public class Question implements Identified<Long>{

    @Id
    private Long id;

    @Column("text")
    private String text;

    @Column("order_number")
    private Integer orderNumber;

    @Column("mark")
    private Double mark;

    @OneToMany(mappedBy = "question_id")
    private Set<QuestionOption> options;

    @ManyToOne(mappedBy = "test_id")
    private Test test;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Set<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(Set<QuestionOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", orderNumber=" + orderNumber +
                ", mark=" + mark +
                ", options=" + options +
                ", test=" + test +
                '}';
    }
}
