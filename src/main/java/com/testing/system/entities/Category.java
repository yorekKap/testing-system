package com.testing.system.entities;

import com.testing.system.dao.annotations.Column;
import com.testing.system.dao.annotations.Id;
import com.testing.system.dao.annotations.Table;

/**
 * Created by yuri on 22.09.17.
 */
@Table("categories")
public class Category implements Identified<Long>{

    @Id
    private Long id;

    @Column("title")
    private String title;

    public Category() {
    }

    public Category(Long id, String title) {
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
}
