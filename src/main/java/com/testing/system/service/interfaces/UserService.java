package com.testing.system.service.interfaces;

/**
 * Business layer API for convenient support of operations
 * with {@link User} entity.
 *
 * @author yuri
 */

import com.testing.system.entities.User;

/**
 * @author yuri
 */
public interface UserService {
    boolean save(User user);

    User findById(Long id);

    User findByUsername(String username);
}
