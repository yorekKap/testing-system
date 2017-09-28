package com.testing.system.web.security;

import com.testing.system.entities.enums.Role;

/**
 * Interface for convenient {@link SecurityContext} building
 *
 * @author yuri
 *
 */
public interface SecurityContextBuilderAccesModifier {

	SecurityContext.SecurityContextBuilder hasRole(Role... role);
	SecurityContext.SecurityContextBuilder permitAll();

}
