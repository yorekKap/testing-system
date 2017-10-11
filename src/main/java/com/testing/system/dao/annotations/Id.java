package com.testing.system.dao.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

/**
 *Tells {@link com.testing.system.dao.parsers.ResultSetParser} to parse field as
 * primary key of the table
 *
 * @author yuri
 */
public @interface Id {
    String name() default "id";
}
