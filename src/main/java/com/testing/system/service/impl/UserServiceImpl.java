package com.testing.system.service.impl;

import com.testing.system.dao.interfaces.UserDao;
import com.testing.system.entities.User;
import com.testing.system.service.interfaces.UserService;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {
	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean save(User user) {
		return userDao.persist(user);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
}
