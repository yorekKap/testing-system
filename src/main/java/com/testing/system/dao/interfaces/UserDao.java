package com.testing.system.dao.interfaces;

import com.testing.system.entities.User;

/**
 * DAO class for managing {@link User} entities
 *
 * @author yuri
 */
public interface UserDao extends GenericDao<User, Long> {
    User findByUsername(String username);
}
