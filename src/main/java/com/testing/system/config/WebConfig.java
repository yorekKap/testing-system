package com.testing.system.config;

import com.testing.system.service.interfaces.AuthenticationService;
import com.testing.system.service.interfaces.CategoryService;
import com.testing.system.service.interfaces.UserService;
import com.testing.system.web.controllers.authorization.LoginController;
import com.testing.system.web.controllers.authorization.LogoutController;
import com.testing.system.web.controllers.authorization.RegisterController;
import com.testing.system.web.controllers.tutor.TutorCategoriesController;
import com.testing.system.web.dispatcher.ControllersMapper;
import com.testing.system.web.resolvers.ViewResolver;
import org.apache.log4j.Logger;

/**
 * {@link Config} implementation for fulfilling components context
 * with web components
 *
 * @author yuri
 */
public class WebConfig implements Config {
    private final static Logger log = Logger.getLogger(WebConfig.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(Components components) {
        components.add(ControllersMapper.class, getControllersMapper())
                .add(ViewResolver.class, getViewResolver());
    }

    /**
     * @return {@link ControllersMapper} instance
     */
    public ControllersMapper getControllersMapper() {
        AuthenticationService authService = WebAppContext.get(AuthenticationService.class);
        UserService userService = WebAppContext.get(UserService.class);
        CategoryService categoryService = WebAppContext.get(CategoryService.class);

        ControllersMapper controllersMapper = ControllersMapper.createBuilder()
                .add("/login", new LoginController(authService))
                .add("/register", new RegisterController(userService))
                .add("/logout", new LogoutController())
                .add("/tutor/category", new TutorCategoriesController(categoryService))
                .build();

        log.info("ControllersMapper initialized");

        return controllersMapper;
    }

    /**
     * @return {@link ViewResolver}
     */
    public ViewResolver getViewResolver() {
        return ViewResolver.getBuilder().setPrefix("/WEB-INF/views/").setSuffix(".jsp").create();
    }


    //commented to show singleton usage
    /**
     * @param mapper to link it with {@link ConrollersMapper}
     * @return @{link RequestHelper}
     *//*
    public RequestHelper getRequestHelper(ControllersMapper mapper) {
		return new RequestHelper(mapper);
	}*/

}
