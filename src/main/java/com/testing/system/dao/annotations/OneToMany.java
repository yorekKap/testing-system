package com.testing.system.dao.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *Tells {@link com.testing.system.dao.parsers.ResultSetParser} to parse field as
 * {@code One} side in {@code OneToMany} relation
 *
 * @author yuri
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OneToMany {
    String mappedBy();
}
