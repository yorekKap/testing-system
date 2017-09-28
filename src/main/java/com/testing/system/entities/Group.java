package com.testing.system.entities;

import com.testing.system.dao.annotations.Column;
import com.testing.system.dao.annotations.Id;
import com.testing.system.dao.annotations.ManyToMany;
import com.testing.system.dao.annotations.Table;

import java.util.List;

/**
 * Created by yuri on 27.09.17.
 */
@Table("groups")
public class Group {

    @Id
    private Long id;

    @Column("title")
    private String title;

    @ManyToMany(table = "students_groups", mappedBy = "group_id")
    private List<User> students;


}
