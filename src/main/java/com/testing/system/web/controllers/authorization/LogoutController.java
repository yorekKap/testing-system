package com.testing.system.web.controllers.authorization;

import com.testing.system.web.constants.SessionScopeAttributes;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;
import com.testing.system.web.security.UserPrincipal;
import org.apache.log4j.Logger;

import java.io.IOException;

public class LogoutController extends Controller {
	private static final Logger log = Logger.getLogger(LogoutController.class);

	private static final String LOGIN_URL = "/login";

	@Override
	public String get(RequestService requestService) {
		String username = ((UserPrincipal)getRequest().getSession()
				.getAttribute(SessionScopeAttributes.PRINCIPAL))
				.getUsername();
		requestService.getSessionService().cleanSessionScope();

		log.info("User: " + username + " has logged out");

		try {
			getResponse().sendRedirect(LOGIN_URL);
		} catch (IOException e) {
			log.error("IOException in LogoutController:", e);
			e.printStackTrace();
		}
		return null;
	}

}
