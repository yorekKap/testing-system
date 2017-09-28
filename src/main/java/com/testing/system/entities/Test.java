package com.testing.system.entities;

import com.testing.system.dao.annotations.Column;
import com.testing.system.dao.annotations.Id;
import com.testing.system.dao.annotations.OneToMany;
import com.testing.system.dao.annotations.Table;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yuri on 19.09.17.
 */
@Table("tests")
public class Test implements Identified<Long> {

    @Id
    private Long id;

    @Column("title")
    private String title;

    @Column("order_number")
    private Integer orderNumber;

    @OneToMany(mappedBy = "test_id")
    private List<Question> questions;

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

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", orderNumber=" + orderNumber +
                ", questions=" + questions.stream().map(x -> x.getId()
                .toString())
                .collect(Collectors.joining(", ")) +
                '}';
    }
}
