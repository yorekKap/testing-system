package com.testing.system.dao.annotations;

import java.lang.annotation.*;


/**
 * Describe corresponding table info
 *
 * @author yuri
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
	String value();
}
