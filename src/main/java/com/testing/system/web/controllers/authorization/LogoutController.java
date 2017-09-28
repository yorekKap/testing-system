package com.testing.system.web.controllers.authorization;

import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.security.UserPrincipal;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutController extends Controller {
	private static final Logger log = Logger.getLogger(LogoutController.class);

	private static final String PRINCIPAL = "principal";
	private static final String LOGIN_URL = "/login";

	@Override
	public String get(HttpServletRequest request, HttpServletResponse response) {
		String username = ((UserPrincipal)request.getSession().getAttribute(PRINCIPAL)).getUsername();
		request.getSession().removeAttribute(PRINCIPAL);
		log.info("User: " + username + " has logged out");

		try {
			response.sendRedirect(LOGIN_URL);
		} catch (IOException e) {
			log.error("IOException in LogoutController:", e);
			e.printStackTrace();
		}
		return null;
	}

}
