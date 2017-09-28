package com.testing.system.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Provide convenient way to get context path from the
 * given URL
 *
 * @author yuri
 *
 */
public class ContextPathFetcher {

	/**
	 * Get context path from the given URL
	 *
	 * @param request
	 * @return context path
	 */
	public static String getContextPath(HttpServletRequest request){
		String url = request.getRequestURL().toString();
		Pattern p = Pattern.compile("http://[^/]+");
		Matcher m = p.matcher(url);
		if(m.find()){
			url = url.substring(m.end(), url.length());
		}
		return url;
}
}
