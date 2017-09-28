package com.testing.system.utils;

import javax.servlet.http.HttpServletRequest;

public class PageIndexFetcher {

	private static final String DEFAULT_PAGE_INDEX_PARAM = "page-index";

	public static int getPageIndex(HttpServletRequest request){
		String pageIndexStr = request.getParameter(DEFAULT_PAGE_INDEX_PARAM);
		int pageIndex = 1;
		if(pageIndexStr != null){
			pageIndex = Integer.valueOf(pageIndexStr);
		}

		return pageIndex;
	}

	public static int getPageIndex(HttpServletRequest request, String pageIndexParameter){
		String pageIndexStr = request.getParameter(pageIndexParameter);
		int pageIndex = 1;
		if(pageIndexStr != null){
			pageIndex = Integer.valueOf(pageIndexStr);
		}

		return pageIndex;
	}

}
