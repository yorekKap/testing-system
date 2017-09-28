package com.testing.system.entities;

import com.testing.system.dao.annotations.Column;
import com.testing.system.dao.annotations.Id;
import com.testing.system.dao.annotations.ManyToOne;
import com.testing.system.dao.annotations.ParseEnum;
import com.testing.system.entities.enums.AccessRight;

/**
 * Created by yuri on 23.09.17.
 */
public class TutorGroupAccessRight {

    @Id
    private Long id;

    @ManyToOne(mappedBy = "group_id")
    private Group group;

    @ParseEnum
    @Column("access_right")
    private AccessRight accessRight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public AccessRight getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(AccessRight accessRight) {
        this.accessRight = accessRight;
    }
}
