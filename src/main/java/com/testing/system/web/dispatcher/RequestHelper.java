package com.testing.system.web.dispatcher;

import com.testing.system.config.WebAppContext;
import com.testing.system.exceptions.dispatcher.BadRequestException;
import com.testing.system.utils.ContextPathFetcher;

import javax.servlet.http.HttpServletRequest;

/**
 * Serve for fetching right {@link Controller} by means of
 * {@link ControllersMapper}
 * </p>
 * Made as singleton in illustration purposes.
 *
 * @author yuri
 */
public class RequestHelper {

	private static RequestHelper instance;

	private ControllersMapper mapper;

	private RequestHelper(){
		mapper = WebAppContext.get(ControllersMapper.class);
	}

	private RequestHelper(ControllersMapper mapper){
		this.mapper = mapper;
	}

	public Controller getController(HttpServletRequest request){
		String url = ContextPathFetcher.getContextPath(request);
		Controller controller = mapper.get(url);
			if(controller == null){
				throw new BadRequestException("There is no controller for " + url);
			}

		return controller;
	}

	public static RequestHelper getInstance(){
		if(instance == null){
			synchronized(RequestHelper.class){
				if(instance == null){
					return new RequestHelper();
				}
			}
		}

		return instance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
