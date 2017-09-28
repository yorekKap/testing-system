package com.testing.system.web.filters;

import com.testing.system.config.WebAppContext;
import com.testing.system.entities.enums.Role;
import com.testing.system.utils.ContextPathFetcher;
import com.testing.system.web.security.SecurityContext;
import com.testing.system.web.security.UserPrincipal;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Check if particular resource is available for current user session.
 *
 * @author yuri
 *
 */
public class SecurityFilter implements Filter {

	private static final String PRINCIPAL = "principal";

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void destroy() {
	}



	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		SecurityContext securityContext = WebAppContext.get(SecurityContext.class);

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String url = ContextPathFetcher.getContextPath(httpRequest);
		UserPrincipal principal = (UserPrincipal)httpRequest.getSession().getAttribute(PRINCIPAL);
		Role role = null;

		if(principal == null){
			role = Role.NOT_AUTHENTICATED;
		}
		else{
			role = principal.getRole();
		}

		if (securityContext.hasAccess(url, role)) {
			filterChain.doFilter(request, response);
		}
		else{
			String redirectUrl = securityContext.getRedirectUrl(role);
			((HttpServletResponse)response).sendRedirect(redirectUrl);
		}

	}


}
