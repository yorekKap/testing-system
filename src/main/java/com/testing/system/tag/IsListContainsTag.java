package com.testing.system.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by yuri on 03.10.17.
 */
public class IsListContainsTag extends SimpleTagSupport {

    private StringWriter stringWriter = new StringWriter();
    private List<Object> list;
    private Object value;

    public void setList(List<Object> list) {
        this.list = list;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (list.contains(value)) {
            getJspBody().invoke(stringWriter);
            getJspContext().getOut().println(stringWriter.toString());
        }
    }
}
