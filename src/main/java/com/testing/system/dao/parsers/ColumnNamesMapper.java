package com.testing.system.dao.parsers;

import com.testing.system.dao.annotations.Table;
import com.testing.system.exceptions.dao.MySQLException;

import java.util.Map;
import java.util.Optional;


/**
 * Class that manages column names mapping and resolving for
 * the need of {@link ResultSetParser}
 *
 * @author yuri
 */
public class ColumnNamesMapper {

    private static final String NO_TABLE_ANNOTATION_MESSAGE =
            "Class for parsing should be annotated with Table : class %s";

    private String tableName;
    private Map<String, Integer> columns;

    public ColumnNamesMapper(Class<?> clazz, Map<String, Integer> columns) {
        this.tableName = Optional.ofNullable(clazz.getAnnotation(Table.class))
                .map(x -> x.value())
                .orElseThrow(() -> new MySQLException(
                        String.format(NO_TABLE_ANNOTATION_MESSAGE, clazz.getName())));

        this.columns = columns;
    }

    public Integer getColumnIndex(String columnName) {
        String fullColumnName = null;
        if (columnName.contains(".")) {
            fullColumnName = columnName;
        } else {
            fullColumnName = tableName + "." + columnName;
        }

        Integer index = columns.get(fullColumnName);

        return index;
    }
}
