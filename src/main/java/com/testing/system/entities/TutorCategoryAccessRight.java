package com.testing.system.entities;

import com.testing.system.dao.annotations.*;
import com.testing.system.entities.enums.AccessRight;

/**
 * Created by yuri on 27.09.17.
 */
@Table("tutors_categories_access_rights")
public class TutorCategoryAccessRight implements Identified<Long> {

    @Id
    private Long id;

    @ManyToOne(mappedBy = "category_id")
    private Category category;

    @ParseEnum
    @Column("access_right")
    private AccessRight accessRight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public AccessRight getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(AccessRight accessRight) {
        this.accessRight = accessRight;
    }
}
