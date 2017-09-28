package com.testing.system.web.parsers;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yuri on 27.09.17.
 */
public class RequestContentParser {

    public static <T> T parse(HttpServletRequest request, Class<T> clazz) {
        try {
            T instance = clazz.newInstance();

            String strValue = null;
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                strValue = request.getParameter(field.getName());
                if (strValue == null){
                    continue;
                }

                if (Enum.class.isAssignableFrom(field.getType())) {
                    Class<?> enumClass = field.getType();
                    Method valueOf = enumClass.getDeclaredMethod("valueOf", String.class);
                    field.set(instance, valueOf.invoke(null, strValue));

                } else {
                    Object value = convertValue(field.getType(), strValue);

                    System.out.println(strValue);
                    System.out.println(value);
                    if(value != null) {
                        field.set(instance, value);
                    }
                }
            }
            return instance;

        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException |InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Object convertValue(Class<?> clazz, String value){
        if (clazz == String.class)
            return value;
        if(clazz == Integer.class)
            return Integer.valueOf(value);
        if (clazz == Long.class)
            return Long.valueOf(value);
        if(clazz == Boolean.class) {
            return Boolean.valueOf(value);
        }
        if(clazz == Double.class){
            return Double.parseDouble(value);
        }

        return null;
    }
}
