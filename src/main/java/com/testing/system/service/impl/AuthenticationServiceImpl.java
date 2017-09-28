package com.testing.system.service.impl;

import com.testing.system.dao.interfaces.UserDao;
import com.testing.system.entities.User;
import com.testing.system.exceptions.service.AuthenticationException;
import com.testing.system.service.interfaces.AuthenticationService;
import com.testing.system.web.security.UserPrincipal;
import org.apache.log4j.Logger;

public class AuthenticationServiceImpl implements AuthenticationService {
	private static final Logger log = Logger.getLogger(AuthenticationServiceImpl.class);

	UserDao userDao;

	public AuthenticationServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserPrincipal login(String username, String password)throws AuthenticationException {
		User user = userDao.findByUsername(username);

		if (user == null) {
			log.warn("Login failed: wrong username");
			throw new AuthenticationException(AuthenticationException.WRONG_USERNAME);
		}
		if (!user.getPassword().equals(password)) {
			log.warn("Login failed: wrong password");
			throw new AuthenticationException(AuthenticationException.WRONG_PASSWORD);
		}

		log.info("UserPricipal with username: " + username + ";password: " + password + " is created");
		return new UserPrincipal(username, user.getUserRole(), user.getId());
	}



}
