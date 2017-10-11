package com.testing.system.entities;

import com.testing.system.dao.annotations.Column;
import com.testing.system.dao.annotations.Id;
import com.testing.system.dao.annotations.Table;

/**
 * Created by yuri on 19.09.17.
 */
@Table("tests")
public class Test implements Identified<Long> {

    @Id
    private Long id;

    @Column("title")
    private String title;

    public Test() {}

    public Test(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
