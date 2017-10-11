package com.testing.system.dao.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

/**
 *Tells {@link com.testing.system.dao.parsers.ResultSetParser} to parse field as
 * column.
 *
 * @author yuri
 */
public @interface Column {
	String value();
}
