package com.cmi.emdsystem.config;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = -1425734704756918633L;

    public BadRequestException(String message) {
        super(message);
    }
}