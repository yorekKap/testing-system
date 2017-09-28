package com.testing.system.config;

import com.testing.system.web.resolvers.LocaleResolver;
import org.apache.log4j.Logger;

import java.util.Locale;

/**
 * {@link Config} implementation for fulfilling components context
 * with application auxiliary components
 *
 * @author yuri
 */
public class RootConfig implements Config {
    private static final Logger log = Logger.getLogger(RootConfig.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(Components components) {
        components.add(LocaleResolver.class, getLocaleResolver());

        log.info("Root components added");
    }

    /**
     * Method for {@link LocaleResolver} initialization
     *
     * @return {@link LocaleResolver} instance
     */
    public LocaleResolver getLocaleResolver() {
        return LocaleResolver.getBuilder()
                .setDefaultLocale(Locale.ENGLISH)
                .addLocale("ua", new Locale("ua", "UA"))
                .createLocaleResolver();
    }

}
