package com.testing.system.web.listeners;

import com.testing.system.web.constants.ApplicationScopeAttributes;
import com.testing.system.web.container.CategoryContainer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by yuri on 11.10.17.
 */
public class TestingSystemContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        CategoryContainer categoryContainer = new CategoryContainer();
        servletContextEvent.getServletContext()
                .setAttribute(ApplicationScopeAttributes.CATEGORY_CONTAINER, categoryContainer);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext()
                .removeAttribute(ApplicationScopeAttributes.CATEGORY_CONTAINER);
    }
}
