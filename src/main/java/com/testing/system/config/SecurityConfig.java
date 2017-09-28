package com.testing.system.config;

import com.testing.system.entities.enums.Role;
import com.testing.system.web.security.SecurityContext;
import org.apache.log4j.Logger;

/**
 * {@link Config} implementation for fulfilling components context
 * with application security components
 *
 * @author yuri
 */
public class SecurityConfig implements Config {
    private static final String LOGOUT = "/logout";

    private static final Logger log = Logger.getLogger(SecurityConfig.class);

    //urls
    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";
    private static final String WELCOME = "/welcome";
    private static final String STUDENT = "/student";
    private static final String TUTOR = "/tutor";

    //url patterns
    private static final String RESOURCES_PATTERN = "/resources/*";
    private static final String TUTOR_PATTERN = "/tutor*";


    /**
     * {@inheritDoc}
     */
    @Override
    public void init(Components components) {
        components.add(SecurityContext.class, getSecurityContext());

        log.info("Security components added");
    }


    /**
     * Initialize {@link SecurityContext}
     *
     * @return @{link SecurityContext} instance
     */
    private SecurityContext getSecurityContext() {
        return SecurityContext.createBuilder()
                //rules for urls
                .forUrl(LOGIN).hasRole(Role.NOT_AUTHENTICATED)
                .forUrl(REGISTER).hasRole(Role.NOT_AUTHENTICATED)
                .forUrl(LOGOUT).hasRole(Role.TUTOR, Role.STUDENT)
                //rules for patterns
                .forMatch(RESOURCES_PATTERN).permitAll()
                .forMatch(TUTOR_PATTERN).hasRole(Role.TUTOR)
                //rules for any other
                .forAny().hasRole(Role.STUDENT)
                //redirect rules
                .addRedirectUrlForRole(Role.NOT_AUTHENTICATED, LOGIN)
                .addRedirectUrlForRole(Role.STUDENT, STUDENT)
                .addRedirectUrlForRole(Role.TUTOR, TUTOR)
                .setDefaultRedirectUrl(WELCOME)
                .build();
    }
}
