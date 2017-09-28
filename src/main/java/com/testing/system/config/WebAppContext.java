package com.testing.system.config;

import org.apache.log4j.Logger;

import java.util.Arrays;


/**
 * Application context for global components managing
 *
 * @author yuri
 *
 */
public class WebAppContext {
	private static final Logger log = Logger.getLogger(WebAppContext.class);

	private static Components components;

	//static context initialization
	static{
		components = new Components();
		init();


	}

	/**
	 * Iterates through all {@link Config} instances
	 * fetched from {@link #getConfigs()} and initialize {@link #components}
	 * by means of {@link Config#init(Components)} method
	 */
	private static void	 init(){
		Arrays.stream( getConfigs() ).forEach(c -> c.init(components));
		log.info("Components has been initialized");
	}


	/**
	 * @return array of {@link Config} instances
	 */
	private static Config[] getConfigs(){
		return new Config[] {
					new DaoConfig(),
					new ServiceConfig(),
					new RootConfig(),
					new WebConfig(),
					new SecurityConfig()
			};
	}


	/**
	 * Allow you to get instance of particular class
	 * that was bound by {@link Config} objects
	 *
	 * @param clazz {@link Class} which instance you want to get
	 * @return component in context
	 */
	public static <T> T get(Class<T> clazz){
		T comp = components.get(clazz);
		if(comp == null){
			log.error("No binding for the class:" + clazz.getName());
			throw new IllegalArgumentException("No binding for the class:" + clazz.getName());
		}

		return components.get(clazz);
	}

}
