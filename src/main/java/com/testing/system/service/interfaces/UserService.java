package com.testing.system.service.interfaces;

import com.testing.system.entities.User;

/**
 * Business layer API for convenient support of operations
 * with {@link User} entity.
 *
 * @author yuri
 */
public interface UserService {
    boolean save(User user);

    User findById(Long id);

    User findByUsername(String username);
}
