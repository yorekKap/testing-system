package com.testing.system.web.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by yuri on 11.10.17.
 */
public class ValidatorFactory {
    Map<Class<?>, Validator<?>> validators;

    private ValidatorFactory(Map<Class<?>, Validator<?>> validators) {
        this.validators = validators;
    }

    public Optional<Validator> getValidator(Class clazz) {
        return Optional.ofNullable(validators.get(clazz));
    }

    public static ValidatorFactoryBuilder builder() {
        return new ValidatorFactoryBuilder();
    }

    public static class ValidatorFactoryBuilder {
        Map<Class<?>, Validator<?>> validators;

        public ValidatorFactoryBuilder() {
            this.validators = new HashMap<>();
        }

        public <T> ValidatorFactoryBuilder put(Class<T> clazz,
                                               Validator<? extends T> validator) {
            this.validators.put(clazz, validator);
            return this;
        }

        public ValidatorFactory build(){
            return new ValidatorFactory(this.validators);
        }
    }

}
