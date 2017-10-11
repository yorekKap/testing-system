package com.testing.system.dao.implementation.jdbc;

import com.testing.system.dao.builder.SelectQuery;
import com.testing.system.entities.Identified;

import javax.sql.DataSource;

/**
 * Adapter class for ruling out need for the implementing
 * of optional methods of {@link AbstractJdbcDao}
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
