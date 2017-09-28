package com.testing.system.config;

import java.util.HashMap;
import java.util.Map;

/**
 *Serving as a components context
 *<p>
 *Used by WebAppContext as main container for application components
 *
 *@author yuri
 *
 */
public class Components {

	private Map<Class<?>, Object> components = new HashMap<>();

	/**
	 * Bind component instance to the corresponding {@link Class}
	 *
	 * @param clazz component {@link Class}
 	 * @param object component itself
	 * @return link to this object for convenient
	 * addition of components
	 */
	public <T> Components add(Class<T> clazz, Object object){
		components.put(clazz, object);
		return this;
	}


	/**
	 * Get component by {@link Class}
	 *
	 * @param clazz {@Class} with which component is bound
	 * @return component
	 */
	public <T> T get(Class<T> clazz){
		return clazz.cast(components.get(clazz));
	}
}
