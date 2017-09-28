package com.testing.system.dao.parsers;

import com.testing.system.dao.annotations.Column;
import com.testing.system.dao.annotations.DateSQL;
import com.testing.system.dao.annotations.Id;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Based on the annotations {@link Column}, {@link Table},
 * {@link DateSQL}, {@link Id}, {@link ParseEnum} parse object to map of
 * columns / values
 *
 * @author yuri
 *
 */
/**
 * @author yuri
 *
 */
public class ObjectToColumnValueMapParser {
	private static final Logger log = Logger.getLogger(ObjectToColumnValueMapParser.class);

	/**
	 * Parse object to map
	 *
	 * @param object to parse
	 * @return map of columns / values
	 */
	public static <T> Map<String, Object> parse(T object){
		Map<String, Object> result = new HashMap<>();
		Field[] fields = object.getClass().getDeclaredFields();

		//iterating through each field annotated with Column annotation
		//and parse it to the map entry
		//ignore fields annotated with Id
		Arrays.stream(fields)
							 .filter(f -> f.getAnnotation(Column.class) != null &&
							 			  f.getAnnotation(Id.class) == null)
							 .peek(f -> f.setAccessible(true))
							 .forEach(f -> parseObjectField(f, object, result));

		log.info(object.getClass() + " is parsed to map");
		return result;
	}


	/**
	 * Parse field to map entry
	 *
	 * @param field to parse
	 * @param obj - the {@link Object} that contains field
	 * @param result - {@link Map} that resulted entry should be placed in
	 */
	private static void parseObjectField(Field field, Object obj, Map<String, Object> result){
		String columnName = field.getAnnotation(Column.class).value();
		try {
			DateSQL date;
			Object value = null;

			//if field is annotated with ParseEnum annotation
			//it parsed from enumeration to String
			if(Enum.class.isAssignableFrom(field.getType())){
				value = parseEnum(field, obj);
			}

			//if field is annotated with DateSQL annotation
			//it parsed to the SQL DATE or TIME or DATETIME type
			else if((date = field.getAnnotation(DateSQL.class)) != null){
				java.util.Date fieldDate = (java.util.Date)field.get(obj);
				value = date.value().getDateInRightFormat((fieldDate));
			}
			else{
				value = field.get(obj);
			}

			result.put(columnName, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error("Exception in ObjectToColumnValueMapParser : ", e);
			e.printStackTrace();
		}
	}

	/**
	 * Parse enum field
	 *
	 * @param field {@link Field} to parse
	 * @param obj {@link Object} to parse
	 * @return {@link Object}
	 */
	private static Object parseEnum(Field field, Object obj){
		try {
			Enum<?> enumObject =  (Enum<?>)field.get(obj);
			return enumObject.name();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			log.error("Exception in ObjectToColumnValueMapParser : ", e);
			throw new RuntimeException();
		}

	}
}
