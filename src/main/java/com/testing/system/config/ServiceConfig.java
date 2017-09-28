package com.testing.system.config;

import com.testing.system.dao.interfaces.CategoryDao;
import com.testing.system.dao.interfaces.UserDao;
import com.testing.system.service.impl.AuthenticationServiceImpl;
import com.testing.system.service.impl.CategoryServiceImpl;
import com.testing.system.service.impl.UserServiceImpl;
import com.testing.system.service.interfaces.AuthenticationService;
import com.testing.system.service.interfaces.CategoryService;
import com.testing.system.service.interfaces.UserService;
import org.apache.log4j.Logger;

/**
 * {@link Config} implementation for fulfilling components context
 * with service implementations
 *
 * @author yuri
 *
 */
public class ServiceConfig implements Config {
		private static final Logger log = Logger.getLogger(ServiceConfig.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(Components components) {
		UserDao userDao = WebAppContext.get(UserDao.class);
		CategoryDao categoryDao = WebAppContext.get(CategoryDao.class);

		components.add(AuthenticationService.class, new AuthenticationServiceImpl(userDao))
				  .add(UserService.class, new UserServiceImpl(userDao))
				 	.add(CategoryService.class, new CategoryServiceImpl(categoryDao));

		log.info("Service components added");
	}

}
