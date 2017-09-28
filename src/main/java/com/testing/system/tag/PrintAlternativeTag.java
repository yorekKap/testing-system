package com.testing.system.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;


/**
 * Custom JSP tag for convenient printing out on of the two alternatives
 *
 * @author yuri
 *
 */
public class PrintAlternativeTag extends SimpleTagSupport{
	private static final Logger log = Logger.getLogger(PrintAlternativeTag.class);

	private boolean ifTrue;
	private String out;
	private String otherwise;

	public void setIfTrue(boolean ifTrue) {
		this.ifTrue = ifTrue;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public void setOtherwise(String otherwise) {
		this.otherwise = otherwise;
	}

	@Override
	public void doTag() throws JspException, IOException {
			String toPrint = ifTrue ? out : otherwise;
			getJspContext().getOut().print(toPrint);
			log.info(toPrint + "printed by the PrintAlternativeTag");
	}

}
