package com.testing.system.web.controllers.authorization;

import com.testing.system.entities.Category;
import com.testing.system.entities.enums.Role;
import com.testing.system.exceptions.service.AuthenticationException;
import com.testing.system.service.interfaces.AuthenticationService;
import com.testing.system.service.interfaces.CategoryService;
import com.testing.system.web.constants.SessionScopeAttributes;
import com.testing.system.web.dispatcher.Controller;
import com.testing.system.web.dispatcher.RequestService;
import com.testing.system.web.dto.UserLoginDto;
import com.testing.system.web.parsers.RequestContentParser;
import com.testing.system.web.security.UserPrincipal;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoginController extends Controller {
    private static final Logger log = Logger.getLogger(LoginController.class);

    private static final String LOGIN_VIEW = "login";
    private static final String FAIL = "fail";

    AuthenticationService authService;

    public LoginController(AuthenticationService authService) {
        this.authService = authService;
    }

    @Override
    public String get(RequestService requestService) {
        return LOGIN_VIEW;
    }

    @Override
    public String post(RequestService requestService) {
        UserLoginDto userLogin = requestService.getParametersAsObject(UserLoginDto.class);

        UserPrincipal principal = null;
        try {
            principal = authService.login(userLogin.getUsername(), userLogin.getPassword());
            getRequest().getSession().setAttribute(SessionScopeAttributes.PRINCIPAL, principal);
            getRequest().getSession().setMaxInactiveInterval(20 * 60);

            if (principal.getRole() == Role.TUTOR) {
                getResponse().sendRedirect("/tutor");
                log.info("Tutor " + principal.getUsername() +
                        " has been logged in");
            } else {
                getResponse().sendRedirect("/student");
                log.info("Student " + principal.getUsername() +
                        " has been logged in");
            }
            return null;

        } catch (AuthenticationException e) {
            requestService.setAttribute(FAIL, e.getReason());
            requestService.setAttribute("fpassword", userLogin.getPassword());
            requestService.setAttribute("fusername", userLogin.getUsername());

            log.info("Login failed for username: " + userLogin.getUsername() +
                    ";password: " + userLogin.getPassword());
            return LOGIN_VIEW;
        } catch (IOException e) {
            log.error("IOException in LoginController:", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
