package com.testing.system.dao.interfaces;

import com.testing.system.entities.User;

/**
 * The interface that defines DAO for {@link User} oldentities
 *
 * @author yuri
 *
 */
public interface UserDao extends GenericDao<User, Long> {
    User findByUsername(String username);
}
