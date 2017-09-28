package com.testing.system.config;

/**
 *The interface that defines {@link Components}
 *initialization with layer specific components
 *
 * @author yuri
 *
 */

public interface Config {

	/**
	 * Method for fulfilling {@link Components} object
	 * with layer specific components
	 *
	 * @param components link to global {@link Components} object
	 */
	void init(Components components);
}
