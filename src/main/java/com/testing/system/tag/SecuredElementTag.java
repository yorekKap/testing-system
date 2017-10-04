package com.testing.system.tag;

import com.testing.system.entities.enums.Role;
import com.testing.system.web.security.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by yuri on 01.10.17.
 */
public class SecuredElementTag extends SimpleTagSupport{

    private static final String PRINCIPAL = "principal";

    private StringWriter stringWriter = new StringWriter();
    private String requiredRole;

    public void setRequiredRole(String requiredRole) {
        this.requiredRole = requiredRole;
    }

    @Override
    public void doTag() throws JspException, IOException {
        Role currentRole = getCurrentRole();
        if(isAccessible(currentRole)){
            getJspBody().invoke(stringWriter);
            getJspContext().getOut().println(stringWriter.toString());
        }
    }

    private boolean isAccessible(Role currentRole){
        Role requiredRole = Role.valueOf(this.requiredRole);

        if(currentRole == Role.ANY)
            return true;
        if(currentRole == requiredRole){
            return true;
        }

        return false;
    }

    private Role getCurrentRole(){
        PageContext pageContext = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        return ((UserPrincipal)request.getSession().getAttribute(PRINCIPAL)).getRole();
    }
}
