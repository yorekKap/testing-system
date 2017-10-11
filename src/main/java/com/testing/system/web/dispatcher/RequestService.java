package com.testing.system.web.dispatcher;

import com.testing.system.config.WebAppContext;
import com.testing.system.exceptions.web.BadRequestExceptionBuilder;
import com.testing.system.web.dispatcher.enums.ActionType;
import com.testing.system.web.parsers.JsonParser;
import com.testing.system.web.parsers.RequestContentParser;
import com.testing.system.web.validators.ValidatorFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Optional;

/**
 * Created by yuri on 29.09.17.
 */
public class RequestService {

    private final static String CONTENT_PARAMETER = "content";
    private final static String ACTION_PARAMETER = "action";

    private HttpServletRequest request;
    private SessionService sessionService;
    private ValidatorFactory validatorFactory;

    public RequestService(HttpServletRequest request) {
        this.request = request;
        this.sessionService = new SessionService(request.getSession());
        this.validatorFactory = WebAppContext.get(ValidatorFactory.class);
    }


    public Optional<Integer> getIntegerAsOptional(String parameter) {
        String value = request.getParameter(parameter);
        if (value == null) {
            return Optional.empty();
        }

        return Optional.of(Integer.valueOf(value));
    }

    public Optional<Long> getLongAsOptional(String parameter) {
        String value = request.getParameter(parameter);
        if (value == null) {
            return Optional.empty();
        }

        return Optional.of(Long.valueOf(value));
    }

    public Optional<Double> getDoubleAsOptional(String parameter) {
        String value = request.getParameter(parameter);
        if (value == null) {
            return Optional.empty();
        }

        return Optional.of(Double.valueOf(value));
    }

    public Optional<String> getStringAsOptional(String parameter) {
        String value = request.getParameter(parameter);
        if (value == null) {
            return Optional.empty();
        }

        return Optional.of(value);
    }


    public Integer getInteger(String parameter) {
        String value = request.getParameter(parameter);
        if (value == null) {
            throw BadRequestExceptionBuilder.noParameter(parameter);
        }

        return Integer.valueOf(value);
    }

    public Long getLong(String parameter) {
        String value = request.getParameter(parameter);
        if (value == null) {
            throw BadRequestExceptionBuilder.noParameter(parameter);
        }

        return Long.valueOf(value);
    }

    public Double getDouble(String parameter) {
        String value = request.getParameter(parameter);
        if (value == null) {
            throw BadRequestExceptionBuilder.noParameter(parameter);
        }

        return Double.valueOf(value);
    }

    public String getString(String parameter) {
        String value = request.getParameter(parameter);
        if (value == null) {
            throw BadRequestExceptionBuilder.noParameter(parameter);
        }

        return value;
    }

    public <T> T getParametersAsObject(Class<T> clazz) {
        T object = RequestContentParser.parse(request, clazz);
        validatorFactory.getValidator(clazz)
                .ifPresent(v -> v.validate(object));

        return object;
    }

    public <T> T getContentAsObject(Class<T> clazz) {
        T object = JsonParser.parse(clazz, request.getParameter(CONTENT_PARAMETER));

        validatorFactory.getValidator(clazz)
                .ifPresent(v -> v.validate(object));

        return object;
    }

    public String getContentAsString() {
        return request.getParameter(CONTENT_PARAMETER);
    }

    public ActionType getAction() {
        return ActionType.valueOf(request.getParameter(ACTION_PARAMETER));
    }

    public void setAttribute(String attribute, Object value) {
        request.setAttribute(attribute, value);
    }

    public SessionService getSessionService() {
        return sessionService;
    }

}
