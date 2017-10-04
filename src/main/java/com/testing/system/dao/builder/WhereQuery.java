package com.testing.system.dao.builder;

import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Class that provide support of WHERE clause to it's
 * subclasses
 *
 *
 * @author yuri
 *
 * @param <T> subclass query type, needed for the convenient
 * 			  return to the right query builder class
 */
public abstract class WhereQuery<T>{

	protected Class<T> clazz;
	protected StringBuilder predicates;

	public WhereQuery(Class<T> clazz) {
		this.clazz = clazz;
		predicates = new StringBuilder();
	}

	private T getDowncastedThis(){
		return clazz.cast(this);
	}

	public WhereQuery<T> where(String columnName){
		predicates.append(" WHERE " + columnName + " ");
		return this;
	}

	public T isEquals(String str){
		predicates.append(" = \'" + str + '\'');
		return getDowncastedThis();
	}

	public T isEquals(Number value){
		predicates.append(" = " +  value.doubleValue());
		return getDowncastedThis();
	}

	public T isNotEquals(String str){
		predicates.append(" != \'" + str + '\'');
		return getDowncastedThis();
	}

	public T isNotEquals(Number value){
		predicates.append(" != " +  value.doubleValue());
		return getDowncastedThis();
	}

	public T isNull(){
		predicates.append(" IS NULL ");
		return getDowncastedThis();
	}

	public T isNotNull(){
		predicates.append(" IS NOT NULL");
		return getDowncastedThis();
	}

	public T less(Number value){
		predicates.append("<" + value.doubleValue());
		return getDowncastedThis();
	}

	public T lessOrEquals(Number value){
		predicates.append("<=" + value.doubleValue());
		return getDowncastedThis();
	}

	public T greater(Number value){
		predicates.append(">" + value.doubleValue());
		return getDowncastedThis();
	}

	public T greaterOrEquals(Number value){
		predicates.append("<=" + value.doubleValue());
		return getDowncastedThis();
	}

	public T beetween(Number min, Number max){
		predicates.append(" BETWEEN " + min + " AND " + max);
		return getDowncastedThis();
	}

	public T notBeetween(Number min, Number max){
		predicates.append(" NOT BETWEEN " + min + " AND " + max);
		return getDowncastedThis();
	}

	public T like(String pattern){
		predicates.append(" LIKE " + "'" +pattern + "'");
		return getDowncastedThis();
	}

	public T notLike(String pattern){
		predicates.append(" NOT LIKE " + pattern);
		return getDowncastedThis();
	}

	public T in(String... list){
		String values = Arrays.stream(list)
							  .map(s -> '\'' + s + '\'')
							  .collect(Collectors.joining(" ,"));
		predicates.append("IN (" + values + ")");
		return getDowncastedThis();
	}

	public T in(Object... list){
		String values = Arrays.stream(list)
				.map(x -> x.toString())
				.collect(Collectors.joining(" ,"));

		predicates.append("IN (" + values + ")");
		return getDowncastedThis();
	}

	public T notIn(String... list){
		String values = Arrays.stream(list)
							  .map(s -> '\'' + s + '\'')
							  .collect(Collectors.joining(" ,"));
		predicates.append("NOT IN (" + values + ")");
		return getDowncastedThis();
	}

	public T notIn(Object... list){
		String values = Arrays.stream(list)
				.map(s -> s.toString())
				.collect(Collectors.joining(" ,"));
		predicates.append("NOT IN (" + values + ")");
		return getDowncastedThis();
	}

	public T in(SelectQuery innerQuery){
		String innerSelect = innerQuery.toString().trim();
		innerSelect = innerSelect.substring(0, innerSelect.length() - 1);
		predicates.append("IN (" + innerSelect + ")");
		return getDowncastedThis();

	}

	public T notIn(SelectQuery innerQuery){
		String innerSelect = innerQuery.toString().trim();
		innerSelect = innerSelect.substring(0, innerSelect.length() - 1);
		predicates.append(" NOT IN (" + innerSelect + ")");
		return getDowncastedThis();

	}

	public WhereQuery<T> and(String columnName){
		predicates.append(" AND " + columnName);
		return this;
	}

	public WhereQuery<T> or(String columnName){
		predicates.append(" OR " + columnName);
		return this;
	}

	public String toString(){
		return predicates.toString();
	}
}
