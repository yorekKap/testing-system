package com.testing.system.web.dispatcher;

import java.util.HashMap;
import java.util.Map;

/**
 * Used for mapping URL to {@link Controller}
 *
 * @author yuri
 *
 */
public class ControllersMapper {
	Map<String, Controller> controllers = new HashMap<>();

	private ControllersMapper(Map<String, Controller> controllers){
		this.controllers = controllers;
	}


	/**
	 * Used for getting right controller based on the given URL
	 *
	 * @param url
	 * @return matched {@link Controller}
	 */
	public Controller get(String url){
		return controllers.get(url);
	}

	public static ControllersMappperBuilder createBuilder(){
		return new ControllersMappperBuilder();
	}


	/**
	 * Builder for convenient initialization of {@link ControllersMapper}
	 *
	 */
	public static class ControllersMappperBuilder{
		Map<String, Controller> controllers = new HashMap<>();

		private ControllersMappperBuilder() {
		}

		/**
		 * Binds {@link Controller} to URL
		 *
		 * @param url
		 * @param controller
		 * @return link to this object for convenient {@code ControllersMapper} creation
		 */
		public ControllersMappperBuilder add(String url, Controller controller){
			controllers.put(url, controller);
			return this;
		}


		/**
		 * @return initialized {@link ControllersMapper}
		 */
		public ControllersMapper build(){
			return new ControllersMapper(controllers);
		}



	}

}
