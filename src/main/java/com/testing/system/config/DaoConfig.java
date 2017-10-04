package com.testing.system.config;


import com.testing.system.dao.factories.DataSourceFactory;
import com.testing.system.dao.implementation.jdbc.*;
import com.testing.system.dao.interfaces.*;
import com.testing.system.entities.QuestionOption;
import com.testing.system.entities.TestRecord;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

/**
 * {@link Config} implementation for fulfilling components context
 * with dao implementations
 *
 * @author yuri
 */
public class DaoConfig implements Config {
    private static final Logger log = Logger.getLogger(DaoConfig.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(Components components) {
        DataSource dataSource = DataSourceFactory.getDataSource(DataSourceFactory.MY_SQL);

        components
                .add(UserDao.class, new JdbcUserDao(dataSource))
                .add(CategoryDao.class, new JdbcCategoryDao(dataSource))
                .add(TestDao.class, new JdbcTestDao(dataSource))
                .add(QuestionDao.class, new JdbcQuestionDao(dataSource))
                .add(TestRecordDao.class, new JdbcTestRecordDao(dataSource))
                .add(QuestionOptionDao.class, new JdbcQuestionOptionDao(dataSource));

        log.info("Dao components added");
    }
}
