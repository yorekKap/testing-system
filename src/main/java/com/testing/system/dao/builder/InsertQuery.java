package com.testing.system.dao.builder;

import com.testing.system.exceptions.dao.MySQLInsertException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Used for the {@code INSERT} query building
 *
 * @author yuri
 */
public class InsertQuery {
    private final static Logger log = Logger.getLogger(InsertQuery.class);

    private Connection connection;
    private String tableName;
    private Map<String, Object> values;

    public InsertQuery(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
        this.values = new HashMap<>();
    }

    public InsertQuery into(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public InsertQuery setValue(String column, Object value) {
        values.put(column, value);
        return this;
    }

    public InsertQuery setValues(Map<String, Object> values) {
        this.values = values;
        return this;
    }

    public int execute() {
        try (PreparedStatement statement = connection.prepareStatement(toString())) {
            int i = 1;
            for (Object o : values.values()) {
                statement.setObject(i++, o);
            }

            log.info(statement.toString() + " statement executing");
            return statement.executeUpdate();

        } catch (SQLException e) {
            log.error("SQLException in InsertQuery :", e);
            throw new MySQLInsertException(e.getMessage());
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
        //question marks for the PreparedStatement
        String questionMarks = IntStream.range(0, values.size())
                .mapToObj(i -> "?")
                .collect(Collectors.joining(", ", "(", ") "));

        //columns that are to be inserted
        String columns = values.keySet()
                .stream()
                .map(x -> x.toString())
                .collect(Collectors.joining(", ", "(", ")"));


        //INSERT INTO tableName(column1, column2) VALUES(?, ?, ...);
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ")
                .append(tableName + columns)
                .append(" VALUES " + questionMarks + ";");

        return sqlBuilder.toString();
    }


}
