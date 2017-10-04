package com.testing.system.exceptions.web;

/**
 * Created by yuri on 29.09.17.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}

