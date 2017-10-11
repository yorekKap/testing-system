package com.testing.system.dao.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *Tells {@link com.testing.system.dao.parsers.ResultSetParser} to parse field as enum
 *
 * @author yuri
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ParseEnum {
}



