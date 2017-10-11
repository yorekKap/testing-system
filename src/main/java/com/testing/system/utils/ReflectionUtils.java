package com.testing.system.utils;

import com.testing.system.exceptions.dao.MySQLException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Predicate;

/**
 * Provide useful method for working with reflection api
 */
public class ReflectionUtils {

    public static Field findFieldAnnotatedWith(Class<?> clazz,
                                               Class<? extends Annotation> annotation) {
        return findField(clazz, x -> x.isAnnotationPresent(annotation));
    }

    public static Field findField(Class<?> clazz, Predicate<Field> predicate) {
        for (Field f : clazz.getDeclaredFields()) {
            try {
                if (predicate.test(f)) {
                    return f;
                }
            }catch(NullPointerException e){}
        }

        return null;
    }

    public static <T> T findAnnotationInClassFields(Class clazz,
                                                    Class<T> annotationClass){
        Object annotation = null;
        for(Field field : clazz.getDeclaredFields()){
            if((annotation = field.getAnnotation((Class)annotationClass)) != null){
                return annotationClass.cast(annotation);
            }
        }

        return null;
    }

    public static Class getGenericType(Field field) {
        Type genericFieldType = field.getGenericType();

        if (genericFieldType instanceof ParameterizedType) {
            ParameterizedType aType = (ParameterizedType) genericFieldType;
            return (Class) aType.getActualTypeArguments()[0];
        } else {
            throw new MySQLException("OneToMany should be parametrized List or Set");
        }
    }

}
