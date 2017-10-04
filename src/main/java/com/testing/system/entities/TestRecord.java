package com.testing.system.entities;

import com.testing.system.dao.annotations.*;
import com.testing.system.dao.annotations.enums.DateType;

import java.util.Date;
import java.util.List;

/**
 * Created by yuri on 27.09.17.
 */
@Table("test_records")
public class TestRecord implements Identified<Long>{

    @Id
    private Long id;

    @Column("mark")
    private String mark;

    @ManyToMany(table = "test_records_question_options",
            mappedBy = "test_record_id")
    private List<QuestionOption> chosenOptions;

    @Column("date")
    @DateSQL(DateType.DATE_TIME)
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public List<QuestionOption> getChosenOptions() {
        return chosenOptions;
    }

    public void setChosenOptions(List<QuestionOption> chosenOptions) {
        this.chosenOptions = chosenOptions;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
