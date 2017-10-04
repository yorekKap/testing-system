package com.testing.system.web.parsers;

import com.testing.system.utils.ReflectionUtils;
import org.mockito.internal.matchers.Null;

import javax.json.*;
import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by yuri on 29.09.17.
 */
public class JsonParser {

    private static final String CONTENT_PARAMETER = "content";

    public static <T> T parse(Class<T> clazz, String jsonString) {
        JsonObject jsonObject = Json
                .createReader(new StringReader(jsonString))
                .readObject();

        return parseJsonObject(jsonObject, clazz);

    }

    private static <T> T parseJsonObject(JsonObject jsonObject, Class<T> clazz) {
        try {
            T result = clazz.newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                field.set(result, parseValue(field, jsonObject));
            }

            return result;

        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Object parseValue(Field field, JsonObject jsonObject) {
        Class clazz = field.getType();
        String fieldName = field.getName();

        try {
            if (clazz == String.class)
                return jsonObject.getString(fieldName);
            if (clazz == Integer.class)
                return new Integer(jsonObject.getInt(fieldName));
            if (clazz == Long.class)
                return new Long(jsonObject.getInt(fieldName));
            if (clazz == Boolean.class)
                return new Boolean(jsonObject.getBoolean(fieldName));
            if (clazz == Double.class)
                return jsonObject.getJsonNumber(fieldName).doubleValue();
            if (Collection.class.isAssignableFrom(clazz)) {
                return parseArray(jsonObject.getJsonArray(fieldName), field);
            } else {
                return parseJsonObject(jsonObject.getJsonObject(fieldName), clazz);

            }
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static Collection parseArray(JsonArray jsonArray, Field field) {
        Class collectionClass = field.getType();
        Collection result = null;

        if (collectionClass == List.class) {
            result = new ArrayList();
        } else if (collectionClass == Set.class) {
            result = new HashSet();
        } else {
            throw new RuntimeException("To parse JsonArray field type " +
                    "should be List or Set");
        }

        Class genericClass = ReflectionUtils.getGenericType(field);

        if (genericClass == String.class) {
            for (JsonString jsonString : jsonArray.getValuesAs(JsonString.class)) {
                result.add(jsonString.getString());
            }
        } else if (Number.class.isAssignableFrom(genericClass)) {
            for (JsonNumber jsonNumber : jsonArray.getValuesAs(JsonNumber.class)) {
                if (genericClass == Integer.class) {
                    result.add(jsonNumber.intValue());
                } else if (genericClass == Long.class) {
                    result.add(new Long(jsonNumber.intValue()));
                } else if (genericClass == Double.class) {
                    result.add(jsonNumber.doubleValue());
                }
            }
        } else {
            for (JsonObject jsonObject : jsonArray.getValuesAs(JsonObject.class)) {
                result.add(parseJsonObject(jsonObject, genericClass));
            }
        }


        return result;
    }

}
