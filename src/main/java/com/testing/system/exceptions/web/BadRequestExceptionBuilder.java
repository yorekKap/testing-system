package com.testing.system.exceptions.web;

/**
 * Created by yuri on 03.10.17.
 */
public class BadRequestExceptionBuilder {

    private static final String NO_PARAMETER_RESPONSE = "No %s in request parameters";
    private static final String NO_ACTION_RESPONSE = "No action parameter in request";

    public static BadRequestException noParameter(String parameter){
        return new BadRequestException(String.format(NO_PARAMETER_RESPONSE, parameter));
    }

    public static BadRequestException noAction(){
        return new BadRequestException(NO_ACTION_RESPONSE);
    }
}
