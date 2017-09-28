package com.testing.system.dao.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yuri on 21.09.17.
 */
public class RowsInitializer {
    private List<Object> parent = new ArrayList<>();

    private List<RowsInitializer> childs = new ArrayList<>();

    public static RowsInitializer parent(Object... objects){
        return new RowsInitializer(objects);
    }

    private RowsInitializer(Object... objects){
        this.parent = Arrays.asList(objects);
    }

    public RowsInitializer child(Object... objects){
        childs.add(new RowsInitializer(objects));
        return this;
    }

    public RowsInitializer child(RowsInitializer initializer){
        childs.add(initializer);
        return this;
    }

    public List<List<Object>> getRows(){
        List<List<Object>> rows = new ArrayList<>();

        if (childs.isEmpty()){
            rows.add(parent);
            return rows;
        }

        for(RowsInitializer child : childs){
            for(List<Object> row : child.getRows()){
                List<Object> newList = new ArrayList<>();
                newList.addAll(parent);
                newList.addAll(row);
                rows.add(newList);
            }
        }

        return rows;
    }

}
