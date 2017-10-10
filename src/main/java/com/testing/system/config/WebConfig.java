package com.testing.system.config;

import com.testing.system.service.interfaces.*;
import com.testing.system.web.controllers.authorization.LoginController;
import com.testing.system.web.controllers.authorization.LogoutController;
import com.testing.system.web.controllers.authorization.RegisterController;
import com.testing.system.web.controllers.tutor.CategoriesController;
import com.testing.system.web.controllers.common.ProfileInfoController;
import com.testing.system.web.controllers.student.StudentTestRecordsController;
import com.testing.system.web.controllers.tutor.TutorTestRecordsController;
import com.testing.system.web.controllers.student.DescribeTestRecordController;
import com.testing.system.web.controllers.student.PassTestController;
import com.testing.system.web.controllers.tutor.TutorTestsController;
import com.testing.system.web.controllers.common.CategoryTestsController;
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
        TestService testService = WebAppContext.get(TestService.class);
        QuestionService questionService = WebAppContext.get(QuestionService.class);
        QuestionOptionService questionOptionService = WebAppContext.get(QuestionOptionService.class);
        TestRecordService testRecordService = WebAppContext.get(TestRecordService.class);

        CategoryTestsController categoryTestsController = new CategoryTestsController(testService, categoryService);
        ProfileInfoController profileInfoController = new ProfileInfoController(userService);

        ControllersMapper controllersMapper = ControllersMapper.createBuilder()
                .add("/login", new LoginController(authService))
                .add("/register", new RegisterController(userService))
                .add("/logout", new LogoutController())
                .add("/tutor", profileInfoController)
                .add("/student", profileInfoController)
                .add("/tutor/categories", new CategoriesController(categoryService))
                .add("/tutor/category", categoryTestsController)
                .add("/tutor/category/test", new TutorTestsController(testService))
                .add("/tutor/category/test/records", new TutorTestRecordsController(testRecordService))
                .add("/student/category", categoryTestsController)
                .add("/student/category/tests/pass-test", new PassTestController(questionService, testService))
                .add("/student/category/tests/test-record", new DescribeTestRecordController(questionService, questionOptionService))
                .add("/student/category/tests/test-records", new StudentTestRecordsController(testRecordService))
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
