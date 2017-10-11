package com.testing.system.web.dispatcher;

import com.testing.system.config.WebAppContext;
import com.testing.system.web.resolvers.ViewResolver;
import com.testing.system.web.validators.ValidatorFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Is a base class of every controller
 * use {@link ViewResolver} for providing right JSP view
 *
 * @author yuri
 *
 */
public abstract class Controller {

	public static final String GET = "GET";
	public static final String POST = "POST";

	private HttpServletRequest request;
	private HttpServletResponse response;

	/**
	 * Executes {@code #get(HttpServletRequest, HttpServletResponse)}
	 * or {@code #post(HttpServletRequest, HttpServletResponse)} methods
	 * based on the method of coming {@link HttpServletRequest}
	 * <p>
	 * Resolve right JSP view name as a result
	 *
	 * @param request {@link HttpServletRequest}
	 * @param response - JSP view name
	 * @return
	 */
	public final String execute(HttpServletRequest request, HttpServletResponse response) {
		 	this.request = request;
		 	this.response = response;

			String method = request.getMethod();
		 	String view;
		 	if(method.equals(GET)){
		 		 view =  get(new RequestService(request));
		 	}
		 	else{
		 		view = post(new RequestService(request));
		 	}

		 	return WebAppContext.get(ViewResolver.class).resolve(view);
	    }

	/**
	 * Method to be called if request HTTP method is GET
	 * <p>
	 * Is not abstract to give the one who will implement it
	 * freedom to choose which HTTP methods should his controller support
	 *
	 * @param requestService
	 * @return view name that will be resolved to the full JSP path
	 */
	public String get(RequestService requestService) {
		return null;
	}

	/**
	 * Method to be called if request HTTP method is POST
	 * <p>
	 * Is not abstract to give the one who will implement it
	 * freedom to choose which HTTP methods should his controller support
	 *
	 * @param requestService
	 * @return view name that will be resolved to the full JSP path
	 */
	public String post(RequestService  requestService) {
		return null;
	}

	public final HttpServletRequest getRequest(){
		return request;
	}

	public final HttpServletResponse getResponse(){
		return response;
	}

}
