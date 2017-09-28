package com.testing.system.utils;

import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ContextPathFetcherTest {

	@Test
	public void getContextPath(){
		String URL = "http://localhost:8080/login";
		String expectedContextPath = "/login";
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURL()).thenReturn(new StringBuffer(URL));

		String actualContextPath = ContextPathFetcher.getContextPath(request);

		assertEquals(expectedContextPath, actualContextPath);

	}

}
