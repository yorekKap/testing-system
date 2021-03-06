package com.testing.system.dao.builder;

import com.testing.system.exceptions.dao.MySQLDeleteException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Used for the {@code DELETE} query building
 *
 * @author yuri
 */
public class DeleteQuery extends WhereQuery<DeleteQuery> {
    private final static Logger log = Logger.getLogger(DeleteQuery.class);

    private Connection connection;
    private String tableName;

    public DeleteQuery(Connection connection, String tableName) {
        super(DeleteQuery.class);
        this.connection = connection;
        this.tableName = tableName;
    }

    public DeleteQuery from(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public int execute() {
        try (PreparedStatement statement = connection.prepareStatement(toString())) {
            log.info(statement.toString() + " query executing");

            int i = 1;
            for(Object obj : getPredicatesValues()){
                statement.setObject(i++, obj);
            }

            return statement.executeUpdate();
        } catch (SQLException e) {
            log.error("SQlException in DeleteQuery : ", e);

            throw new MySQLDeleteException(e.getMessage());
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

    public String toString() {
        StringBuilder sqlBuilder = new StringBuilder("DELETE FROM ").append(tableName).append("\n")
                .append(getPredicatesWithQuestionMarks()).append(";");
        return sqlBuilder.toString();

    }
}
