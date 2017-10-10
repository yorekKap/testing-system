package com.testing.system.service.interfaces;

import com.testing.system.exceptions.service.AuthenticationException;
import com.testing.system.web.security.UserPrincipal;


/**
 * Business layer API for convenient user authorization
 *
 * @author yuri
 */
public interface AuthenticationService {


    /**
     * Provide user with {@link UserPrincipal} when right {@code password} and {@code username}
     * are given
     *
     * @param username of existing users
     * @param password that should match password of user fetched from database
     * @return valid {@link UserPrincipal}
     * @throws AuthenticationException in case of wrong username or password provided
     */
    UserPrincipal login(String username, String password) throws AuthenticationException;
}
