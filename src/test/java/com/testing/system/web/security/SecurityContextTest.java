package com.testing.system.web.security;

import com.testing.system.exceptions.builders.SecurityContextBuildingException;
import com.testing.system.oldentities.enums.Role;
import org.junit.Test;

import static org.junit.Assert.*;

public class SecurityContextTest {

	private static String DEFAULT_REDIRECT_URL = "/default/redirect";

	@Test(expected = SecurityContextBuildingException.class)
	public void buildWithoutDefaultRedirectURL(){
		SecurityContext.createBuilder()
					   .build();
	}

	@Test
	public void getRedirectURLForUnpecifiedRole(){
		SecurityContext securityContext = SecurityContext.createBuilder()
											 			 .setDefaultRedirectUrl(DEFAULT_REDIRECT_URL)
											 			 .build();

		String redirectUrl = securityContext.getRedirectUrl(Role.USER);

		assertEquals(DEFAULT_REDIRECT_URL, redirectUrl);
	}

	@Test
	public void getRedirectURLForSpecifiedRole(){
		Role specifiedRole = Role.USER;
		String redirectForRole = "/redirect/for/user";
		SecurityContext securityContext = SecurityContext.createBuilder()
														 .addRedirectUrlForRole(specifiedRole, redirectForRole)
														 .setDefaultRedirectUrl(DEFAULT_REDIRECT_URL)
														 .build();

		String actualRedirect = securityContext.getRedirectUrl(specifiedRole);

		assertEquals(redirectForRole, actualRedirect);

	}

	@Test
	public void hasAccesWhileEmpty(){
		String testURL = "/test1";
		SecurityContext securityContext = SecurityContext.createBuilder()
											 			 .setDefaultRedirectUrl(DEFAULT_REDIRECT_URL)
											 			 .build();

		boolean hasAccess = securityContext.hasAccess(testURL, Role.NOT_AUTHNENTICATED);

		//by default for any URL access is permit all
		assertTrue(hasAccess);
	}

	@Test
	public void hasAccesWithForAnyPermitAll(){
		String testURL = "/test1";
		SecurityContext securityContext = SecurityContext.createBuilder()
														 .forAny().permitAll()
														 .setDefaultRedirectUrl(DEFAULT_REDIRECT_URL)
														 .build();

		boolean hasAccessForUser = securityContext.hasAccess(testURL, Role.USER);
		boolean hasAccesForAdmin = securityContext.hasAccess(testURL, Role.ADMIN);

		assertTrue(hasAccessForUser);
		assertTrue(hasAccesForAdmin);
	}

	@Test
	public void hasAccesWithForURLRestrictedToAdmin(){
		String testURL = "/test1";
		String forAdminURL = "/for/admin";
		SecurityContext securityContext = SecurityContext.createBuilder()
														 .forUrl(forAdminURL).hasRole(Role.ADMIN)
														 .forAny().permitAll()
														 .setDefaultRedirectUrl(DEFAULT_REDIRECT_URL)
														 .build();

		boolean hasAccessForUser = securityContext.hasAccess(forAdminURL, Role.USER);
		boolean hasAccessForUserToNotRestrictedURL = securityContext.hasAccess(testURL, Role.USER);
		boolean hasAccessForAdmin = securityContext.hasAccess(forAdminURL, Role.ADMIN);

		assertFalse(hasAccessForUser);
		assertTrue(hasAccessForUserToNotRestrictedURL);
		assertTrue(hasAccessForAdmin);

	}

	@Test
	public void hasAccessForMatchRestrictedToAdmin(){
		String testURL = "/test1";
		String forAdminMatch = "/admin*";
		String forAdminURL1 = "/admin/test";
		String forAdminURL2 = "/admin";
		String forAdminURL3 = "/admin/test1/test2/test3";
		SecurityContext securityContext = SecurityContext.createBuilder()
														 .forMatch(forAdminMatch).hasRole(Role.ADMIN)
														 .forAny().permitAll()
														 .setDefaultRedirectUrl(DEFAULT_REDIRECT_URL)
														 .build();

		boolean hasAccessForUser = securityContext.hasAccess(forAdminURL1, Role.USER);
		boolean hasAccessForUserToNotRestrictedURL = securityContext.hasAccess(testURL, Role.USER);
		boolean hasAccessForAdmin1 = securityContext.hasAccess(forAdminURL1, Role.ADMIN);
		boolean hasAccessForAdmin2 = securityContext.hasAccess(forAdminURL2, Role.ADMIN);
		boolean hasAccessForAdmin3 = securityContext.hasAccess(forAdminURL3, Role.ADMIN);


		assertFalse(hasAccessForUser);
		assertTrue(hasAccessForUserToNotRestrictedURL);
		assertTrue(hasAccessForAdmin1);
		assertTrue(hasAccessForAdmin2);
		assertTrue(hasAccessForAdmin3);
	}

}
