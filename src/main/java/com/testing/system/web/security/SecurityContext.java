package com.testing.system.web.security;

import com.testing.system.entities.enums.Role;
import com.testing.system.exceptions.builders.SecurityContextBuildingException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *Class for managing security rules which includes
 *what {@link Role} is required for every URL and to what
 *URL to redirect each {@link Role}
 *
 * @author yuri
 *
 */
public class SecurityContext {

	/**
	 *Precise URL : Roles
	 */
	private Map<String, List<Role>> urls;

	/**
	 *URL pattern : Roles
	 */
	private Map<String, List<Role>> matches;

	/**
	 *Role for every URL that aren't in {@code #urls} or {@code #matches}
	 */
	private List<Role> forAny;

	/**
	 *Role : Redirect URL
	 */
	private Map<Role, String> redirectUrls;


	/**
	 * Redirect URL for every role not specified in {@link #redirectUrls}
	 */
	private String defaultRedirectUrl;

	private SecurityContext(Map<String, List<Role>> urls, Map<String, List<Role>> matches, String redirectUrl,
							Map<Role, String> redirectUrls) {
		this.urls = urls;
		this.matches = matches;
		this.defaultRedirectUrl = redirectUrl;
		this.redirectUrls = redirectUrls;

	}

	private SecurityContext(Map<String, List<Role>> urls, Map<String, List<Role>> matches, List<Role> forAny,
							String redirectUrl, Map<Role, String> redirectUrls) {
		this.urls = urls;
		this.matches = matches;
		this.forAny = forAny;
		this.defaultRedirectUrl = redirectUrl;
		this.redirectUrls = redirectUrls;
	}


	/**
	 * Check if given {@code role} has access to the {@code url}
	 *
	 * @param url
	 * @param role
	 * @return true if access can be acquired, false otherwise
	 */
	public boolean hasAccess(String url, Role role){
		List<Role> rolesRequired;
		if((rolesRequired = urls.get(url)) != null){
			return checkRole(rolesRequired, role);
		}
		else if((rolesRequired = getMatch(url)) != null){
			return checkRole(rolesRequired, role);
		}
		else{
			return checkRole(forAny, role);
		}
	}


	/**
	 * Check if actual {@link Role} matches required {@link Role}
	 *
	 * @param requiredRoles
	 * @param actualRole
	 * @return true if matches, false - otherwise
	 */
	private boolean checkRole(List<Role> requiredRoles, Role actualRole){
		if(requiredRoles.contains(Role.ANY)){
			return true;
		}

		return requiredRoles.contains(actualRole);
	}


	/**
	 * Return {@link List} {@link Role} that binded to the pattern that matches {@code url}
	 *
	 * @param url that match against pattern is {@code #matches}
	 * @return {@link Role}
	 */
	private List<Role> getMatch(String url){
		String key = matches.keySet().stream()
						.filter(x -> url.matches(x))
						.findFirst()
						.orElse(null);

		if(key == null){
			return null;
		}

		return matches.get(key);
	}


	/**
	 * Get redirect URL for given {@link Role}
	 *
	 * @param role
	 * @return URL
	 */
	public String getRedirectUrl(Role role){
		String redirectUrl = redirectUrls.get(role);
		if(redirectUrl != null){
			return redirectUrl;
		}
		else{
			return defaultRedirectUrl;
		}
	}

	/**
	 * @return builder class
	 */
	public static SecurityContextBuilder createBuilder(){
		return new SecurityContextBuilder();
	}


	/**
	 * Builder class for the {@link SecurityContext}
	 *
	 *
	 * @author yuri
	 */
	public static class SecurityContextBuilder implements SecurityContextBuilderAccesModifier{

		private static final String MANDATORY_REDIRECT_URL_MESSAGE = "Redirect url must be set";

		private Map<String, List<Role>> urls = new HashMap<>();
		private Map<String, List<Role>> matches = new HashMap<>();
		private Map<Role, String> redirectUrls = new HashMap<>();
		private List<Role> forAny = Arrays.asList(Role.ANY);
		private String defaultRedirectUrl;
		private String tempUrl;
		private String tempMatch;

		public SecurityContextBuilderAccesModifier forUrl(String url){
			this.tempUrl = url;
			return this;
		}

		public SecurityContextBuilderAccesModifier forMatch(String url){
			this.tempMatch = url.replace("*", ".*");
			return this;
		}

		public SecurityContextBuilderAccesModifier forAny(){
			return this;
		}

		@Override
		public SecurityContextBuilder hasRole(Role... roles) {
			putRolesToMap(Arrays.asList(roles));
			clearTemps();
			return this;
		}

		@Override
		public SecurityContextBuilder permitAll() {
			putRolesToMap(Arrays.asList(Role.ANY));
			clearTemps();
			return this;
		}


		private void putRolesToMap(List<Role> roles){
			if (tempUrl != null){
				urls.put(tempUrl, roles);
			}
			else if (tempMatch != null){
				matches.put(tempMatch, roles);
			}
			else{
				forAny = roles;
			}
		}

		private void clearTemps(){
			tempUrl = null;
			tempMatch = null;
		}

		public SecurityContextBuilder setDefaultRedirectUrl(String redirectUrl){
			this.defaultRedirectUrl = redirectUrl;
			return this;
		}

		public SecurityContextBuilder addRedirectUrlForRole(Role role, String redirectUrl){
			this.redirectUrls.put(role, redirectUrl);
			return this;
		}


		public SecurityContext build(){
			if(defaultRedirectUrl == null){
				throw new SecurityContextBuildingException(MANDATORY_REDIRECT_URL_MESSAGE);
			}
			return new SecurityContext(urls, matches, forAny, defaultRedirectUrl, redirectUrls);
		}
	}
}
