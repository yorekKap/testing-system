package com.testing.system.dao.implementation.jdbc;

import com.testing.system.dao.builder.SelectQuery;
import com.testing.system.entities.Identified;

import javax.sql.DataSource;

/**
 * Created by yuri on 27.09.17.
 */
public abstract class AbstractJdbcDaoAdapter<T extends Identified<Long>>
        extends AbstractJdbcDao<T> {

    public AbstractJdbcDaoAdapter(DataSource dataSource, Class<T> clazz) {
        super(dataSource, clazz);
    }

    @Override
    public void provideInnerJoin(SelectQuery query) {}

    @Override
    public void provideOrdering(SelectQuery query) {}
}
