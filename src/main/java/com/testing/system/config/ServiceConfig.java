package com.testing.system.config;

import com.testing.system.dao.interfaces.*;
import com.testing.system.service.impl.*;
import com.testing.system.service.interfaces.*;
import org.apache.log4j.Logger;

/**
 * {@link Config} implementation for fulfilling components context
 * with service implementations
 *
 * @author yuri
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
        TestDao testDao = WebAppContext.get(TestDao.class);
        QuestionDao questionDao = WebAppContext.get(QuestionDao.class);
        TestRecordDao testRecordDao = WebAppContext.get(TestRecordDao.class);
        QuestionOptionDao questionOptionDao = WebAppContext.get(QuestionOptionDao.class);

        components.add(AuthenticationService.class, new AuthenticationServiceImpl(userDao))
                .add(UserService.class, new UserServiceImpl(userDao))
                .add(CategoryService.class, new CategoryServiceImpl(categoryDao))
                .add(TestService.class, new TestServiceImpl(testDao, questionDao, testRecordDao))
                .add(QuestionService.class, new QuestionServiceImpl(questionDao))
                .add(QuestionOptionService.class, new QuestionOptionServiceImpl(questionOptionDao))
                .add(TestRecordService.class, new TestRecordServiceImpl(testRecordDao));

        log.info("Service components added");
    }

}
