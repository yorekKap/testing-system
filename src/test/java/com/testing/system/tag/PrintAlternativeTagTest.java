package com.testing.system.tag;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspWriter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PrintAlternativeTagTest {

	private static final String OUT = "out";
	private static final String OTHERWISE = "otherwise";

	@Mock
	JspWriter jspWriter;

	@Mock
	JspContext context;

	@Captor
	ArgumentCaptor<String> outCaptor;

	PrintAlternativeTag tag;

	@Before
	public void setUpBeforeMethod(){
		when(context.getOut()).thenReturn(jspWriter);
		tag = new PrintAlternativeTag();
		tag.setJspContext(context);
	}

	@Test
	public void printAlternativeIfTrue(){
		tag.setIfTrue(true);
		tag.setOut(OUT);
		tag.setOtherwise(OTHERWISE);

		try {
			tag.doTag();
			verify(jspWriter).print(outCaptor.capture());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String actualOut = outCaptor.getValue();
		assertEquals(OUT, actualOut);
	}

	@Test
	public void printAlternativeIfFalse(){
		tag.setIfTrue(false);
		tag.setOut(OUT);
		tag.setOtherwise(OTHERWISE);

		try {
			tag.doTag();
			verify(jspWriter).print(outCaptor.capture());
		} catch (Exception e) {
			e.printStackTrace();
		}

		String actualOut = outCaptor.getValue();
		assertEquals(OTHERWISE, actualOut);
	}

}
