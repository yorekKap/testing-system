package com.testing.system.dao.annotations;

import com.testing.system.dao.annotations.enums.DateType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

/**
 *Describes how {@code Date} should be parsed by
 * {@link com.testing.system.dao.parsers.ResultSetParser}
 *
 * @author yuri
 */
public @interface DateSQL {
	DateType value() default DateType.DATE_TIME;
}
