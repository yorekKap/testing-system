package com.testing.system.dao.parsers;

import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by yuri on 21.09.17.
 */
public class ResultSetMocker {

    private Map<String, List<String>> tableColumnNames = new LinkedHashMap<>();
    private List<List<?>> rows = new ArrayList<>();

    public static ResultSetMocker mocker() {
        return new ResultSetMocker();
    }

    public ResultSetMocker columns(String tableName, String... columnNames) {
        tableColumnNames.put(tableName, Arrays.asList(columnNames));
        return this;
    }

    public ResultSetMocker row(Object... values) {
        rows.add(Arrays.asList(values));
        return this;
    }

    public ResultSetMocker rows(RowsInitializer rowsInitializer) {
        rows.addAll(rowsInitializer.getRows());
        return this;
    }

    public ResultSet mockResultSet() {
        ResultSet rs = mock(ResultSet.class);
        ResultSetMetaData rsMetaData = mock(ResultSetMetaData.class);

        try {
            when(rs.getMetaData()).thenReturn(rsMetaData);

            int counter = 0;

            for (Map.Entry<String, List<String>> entry : tableColumnNames.entrySet()) {
                for (String column : entry.getValue()) {
                    counter++;
                    when(rsMetaData.getTableName(counter)).thenReturn(entry.getKey());
                    when(rsMetaData.getColumnName(counter)).thenReturn(column);
                }
            }

            when(rsMetaData.getColumnCount()).thenReturn(counter);

            OngoingStubbing rsNextStub = when(rs.next());
            for (int i = 1; i <= counter; i++) {
                rsNextStub = rsNextStub.thenReturn(true);
            }
            rsNextStub.thenReturn(false);

            final int finalCounter = counter;
            Mockito.doAnswer(x -> {
                OngoingStubbing rsNext = when(rs.next());
                for (int i = 1; i <= finalCounter; i++) {
                    rsNext = rsNext.thenReturn(true);
                }
                rsNext.thenReturn(false);
                return x;

            }).when(rs).beforeFirst();


            System.out.println(rows);
            Object tempObj = null;
            for (int i = 1; i <= counter; i++) {
                OngoingStubbing rsGetObjectStub = when(rs.getObject(i));

                for (int j = 0; j < rows.size(); j++) {
                    System.out.println(i + " : " + rows.get(j).get(i - 1));
                    rsGetObjectStub = rsGetObjectStub.thenReturn(rows.get(j).get(i - 1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return rs;
    }

}
