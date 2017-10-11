package com.testing.system.config;

import com.testing.system.entities.User;
import com.testing.system.web.dto.CreateTestDto;
import com.testing.system.web.resolvers.LocaleResolver;
import com.testing.system.web.validators.CreateTestDtoValidator;
import com.testing.system.web.validators.UserValidator;
import com.testing.system.web.validators.ValidatorFactory;
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
        components.add(ValidatorFactory.class, getValidatorFactory());
        
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

    /**
     * Method for {@link ValidatorFactory} initialization
     *
     * @return {@link ValidatorFactory} instance
     */
    public ValidatorFactory getValidatorFactory() {
        return ValidatorFactory.builder()
                .put(User.class, new UserValidator())
                .put(CreateTestDto.class, new CreateTestDtoValidator())
                .build();
    }

}
