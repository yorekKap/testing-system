package com.testing.system.dao.builder;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Functional interface for referencing functions
 * that should parse {@link ResultSet} to a particular {@link T} type
 *
 * @author yuri
 *
 * @param <T> type to which {@link ResultSet} should be parsed
 */
@FunctionalInterface
public interface PrepareResultSetFunction<T> {

	T prepareResultSet(ResultSet rs) throws SQLException;

}
