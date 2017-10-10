package com.testing.system.dao.dto;

import com.testing.system.dao.annotations.*;
import com.testing.system.dao.annotations.enums.DateType;
import com.testing.system.entities.Identified;
import com.testing.system.entities.User;

import java.util.Date;

/**
 * Created by yuri on 08.10.17.
 */
@Table("test_records")
public class TestRecordWithStudentInfoDto implements Identified<Long> {
    @Id
    private Long id;

    @Column("mark")
    private String mark;

    @Column("date")
    @DateSQL(DateType.DATE_TIME)
    private Date date;

    @ManyToOne(mappedBy = "student_id")
    private User student;

    public TestRecordWithStudentInfoDto() {
    }

    public TestRecordWithStudentInfoDto(Long id, String mark, Date date, User student) {
        this.id = id;
        this.mark = mark;
        this.date = date;
        this.student = student;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }
}
