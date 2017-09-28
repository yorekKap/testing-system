package com.testing.system.entities;

import com.testing.system.dao.annotations.Column;
import com.testing.system.dao.annotations.Id;
import com.testing.system.dao.annotations.ManyToMany;
import com.testing.system.dao.annotations.Table;

import java.util.List;

/**
 * Created by yuri on 27.09.17.
 */
@Table("test_records")
public class TestRecord {

    @Id
    private Long id;

    @Column("right_answers_percentage")
    private Integer rightsAnswersPercentage;

    @ManyToMany(table = "test_records_question_options",
            mappedBy = "test_record_id")
    private List<QuestionOption> chosenOptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRightsAnswersPercentage() {
        return rightsAnswersPercentage;
    }

    public void setRightsAnswersPercentage(Integer rightsAnswersPercentage) {
        this.rightsAnswersPercentage = rightsAnswersPercentage;
    }

    public List<QuestionOption> getChosenOptions() {
        return chosenOptions;
    }

    public void setChosenOptions(List<QuestionOption> chosenOptions) {
        this.chosenOptions = chosenOptions;
    }
}
