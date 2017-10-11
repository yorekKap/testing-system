package com.testing.system.dao.implementation.jdbc;

import com.testing.system.dao.interfaces.UserDao;
import com.testing.system.entities.User;

import javax.sql.DataSource;

public class JdbcUserDao extends AbstractJdbcDaoAdapter<User> implements UserDao {

    public JdbcUserDao(DataSource dataSource) {
        super(dataSource, User.class);
    }

    @Override
    public User findByUsername(String username) {
        return builder.select("*")
                .where("username").isEquals(username)
                .executeForSingle(User.class);
    }
}
