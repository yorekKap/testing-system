package com.testing.system.web.controllers.authorization;

import com.testing.system.entities.User;
import com.testing.system.service.interfaces.UserService;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;
import com.testing.system.web.parsers.RequestContentParser;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterController extends Controller {
	private static final Logger log = Logger.getLogger(RegisterController.class);

	UserService userService;

	public RegisterController(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String get(RequestService requestService) {
		return "register";
	}

	@Override
	public String post(RequestService requestService) {
		User user = requestService.getParametersAsObject(User.class);

		if (userService.save(user)){
			try {
				log.info("User with username: "+ user.getUsername() + ";password: " +
						  user.getPassword() + "is registered");
				getResponse().sendRedirect("/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		else{
			requestService.setAttribute("user", user);
			requestService.setAttribute("failed", true);
			log.info("Register is failed for user: " + user.toString());
			return "register";
		}
	}
}
