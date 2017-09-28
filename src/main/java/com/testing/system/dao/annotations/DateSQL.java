package com.testing.system.dao.annotations;

import com.testing.system.dao.annotations.enums.DateType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateSQL {
	DateType value() default DateType.DATE_TIME;
}
