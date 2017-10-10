package com.testing.system.dao.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	private StringBuilder predicatesWithQuestionMarks;
	private List<Object> predicatesValues;


	public WhereQuery(Class<T> clazz) {
		this.clazz = clazz;
		predicatesWithQuestionMarks = new StringBuilder();
		predicatesValues = new ArrayList<>();
	}

	private T getDowncastedThis(){
		return clazz.cast(this);
	}

	public WhereQuery<T> where(String columnName){
		predicatesWithQuestionMarks.append(" WHERE " + columnName + " ");
		return this;
	}

	public T isEqualVariable(String variable){
		predicatesWithQuestionMarks.append(" = " + variable);
		return getDowncastedThis();
	}

	public T isEquals(Object obj){
		predicatesWithQuestionMarks.append(" = ? ");
		predicatesValues.add(obj);

		return getDowncastedThis();
	}

	public T isNotEquals(Object obj){
		predicatesWithQuestionMarks.append(" != ?");
		predicatesValues.add(obj);

		return getDowncastedThis();
	}


	public T isNull(){
		predicatesWithQuestionMarks.append(" IS NULL ");
		return getDowncastedThis();
	}

	public T isNotNull(){
		predicatesWithQuestionMarks.append(" IS NOT NULL");
		return getDowncastedThis();
	}

	public T less(Number value){
		predicatesWithQuestionMarks.append(" < ?" );
		predicatesValues.add(value);

		return getDowncastedThis();
	}

	public T lessOrEquals(Number value){
		predicatesWithQuestionMarks.append(" <= ? ");
		predicatesValues.add(value);

		return getDowncastedThis();
	}

	public T greater(Number value){
		predicatesWithQuestionMarks.append(" > ? ");
		predicatesValues.add(value);

		return getDowncastedThis();
	}

	public T greaterOrEquals(Number value){
		predicatesWithQuestionMarks.append(" <= ? ");
		predicatesValues.add(value);

		return getDowncastedThis();
	}

	public T beetween(Number min, Number max){
		predicatesWithQuestionMarks.append(" BETWEEN ? AND ? ");
		predicatesValues.add(min);
		predicatesValues.add(max);

		return getDowncastedThis();
	}

	public T notBeetween(Number min, Number max){
		predicatesWithQuestionMarks.append(" NOT BETWEEN ? AND ?");
		predicatesValues.add(min);
		predicatesValues.add(max);

		return getDowncastedThis();
	}

	public T like(String pattern){
		predicatesWithQuestionMarks.append(" LIKE " + "'" +pattern + "'");
		return getDowncastedThis();
	}

	public T notLike(String pattern){
		predicatesWithQuestionMarks.append(" NOT LIKE " +  "'" +pattern + "'");
		return getDowncastedThis();
	}

	public T in(Object... list){
		String values = Arrays.stream(list)
				.map(s -> "?")
				.collect(Collectors.joining(", ","(",")"));

		predicatesWithQuestionMarks.append(" IN " + values);
		predicatesValues.add(Arrays.asList(list));

		return getDowncastedThis();

	}

	public T notIn(Object... list){
		String values = Arrays.stream(list)
				.map(s -> "?")
				.collect(Collectors.joining(", ","(",")"));

		predicatesWithQuestionMarks.append(" IN " + values);
		predicatesValues.add(Arrays.asList(list));

		return getDowncastedThis();
	}

	public T in(SelectQuery innerQuery){
		String innerSelect = innerQuery.toString().trim();
		innerSelect = innerSelect.substring(0, innerSelect.length() - 1);

		predicatesWithQuestionMarks.append("IN (" + innerSelect + ")");

		predicatesValues.add(innerQuery.getPredicatesValues());
		return getDowncastedThis();

	}

	public T notIn(SelectQuery innerQuery){
		String innerSelect = innerQuery.toString().trim();
		innerSelect = innerSelect.substring(0, innerSelect.length() - 1);

		predicatesWithQuestionMarks.append("NOT IN (" + innerSelect + ")");

		predicatesValues.add(innerQuery.getPredicatesValues());
		return getDowncastedThis();
	}

	public WhereQuery<T> and(String columnName){
		predicatesWithQuestionMarks.append(" AND " + columnName);
		return this;
	}

	public WhereQuery<T> or(String columnName){
		predicatesWithQuestionMarks.append(" OR " + columnName);
		return this;
	}

	public StringBuilder getPredicatesWithQuestionMarks() {
		return predicatesWithQuestionMarks;
	}

	public List<Object> getPredicatesValues() {
		return predicatesValues;
	}

	public String toString(){
		return predicatesWithQuestionMarks.toString();
	}
}
