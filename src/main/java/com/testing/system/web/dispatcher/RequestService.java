package com.testing.system.web.dispatcher;

import com.testing.system.web.dispatcher.enums.ActionType;
import com.testing.system.web.parsers.JsonParser;
import com.testing.system.web.parsers.RequestContentParser;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by yuri on 29.09.17.
 */
public class RequestService {

    private final static String CONTENT_PARAMETER = "content";
    private final static String ACTION_PARAMETER = "action";

    private HttpServletRequest request;

    public RequestService(HttpServletRequest request) {
        this.request = request;
    }

    public Optional<Integer> getInteger(String parameter){
        return Optional.ofNullable(Integer.valueOf(request.getParameter(parameter)));
    }

    public Optional<Long> getLong(String parameter){
        return Optional.ofNullable(Long.valueOf(request.getParameter(parameter)));
    }

    public Optional<Double> getDouble(String parameter){
        return Optional.ofNullable(Double.valueOf(request.getParameter(parameter)));
    }

    public Optional<String> getString(String parameter){
        return Optional.of(request.getParameter(parameter));
    }

    public <T> T getParametersAsObject(Class<T> clazz){
        return RequestContentParser.parse(request, clazz);
    }

    public <T> T getContentAsObject(Class<T> clazz){
        return JsonParser.parse(clazz, request.getParameter(CONTENT_PARAMETER));
    }

    public String getContentAsString(){
        return request.getParameter(CONTENT_PARAMETER);
    }

    public ActionType getAction(){
        return ActionType.valueOf(request.getParameter(ACTION_PARAMETER));
    }

    public void setAttribute(String attribute, Object value){
        request.setAttribute(attribute, value);
    }

}
