package com.testing.system.dao.builder;

import com.testing.system.exceptions.dao.MySQLInsertException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private List<Map<String, Object>> values;
    private int currentMap = -1;

    public InsertQuery(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
        this.values = new ArrayList<>();
    }

    public InsertQuery into(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public InsertQuery newValuesList(){
        currentMap++;
        values.add(new HashMap<>());
        return this;
    }

    public InsertQuery addValue(String column, Object value) {
        Map<String, Object> map = values.get(currentMap);
        if(map == null){
            throw new MySQLInsertException("You should start new values " +
                    "list before setting values");
        }
        values.get(currentMap).put(column, value);
        return this;
    }


    public InsertQuery addValues(Map<String, Object> values) {
        this.values.add(values);
        return this;
    }

    public int execute() {
        if(values.isEmpty()){
            throw new MySQLInsertException("No values set");


        }
        try (PreparedStatement statement = connection.prepareStatement(toString())) {
            int i = 1;
            for(Map<String, Object> map : values)
            for (Object o : map.values()) {
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
        String questionMarks = values.stream()
                .map(map -> map.keySet()
                        .stream()
                        .map(x -> "?")
                        .collect(Collectors.joining(", ", "(", ")")))
                .collect(Collectors.joining(", "));


        //columns that are to be inserted
        String columns = values.get(0).keySet()
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
