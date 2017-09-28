package com.testing.system.dao.builder;

import com.testing.system.dao.parsers.ResultSetParser;
import com.testing.system.exceptions.dao.MySQLException;
import com.testing.system.entities.Identified;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Used for the {@code SELECT} query building
 *
 * @author yuri
 */
public final class SelectQuery extends WhereQuery<SelectQuery> {
    private final static Logger log = Logger.getLogger(SelectQuery.class);

    private Connection connection;
    private String tableName;
    private String orderByColumn;
    private String[] columnNames;
    private StringBuilder join = new StringBuilder();
    private int limit[];

    protected SelectQuery(Connection connection, String[] columnNames, String tableName) {
        super(SelectQuery.class);
        this.connection = connection;
        this.columnNames = columnNames;
        this.tableName = tableName;
    }

    public SelectQuery from(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public SelectQuery join(String tableName) {
        join.append(" JOIN " + tableName);
        return this;
    }

    public SelectQuery leftJoin(String tableName) {
        join.append(" LEFT JOIN " + tableName);
        return this;
    }

    public SelectQuery rightJoin(String tableName) {
        join.append(" RIGHT JOIN " + tableName);
        return this;
    }

    public SelectQuery on(String column1, String column2) {
        join.append(" ON " + column1 + "=" + column2);
        return this;
    }

    public SelectQuery orderBy(String orderByColumn, String orderMode) {
        this.orderByColumn = orderByColumn + " " + orderMode;
        return this;
    }

    public SelectQuery limit(int numOfRows) {
        this.limit = new int[1];
        this.limit[0] = numOfRows;
        return this;
    }

    public SelectQuery limit(int offset, int numOfRows) {
        this.limit = new int[2];
        this.limit[0] = offset;
        this.limit[1] = numOfRows;
        return this;
    }

    public <T> List<T> execute(PrepareResultSetFunction<T> prepareResultSetFunction) {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = execute(statement);
            List<T> result = new ArrayList<>();
            while (rs.next()) {
                result.add(prepareResultSetFunction.prepareResultSet(rs));
            }

            return result;
        } catch (SQLException e) {
            throw new MySQLException(e.getMessage());
        } finally {
            try {
                if (connection.getAutoCommit()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T executeForSingle(PrepareResultSetFunction<T> prepareResultSetFunction) {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = execute(statement);
            if (rs.next()) {
                return prepareResultSetFunction.prepareResultSet(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            log.error("SQLException in SelectQuery : ", e);
            throw new MySQLException(e.getMessage());
        } finally {
            try {
                if (connection.getAutoCommit()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public <T extends Identified<Long>> List<T> execute(Class<T> clazz) {
        try (Statement statement = connection.createStatement()) {
            return new ResultSetParser(execute(statement)).parse(clazz);
        } catch (SQLException e) {
            log.error("SQLException in SelectQuery : ", e);
            throw new MySQLException(e.getMessage());
        } finally {
            try {
                if (connection.getAutoCommit()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public <T extends Identified<Long>> T executeForSingle(Class<T> clazz) {
        List<T> results = execute(clazz);
        if (results.isEmpty()) {
            return null;
        }

        return results.get(0);
    }


    private ResultSet execute(Statement statement) {
        try {
            log.info(toString() + " Select query executing");
            System.out.println(toString());
            return statement.executeQuery(toString());

        } catch (SQLException e) {
            log.error("SQLException in SelectQuery : ", e);
            throw new MySQLException(e.getMessage());
        }
    }


    public String toString() {
        StringBuilder sqlBuilder = new StringBuilder("SELECT ")
                .append(String.join(", ", columnNames))
                .append(" FROM ")
                .append(tableName);

        if (join != null) {
            sqlBuilder.append(join);
        }

        if (predicates.length() != 0) {
            sqlBuilder.append(predicates);

        }

        if (orderByColumn != null) {
            sqlBuilder.append(" ORDER BY ")
                    .append(orderByColumn);
        }

        if (limit != null) {
            sqlBuilder.append(" LIMIT ")
                    .append(Arrays.stream(limit)
                            .mapToObj(i -> String.valueOf(i))
                            .collect(Collectors.joining(", ")));
        }

        sqlBuilder.append(";");
        return sqlBuilder.toString();
    }
}
