package com.testing.system.dao.parsers;

import com.testing.system.dao.annotations.*;
import com.testing.system.exceptions.dao.MySQLException;
import com.testing.system.entities.Identified;
import com.testing.system.utils.ReflectionUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yuri on 23.09.17.
 */
public class ResultSetParser {
    private static final Logger log = Logger.getLogger(OldResultSetParser.class);

    private ResultSet rs;
    private Map<String, Integer> columns = new HashMap<>();
    private Map<Class, Map<Long, Object>> oneSides = new HashMap<>();

    public ResultSetParser(ResultSet rs) {
        this.rs = rs;
        getColumnsFromResultSet();
    }

    /**
     * Fetch {@link ResultSetMetaData} from {@link ResultSet}
     * to initialize {@code #columns} map with
     * corresponding column_names
     */
    private void getColumnsFromResultSet() {
        try {

            ResultSetMetaData md = rs.getMetaData();
            int count = md.getColumnCount();

            String columnName = null;
            for (int i = 1; i <= count; i++) {
                columnName = md.getTableName(i) + "." + md.getColumnName(i);
                columns.put(columnName, i);
            }

            log.info("Columns map is initialized");

        } catch (SQLException e) {
            log.error("SQLException in OldResultSetParser : ", e);
            e.printStackTrace();
        }
    }

    public <T extends Identified<Long>> List<T> parse(Class<T> clazz) {
        try {
            return parse(clazz, null, null);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private <T> List<T> parse(Class<T> clazz, String fkColumn, Long fkValue) {
        List<Object> result = new ArrayList<>();

        ColumnNamesMapper columnNamesMapper = new ColumnNamesMapper(clazz, columns);
        try {
            Identified<Long> newObj = null;
            if (fkColumn != null) {
                newObj = parseRow(clazz, columnNamesMapper, null);
                if (newObj == null) {
                    return new ArrayList<>();
                }
                result.add(newObj);
            }

            while (rs.next()) {
                if (fkColumn != null) {
                    Long actualFK = rs.getLong(columnNamesMapper.getColumnIndex(fkColumn));
                    if (!actualFK.equals(fkValue)) {
                        rs.previous();
                        break;
                    }
                }

                newObj = parseRow(clazz, columnNamesMapper, null);
                if (newObj != null) {
                    result.add(newObj);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return result.stream()
                .map(clazz::cast)
                .collect(Collectors.toList());
    }

    private Identified<Long> parseRow(Class<?> clazz, ColumnNamesMapper columnNamesMapper, String ignoredOneToMany) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field idField = getIdField(clazz, columnNamesMapper);
        idField.setAccessible(true);

        Integer columnIndex = columnNamesMapper
                .getColumnIndex(idField.getAnnotation(Id.class).name());

        if(columnIndex == null){
            return null;
        }

        //null id checking
        Integer intId = (Integer) rs.getObject(columnIndex);
        if (intId == null) {
            return null;
        }

        Long id = new Long(intId);
        Identified newObject = Identified.class.cast(clazz.newInstance());
        idField.set(newObject, id);

        for (Field field : clazz.getDeclaredFields()) {
            Object value = parseColumn(field, columnNamesMapper, newObject, ignoredOneToMany);
            field.setAccessible(true);

            if (value != null) {
                field.set(newObject, value);
            }
        }

        return newObject;
    }

    private Field getIdField(Class clazz, ColumnNamesMapper columnNamesMapper) throws SQLException {
        Field idField = ReflectionUtils.findFieldAnnotatedWith(clazz, Id.class);

        if (idField == null) {
            throw new MySQLException("Parsed class should have field annotated with Id");
        }

        return idField;
    }


    private Object parseColumn(Field field, ColumnNamesMapper columnNamesMapper,
                               Identified actualObject, String ignoredOneToMany) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object value = null;

        Column column;
        if ((column = field.getAnnotation(Column.class)) != null) {
            value = rs.getObject(columnNamesMapper.getColumnIndex(column.value()));

            if (field.isAnnotationPresent(ParseEnum.class)) {
                value = parseEnum(field, value);
            } else if (field.getType().equals(Long.class) && value != null) {
                value = new Long((Integer) value);
            } else if (field.getType().equals(Double.class) && value != null) {
                value = ((BigDecimal) value).doubleValue();
            }

        } else if (field.isAnnotationPresent(OneToMany.class)) {
            value = parseOneToMany(field, actualObject, ignoredOneToMany);
        } else if (field.isAnnotationPresent(ManyToOne.class)) {
            value = parseManyToOne(field, columnNamesMapper, actualObject);
        } else if (field.isAnnotationPresent(ManyToMany.class)){
            value = parseManyToMany(field, actualObject);
        }

        return value;
    }

    private Object parseEnum(Field field, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> enumClass = field.getType();
        Method valueOf = enumClass.getDeclaredMethod("valueOf", String.class);

        return valueOf.invoke(null, value);
    }

    private Object parseManyToMany(Field field, Identified<Long> actualObject) {
        ManyToMany manyToMany = field.getAnnotation(ManyToMany.class);

        String fullColumnName = manyToMany.table() + "." + manyToMany.mappedBy();
        Collection<?> value = parse(ReflectionUtils.getGenericType(field),
                fullColumnName, actualObject.getId());

        if (field.getType() == Set.class) {
            value = new HashSet(value);
        }

        return value;
    }

    private Object parseOneToMany(Field field, Identified<Long> actualObject, String ignoredOneToMany) {
        OneToMany oneToMany = field.getAnnotation(OneToMany.class);

        if (oneToMany.mappedBy().equals(ignoredOneToMany)) {
            if (field.getType() == Set.class) {
                return new HashSet<>();
            }
            return new ArrayList<>();
        }

        HashMap<Long, Object> classValues = new HashMap<>();
        classValues.put(actualObject.getId(), actualObject);

        oneSides.put(actualObject.getClass(), classValues);

        Collection<?> value = parse(ReflectionUtils.getGenericType(field),
                oneToMany.mappedBy(), actualObject.getId());

        if (field.getType() == Set.class) {
            value = new HashSet(value);
        }

        return value;
    }

    private Object parseManyToOne(Field field, ColumnNamesMapper columnNamesMapper,
                                  Object actualObject) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
        Object value = null;

        String mappedBy = manyToOne.mappedBy();
        Long fkValue = rs.getLong(columnNamesMapper.getColumnIndex(mappedBy));
        Class clazz = field.getType();

        Map<Long, Object> oneSideValues = oneSides.get(clazz);

        if (oneSideValues == null) {
            oneSideValues = new HashMap<>();
            oneSides.put(clazz, oneSideValues);
        }

        value = oneSideValues.get(fkValue);
        if (value == null) {
            value = parseRow(clazz, new ColumnNamesMapper(clazz, columns), mappedBy);
            oneSideValues.put(fkValue, value);
        }

        Field oneToManyField = ReflectionUtils.findField(clazz,
                x -> x.getAnnotation(OneToMany.class).mappedBy().equals(mappedBy));

        if (oneToManyField != null) {
            oneToManyField.setAccessible(true);
            ((Collection) oneToManyField.get(value)).add(actualObject);
        }

        return value;
    }
}

